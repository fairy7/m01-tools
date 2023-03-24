package www.m01.tools.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.util.CollectionUtils;

/**
 * DateTest
 *
 * @author: hyr
 * @date: 2022-07-18
 **/
public class DateTest {

    public static void main(String[] args) throws Exception{
        String dateStr = "2022-07-16 00:00:00";
        Integer interval = null;
        Integer hours = 16;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(dateStr);
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int intervalReal = interval == null ? 10 : interval;
        for (int j = 0; j <= hours * 60 / intervalReal; j++) {
                System.out.println(ca.getTime());
            ca.add(Calendar.MINUTE, intervalReal);
        }
    }
}
