package www.m01.tools.kafka.controller;

import cn.hutool.core.date.DateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * TestTask
 *
 * @author: hyr
 * @date: 2022-07-19
 **/
@Component
@EnableScheduling
@Slf4j
public class TestTask {

//    @Scheduled(cron = "0/5 * * * * ?")
//    public void run01() {
//        log.info("01 : {}", DateUtil.format(new Date(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
//    }
//
//    @Scheduled(cron = "*/5 * * * * ?")
//    public void run02() {
//        log.info("02 : {}", DateUtil.format(new Date(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
//    }

}
