package tools.excel;

import cn.hutool.json.JSONObject;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * ExcelUtils
 *
 * @author: hyr
 * @date: 2022-11-18
 **/
@Slf4j
public class ExcelUtils {

    public static void main(String[] args) {
        read4();
    }


    public static void read4() {
        ExcelReader reader = ExcelUtil.getReader("D:\\download\\窨井监测20230222.xlsx", 0);
        List<List<Object>> read = reader.read(1, 10);

        for (List<Object> line : read) {
            String deviceCode = "MHC2023" + line.get(1).toString().trim();
            String sqlFormat = "update info_device set address = '%s', longitude = '%s', latitude = '%s' where device_code = '%s';";
            System.out.println(
                String.format(sqlFormat, line.get(0), line.get(5), line.get(6), deviceCode));
        }

        log.info("{}", read.get(read.size() - 1));
    }

    public static void read() {
        ExcelReader reader = ExcelUtil.getReader("D:\\download\\2022年1月太仓市“河长制”监测断面水质汇总表.xlsx", 0);
        List<List<Object>> read = reader.read(4, 75);
        for (int i = 0; i < read.get(0).size(); i++) {
            log.info("{}, {}", i, read.get(0).get(i));
        }
        log.info("{}", read.get(read.size() - 1));
    }

    public static void read2() {
        ExcelReader reader = ExcelUtil.getReader("D:\\download\\供水管网监测点(乡镇)(1).xlsx", 0);
        List<List<Object>> read = reader.read(0);
        for (int i = 1; i < read.size(); i++) {
            List<Object> objects = read.get(i);
            if (objects.size() < 9) {
                continue;
            }
            String id = objects.get(1).toString();
            String area = objects.get(8).toString();
            System.out.println(String.format(
                "update info_ds_check_point set area_code = '%s' where station_no = %s;", area,
                id));
//            log.info("{}, {}, == {} == {}", i, objects, objects.get(1), objects.get(8));
        }
        log.info("{}", read.get(read.size() - 1));
    }

    public static void read3() {
        ExcelReader reader = ExcelUtil.getReader("D:\\download\\2_0106#排水处污水泵站需呈现数据(1).xlsx", 0);
        List<List<Object>> read = reader.read(0);
        List<JSONObject> list = new ArrayList<>();
        for (int i = 1; i < read.size(); i++) {
            List<Object> objects = read.get(i);
            JSONObject item = new JSONObject();
            item.set("name", objects.get(1).toString());
            item.set("type", objects.get(2).toString());
            list.add(item);
        }

        ExcelReader reader2 = ExcelUtil.getReader("D:\\download\\2_0106#排水处污水泵站需呈现数据(1).xlsx", 1);
        List<List<Object>> read2 = reader2.read(0);
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i < read2.size(); i++) {
            List<Object> objects = read2.get(i);
            if (objects.size() <= 3) {
                continue;
            }
            String name = objects.get(1).toString();
            String name2 = objects.get(3).toString();
            if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(name2)) {
                map.put(name, name2);
            }
        }

        list.forEach(item -> {
            String name = item.getStr("name").trim();
            String type = item.getStr("type").trim();
            String name2 = map.get(name);

//            log.info("name : {}, type : {}, name2 : {}", name, type, name2);
            if (StringUtils.isBlank(name2)) {
                System.out.println(String.format(
                    "update info_ss_lift_pump set pump_type = '%s' where name = '%s';", type,
                    name));
            } else {
                System.out.println(String.format(
                    "update info_ss_lift_pump set pump_type = '%s' where name = '%s' or name = '%s';",
                    type, name, name2.trim()));
            }
        });

    }

    @Test
    public void readWell() {
        ExcelReader reader = ExcelUtil.getReader("D:\\download\\井盖20230324.xlsx", 0);
        List<List<Object>> read = reader.read(0);
        log.info("k start : {}", read.get(2));
        log.info("k end : {}", read.get(37));
        log.info("c start : {}", read.get(39));
        log.info("c end : {}", read.get(70));

        System.out.println();
        for (int i = 2; i <= 37; i++) {
            List<Object> line = read.get(i);
            String sql = String.format(
                "update info_device set area = 'KJXC', longitude = '%s', latitude = '%s', device_name = '%s', address = '%s' where device_code = '%s';",
                line.get(1).toString().trim(), line.get(2).toString().trim(),
                line.get(3).toString().trim(), line.get(3).toString().trim(),
                "9a475920db7540ffb248f3aaa7228f80_MHC2023" + line.get(0).toString().trim());
            System.out.println(sql);
        }
        for (int i = 39; i <= 70; i++) {
            List<Object> line = read.get(i);
            String sql = String.format(
                "update info_device set area = 'CXZ', longitude = '%s', latitude = '%s', device_name = '%s', address = '%s' where device_code = '%s';",
                line.get(1).toString().trim(), line.get(2).toString().trim(),
                line.get(3).toString().trim(), line.get(3).toString().trim(),
                "9a475920db7540ffb248f3aaa7228f80_MHC2023" + line.get(0).toString().trim());
            System.out.println(sql);
        }
    }

}
