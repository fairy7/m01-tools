package tools.excel;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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

    @Test
    public void readEPb() {
        ExcelReader reader = ExcelUtil.getReader("D:\\download\\0327#环保局水质监测_河道(1).xlsx", 0);
        List<List<Object>> read = reader.read(0);

        System.out.println();
        for (List<Object> line : read) {
            String sql = String.format(
                "update `info_env_epb` set river_course = '%s' where station_id = '%s';",
                line.get(6).toString().trim(), line.get(0).toString().trim());
            System.out.println(sql);
        }
    }

    @Test
    public void tett() {
        Map<String, Object> dataMap= new HashMap<>();
        log.info("{}", Double.parseDouble((String) dataMap.get("latitude")));
    }

    private Map<String, String> areas() {
        Map<String, String> areas = new HashMap<>();
        areas.put("城厢镇","CXZ");
        areas.put("港区","GQ");
        areas.put("浮桥镇","GQ");
        areas.put("璜泾镇","HJZ");
        areas.put("科教新城","KJXC");
        areas.put("浏河镇","LHZ");
        areas.put("双凤镇","SFZ");
        areas.put("沙溪镇","SXZ");
        areas.put("高新区","XQ");
        return areas;
    }

    @Test
    public void readRepair() {
        ExcelReader reader = ExcelUtil.getReader("D:\\download\\0407#更新#太仓市劣V类水体整治进展(1).xls", 1);
        List<List<Object>> read = reader.read(0);

        log.info("0 : {}", read.get(0));
        log.info("1 : {}", read.get(1));
        log.info("2 : {}", read.get(2));
        log.info("3 : {}", read.get(3));

        System.out.println();
        for (int i = 1; i < read.size(); i++) {
            List<Object> line = read.get(i);
            String sql = String.format(
                "insert into taicang_water.info_env_repair(`id`, `river_name`, `river_level`, `length`, `reason`, `main_river`, `area_code`, `year`, `in_build_area`, `invest_amount`, `note`) values (%s, '%s','%s',%s,null,null,'%s',%s,%s,%s,null);",
                line.get(0).toString().trim(), line.get(1).toString().trim(), line.get(2).toString().trim(), line.get(3).toString().trim(), areas().get(line.get(4).toString().trim()).trim(),
                line.get(6).toString().trim().substring(0,4), line.get(5).toString().trim().equals("是") ? 1 : 0, line.get(7).toString().trim());
            System.out.println(sql);
        }

    }

    @Test
    public void readTimeRepair() {
        ExcelReader reader = ExcelUtil.getReader("D:\\download\\0406#更新#太仓市劣V类水体整治进展(1).xls", 1);
        List<List<Object>> read = reader.read(0);

        log.info("0 : {}", read.get(0));
        log.info("1 : {}", read.get(1));
        log.info("2 : {}", read.get(2));
        log.info("3 : {}", read.get(3));

        System.out.println();
        String format = "insert into taicang_water.time_env_repair values (%s,'%s','%s');";
        for (int i = 1; i < read.size(); i++) {
            List<Object> line = read.get(i);
            System.out.println(String.format(
                format,
                2021, line.get(0).toString().trim(), line.get(7).toString().trim()));
            System.out.println(String.format(
                format,
                2022, line.get(0).toString().trim(), line.get(8).toString().trim()));
            System.out.println(String.format(
                format,
                2023, line.get(0).toString().trim(), line.get(9).toString().trim()));
        }
    }

    @Test
    public void read68Well() {
        ExcelReader reader = ExcelUtil.getReader("D:\\download\\窨井盖20230330(1).xlsx", 0);
        List<List<Object>> read = reader.read(2);

        log.info("0 : {}", read.get(0));
        log.info("1 : {}", read.get(1));
        log.info("2 : {}", read.get(2));
        log.info("3 : {}", read.get(3));

        System.out.println();
        String format = "update taicang_water.info_device set area= 'KJXC', device_name = '%s', address = '%s', longitude = '%s', latitude = '%s' where device_code = '9a475920db7540ffb248f3aaa7228f80_MHC2023%s';";
        for (int i = 0; i <= 43; i++) {
            List<Object> line = read.get(i);
            System.out.println(String.format(
                format,
                line.get(3).toString().trim(), line.get(3).toString().trim(), line.get(1).toString().trim(), line.get(2).toString().trim(), line.get(0).toString().trim()));
        }
    }

    @Test
    public void info_env_transectInsert() throws Exception {
        List<String> rs = new ArrayList<>();

        for (int j = 0; j < 3; j++) {
            String level = "";
            if (j == 0) {
                level = "市级";
            } else if (j == 1) {
                level = "镇级";
            } else if (j == 2) {
                level = "村级";
            }
            ExcelReader reader = ExcelUtil.getReader("D:\\requirements\\20220513太仓水务\\现期\\数据对接\\0417#修改#河长制监测点位汇总表(1).xlsx", j);
            List<List<Object>> read = reader.read(2);
            String format = "INSERT INTO `taicang_water`.`info_env_transect`(`transect_code`, `transect_name`, `river_name`, `river_level`, `river_length`, `trend`, `area_code`, `village`, `river_manager`, `transect_location`, `road`, `lng`, `lat`, `markers`, `year`, `major`) "
                + "select '%s', '%s', '%s', '%s', %s, '%s', '%s', '%s', '%s', '%s', '%s', %s, %s, '%s', 2022, 0 from dual where not exists (select transect_code from `taicang_water`.`info_env_transect` where transect_code = '%s'); ";
            for (int i = 0; i < read.size(); i++) {
                List<Object> line = read.get(i);
                if (StringUtils.isBlank(areas().get(line.get(5).toString().trim()))) {
                    throw new Exception("area is null");
                }
                String sql = String.format(
                    format,
                    line.get(1).toString().trim(), line.get(2).toString().trim(), line.get(3).toString().trim(), level,
                    StringUtils.isBlank(line.get(14).toString().trim()) ? null : line.get(14).toString().trim(), line.size() < 16 ? "" : line.get(15).toString().trim(), areas().get(line.get(5).toString().trim())
                    , line.get(6).toString().trim(), line.get(7).toString().trim(), line.get(8).toString().trim(), line.get(9).toString().trim()
                    ,  line.get(12).toString().trim(), line.get(13).toString().trim(), line.get(11).toString().trim(), line.get(1).toString().trim());
                rs.add(sql);
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(
            "C:\\Users\\huangyurun\\Desktop\\m_.sql"))) {
            for(String sql : rs) {
                bw.write(sql);
                bw.newLine();
            }
        } catch (IOException e) {

        }
    }


    @Test
    public void time_env_transectInsert(){
        List<String> filePaths = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            if (i == 4) {
                continue;
            }
            filePaths.add(String.format("D:\\requirements\\20220513太仓水务\\现期\\数据对接\\0414#手工监测断面新增数据\\月度水质\\2022年%s月太仓市“河长制”监测断面水质汇总表.xlsx", i));
        }
        filePaths.add("D:\\requirements\\20220513太仓水务\\现期\\数据对接\\0414#手工监测断面新增数据\\月度水质\\2023年1月太仓市“河长制”监测断面水质汇总表.xlsx");
