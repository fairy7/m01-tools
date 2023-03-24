package tools.coffee;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import tools.coffee.utils.ReadLineUtils;

/**
 * TTT
 *
 * @author: hyr
 * @date: 2022-07-29
 **/
@Slf4j
public class TTT {



    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            log.info(" {}", new Random().nextDouble());
//        }
//        getBeginAndEndOfTheWeek();



        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(calendar.getTime());
        calendar.add(Calendar.MONTH, 1);
        System.out.println(calendar.getTime());

        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(calendar.getTime());
        calendar.add(Calendar.YEAR, 1);
        System.out.println(calendar.getTime());

        System.out.println("--------------------------------------------");
        String start = "2022-10-17";
        String calc = "2022-12-05";
        try {
            Date s = DateUtils.parseDate(start, "yyyy-MM-dd");
            Date e = DateUtils.parseDate(calc, "yyyy-MM-dd");
//            Calendar s = DateUtil.calendar(DateUtils.parseDate(start, "yyyy-MM-dd"));
            log.info("day  start : {}", (e.getTime() - s.getTime()) /24/60/60/1000);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String url = "http://localhost:9200/file/file-download?fileId=87";
        log.info("{}", url.lastIndexOf("/"));
        log.info("{}", url.substring(0 , url.lastIndexOf("/") + 1));
        log.info("{}", url.substring(url.lastIndexOf("/") + 1));
        log.info("{}", url.indexOf("https://"));

    }
    
    public static List<String> times() {
        Calendar calendar = Calendar.getInstance();

        int i = calendar.get(Calendar.MINUTE);
        int i1 = i / 10 * 10;
        calendar.set(Calendar.MINUTE, i1);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);

        return null;
    }
    public static void de() {
        String s = "1111";
        byte[] bytes = s.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
        }
    }

    public static void de(long a, long b) {
        if (b<=0) {
            return;
        }
        BigDecimal bigDecimal = new BigDecimal((float) a * 100 / b).setScale(0, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal);


    }

    public static Date[] getBeginAndEndOfTheWeek() {
        Date time = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.WEEK_OF_YEAR, - 1);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date start = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 6);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date end = calendar.getTime();

        System.out.println(start);
        System.out.println(end);
        return null;
    }

    private static List<Map<String, Object>> listMap() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("one", i);
            map.put("two", "str" + i);
            list.add(map);
        }
        return list;
    }

    static class En {

        private String name;
        private int order;

        @Override
        public String toString() {
            return "En{" +
                "name='" + name + '\'' +
                ", order=" + order +
                '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public En(String name, int order) {
            this.name = name;
            this.order = order;
        }
    }

    private static void sortT() {
        List<En> list = Arrays.asList(new En("1", 1)
            , new En("2", 2)
            , new En("9", 9),
            new En("8", 8));
        log.info("before : {}", list);
        list.sort(Comparator.comparing(En::getOrder).reversed());
        log.info("after : {}", list);

    }
}
