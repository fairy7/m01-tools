package tools.coffee.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

/**
 * ReadLineUtils
 *
 * @author: hyr
 * @date: 2022-11-03
 **/
@Slf4j
public class ReadLineSqlUtils {

    public static List<String> read(String path) {
        try (
            InputStream is = new ClassPathResource(path).getInputStream();
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader bfr = new BufferedReader(isr)) {

            List<String> lines = new ArrayList<>();
            String line;
            while ((line = bfr.readLine()) != null) {
                lines.add(line);
//                log.info("one line : {}", line);
            }
            return lines;
        } catch (Exception e) {
            log.error("error!", e);
            return null;
        }
    }

    public static void main(String[] args) {
        List<String> lines = read("tt10.sql");
        System.out.println(lines.get(0));
//        String line = lines.get(0);
//        line = line.substring(1, line.length() -1);
//        String[] split = line.split("\",\"");
//        System.out.println(Arrays.asList(split));
//        List<String> list = Arrays.asList(line.split("\",\""));
//        String lineSql = String.format(
//            "INSERT INTO `taicang_water`.`time_sensor_data`(`origin_data_id`, `monitor_time`, `device_code`, `sensor_code`, `origin_code`, `item_type`, `origin_value`, `adjust_value`, `if_available`, `if_warn`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ("
//                + "'%s', '%s', '%s', '%s', %s, '%s', %s, %s, %s, %s, '%s', '%s', %s, %s, %s);"
//            , list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6),
//            list.get(7), list.get(8), list.get(9), list.get(10), list.get(11), list.get(12),
//            list.get(13), list.get(14), list.get(15));

        List<String> content = new ArrayList<>();
        lines.forEach(line -> {
            line = line.substring(1, line.length() -1);
            List<String> list = Arrays.asList(line.split("\",\""));
            String lineSql = String.format(
                "INSERT INTO `taicang_water`.`time_sensor_data`(`origin_data_id`, `monitor_time`, `device_code`, `sensor_code`, `origin_code`, `item_type`, `origin_value`, `adjust_value`, `if_available`, `if_warn`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ("
                    + "'%s', '%s', '%s', '%s', %s, '%s', %s, %s, %s, %s, '%s', '%s', %s, %s, %s);"
                , list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6),
                list.get(7), list.get(8), list.get(9), list.get(10), list.get(11), list.get(12),
                list.get(13), list.get(14), list.get(15));
            content.add(lineSql);
//            System.out.println(lineSql);
            try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("D:\\download\\tt10_format.sql", true));
            ) {
                bufferedWriter.append(lineSql).append("\n");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


    }

}
