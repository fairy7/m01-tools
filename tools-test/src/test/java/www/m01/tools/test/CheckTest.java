package www.m01.tools.test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * CheckTest
 *
 * @author: hyr
 * @date: 2022-08-03
 **/
@Slf4j
public class CheckTest {

    public static void main(String[] args) {
        test01();
    }

    private static Executor executor = Executors.newFixedThreadPool(40);

    private static void test01() {
        CheckService checkService = new CheckService();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(() ->{
                checkService.setThreadNum(finalI);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("ThreadNum, set : {}, get : {}", finalI, checkService.getThreadNum());
            }).start();
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("");
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(() ->{
                checkService.setCommonNum(finalI);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("CommonNum, set : {}, get : {}", finalI, checkService.getCommonNum());
            }).start();
        }
    }

}
