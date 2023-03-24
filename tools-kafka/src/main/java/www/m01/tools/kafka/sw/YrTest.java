package www.m01.tools.kafka.sw;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TestJava
 *
 * @author: hyr
 * @date: 2022-07-07
 **/
public class YrTest {

    // 解密数据
    public String decode(String str) {
        String sct = "";
        int length = str.length();
        for (int i = 0; i < length; i++) {
            sct += (char) (str.charAt(i) - 1);
        }
        return sct;
    }

    public List<JSONObject> getData(String data) {
        List<JSONObject> result = new ArrayList<>();
        return JSON.parseObject(decode(data), List.class);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(random(1, 30, 0));
        }
    }
    private static BigDecimal random(Integer origin, Integer mul, Integer scale) {
        BigDecimal bigDecimal = new BigDecimal(origin + mul * new Random().nextDouble());
        return bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }
}