//        filePaths.add("D:\\requirements\\20220513太仓水务\\现期\\数据对接\\0414#手工监测断面新增数据\\月度水质\\2023年2月太仓市“河长制”监测断面水质汇总表（替）.xlsx");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(
            "C:\\Users\\huangyurun\\Desktop\\m.sql"))) {
            for(String filePath : filePaths) {
                List<String> strings = time_env_transectInsert(filePath);
                for (String sql : strings) {
                    bw.write(sql);
                    bw.newLine();
                }
            }
            List<String> strings = time_env_transectInsert2("D:\\requirements\\20220513太仓水务\\现期\\数据对接\\0414#手工监测断面新增数据\\月度水质\\2023年2月太仓市“河长制”监测断面水质汇总表（替）.xlsx");
            for (String sql : strings) {
                bw.write(sql);
                bw.newLine();
            }
        } catch (IOException e) {

        }



    }
    public List<String> time_env_transectInsert(String filePath){
        List<String> rs = new ArrayList<>();

        for (int j = 0; j < 3; j++) {

            ExcelReader reader = ExcelUtil.getReader(filePath, j);
            List<List<Object>> read = reader.read(2);
            String format = "INSERT INTO `taicang_water`.`time_transect_quality_data`(`transect_code`, `water_level`, `flow`, `flow_direct`, `depth`, `temp`, `PH`, `oxygen`, `CODMN`, `NH4N`, `TN`, `TP`, `monitor_time`) VALUES "
                + "('%s', '%s', '%s', '%s', %s, %s, %s, %s, %s, %s, %s, %s, '%s');";
            for (int i = 0; i < read.size(); i++) {

                List<Object> line = read.get(i);
                if (line.size() < 17) {
                    continue;
                }

                if (line.get(0) == null) {
                    continue;
                }
                String index = line.get(0).toString().trim();
                try {
                    Integer.parseInt(index);
                } catch (Exception e) {
                    continue;
                }

                String code = getColumn(line, 1);
                String level = getColumn(line, 7);
                String flow = getColumn(line, 8);
                String flowDirect = getColumn(line, 9);
                String depth = getBigDecimal(line, 10);
                String temp = getBigDecimal(line, 11);
                String ph = getBigDecimal(line, 12);
                String oxygen = getBigDecimal(line, 13);
                String codmn = getBigDecimal(line, 14);
                String nh4n = getBigDecimal(line, 15);
                String tn = getBigDecimal(line, 16);
                String tp = getBigDecimal(line, 17);
                String monitorTime = getColumn(line, 6);
                if (StringUtils.isBlank(monitorTime)) {
                    continue;
                }
                try {
                    Date parseDate = DateUtils.parseDate(monitorTime, "yyyy.MM.dd");
                    monitorTime = DateUtil.format(parseDate, "yyyy-MM-dd HH:mm:ss");
                } catch (Exception e) {
                    continue;
                }

                String sql = String.format(
                    format,
                    code, level,flow, flowDirect, depth, temp, ph, oxygen, codmn, nh4n, tn, tp, monitorTime);
                rs.add(sql);
//                System.out.println(sql);
            }
        }

        return rs;
    }

    public List<String> time_env_transectInsert2(String filePath){
        List<String> rs = new ArrayList<>();

        for (int j = 0; j < 3; j++) {

            ExcelReader reader = ExcelUtil.getReader(filePath, j);
            List<List<Object>> read = reader.read(2);
            String format = "INSERT INTO `taicang_water`.`time_transect_quality_data`(`transect_code`, `water_level`, `flow`, `flow_direct`, `depth`, `temp`, `PH`, `oxygen`, `CODMN`, `NH4N`, `TN`, `TP`, `monitor_time`) VALUES "
                + "('%s', '%s', %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, '%s');";
            for (int i = 0; i < read.size(); i++) {

                List<Object> line = read.get(i);
                if (line.size() < 13) {
                    continue;
                }

                String index = line.get(0).toString().trim();
                try {
                    Integer.parseInt(index);
                } catch (Exception e) {
                    continue;
                }

                String code = getColumn(line, 1);
                String level = getColumn(line, 7);
                String flow = "null";
                String flowDirect = "null";
                String depth = "null";
                String temp = "null";
                String ph = getBigDecimal(line, 8);
                String oxygen = getBigDecimal(line, 9);
                String codmn = getBigDecimal(line, 10);
                String nh4n = getBigDecimal(line, 11);
                String tn = getBigDecimal(line, 12);
                String tp = getBigDecimal(line, 13);
                String monitorTime = getColumn(line, 6);
                if (StringUtils.isBlank(monitorTime)) {
                    continue;
                }
                try {
                    Date parseDate = DateUtils.parseDate(monitorTime, "yyyy.MM.dd");
                    monitorTime = DateUtil.format(parseDate, "yyyy-MM-dd HH:mm:ss");
                } catch (Exception e) {
                    continue;
                }

                String sql = String.format(
                    format,
                    code, level,flow, flowDirect, depth, temp, ph, oxygen, codmn, nh4n, tn, tp, monitorTime);
                rs.add(sql);
//                System.out.println(sql);
            }
        }

        return rs;
    }

    public  String getColumn(List<Object> line, int i) {
        if (line.size()-1 < i) {
            return "";
        }
        Object o = line.get(i);
        if (o == null) {
            return "";
        }
        return o.toString().trim();
    }

    public String getBigDecimal(List<Object> line, int i) {
        if (line.size()-1 < i) {
            return "null";
        }
        Object o = line.get(i);
        if (o == null) {
            return "null";
        }
        BigDecimal val = null;
        try {
            val = new BigDecimal(o.toString().trim());
            return val.toString();
        } catch (Exception e) {
            return "null";
        }
    }
}
