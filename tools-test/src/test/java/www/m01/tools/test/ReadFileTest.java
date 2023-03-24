package www.m01.tools.test;

import java.io.File;
import lombok.extern.slf4j.Slf4j;

/**
 * ReadFileTest
 *
 * @author: hyr
 * @date: 2022-08-15
 **/
@Slf4j
public class ReadFileTest {

    public static void main(String[] args) {

        File dir = new File("D:\\db");
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
//            log.info("create database if not exists {} character set utf8  collate utf8_general_ci;", file.getName().substring(0, file.getName().indexOf(".sql")));
            System.out.println(String.format("create database if not exists %s character set utf8  collate utf8_general_ci;", file.getName().substring(0, file.getName().indexOf(".sql"))));
        }

    }

}
