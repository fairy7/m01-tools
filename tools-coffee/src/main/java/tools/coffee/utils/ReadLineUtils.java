package tools.coffee.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

/**
 * ReadLineUtils
 *
 * @author: hyr
 * @date: 2022-11-03
 **/
@Slf4j
public class ReadLineUtils {

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
        List<String> latlon = read("latlon");
        latlon.forEach(line -> {
            String[] s = line.split(" ");
            String format = String.format(
                "update syzhhbdc_standard.wry_feature_event set latitude = '%s', longitude = '%s' where pkid = '%s';",
                s[2], s[1], s[0]);
            System.out.println(format);
        });
    }

}
