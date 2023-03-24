package www.m01.tools.kafka.sw;

import com.alibaba.fastjson.JSON;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author: hyr
 * @date: 2022-07-12
 **/
@RestController
@RequestMapping("/sw")
@Slf4j
public class SwDataSendController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public static final String TOPIC = "device_origin_data";

    @PostMapping("/send")
    public String msgSend(String dateStr, String[] deviceCodes) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(dateStr);
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        for (int j = 0; j < 15 * (60 / 10); j++) {
            ca.add(Calendar.MINUTE, 10);
            for (int i = 0; i < deviceCodes.length; i++) {
                String data = getData(ca.getTime(), deviceCodes[i]);
                kafkaTemplate.send(TOPIC, deviceCodes[i], data);
            }
        }
        return "success";
    }

    public static String encode(String str) {
        String sct = "";
        int length = str.length();
        for (int i = 0; i < length; i++) {
            sct += (char) (str.charAt(i) + 1);
        }
        return sct;
    }

    public String getData(Date monitorTime, String deviceCode) {
        List<ParseProtocolData> list = new ArrayList<>();
        list.add(new ParseProtocolData("dy", random(50, 10, 2), monitorTime, 0, "v", "电压"));
        list.add(new ParseProtocolData("ph", random(5, 4, 0), monitorTime, 1, "mol·L", "PH"));
        list.add(new ParseProtocolData("sw", random(20, 5, 2), monitorTime, 1, "psi", "水位"));
        list.add(new ParseProtocolData("ll", random(100, 20, 2), monitorTime, 1, "m3/h", "流量"));
        String data = JSON.toJSONString(list);
        log.info("dd : {}", encode(data));
        OriginDeviceData originDeviceData = new OriginDeviceData(
            null, deviceCode, encode(data), new Date());

        return JSON.toJSONString(originDeviceData);
    }

    private BigDecimal random(Integer origin, Integer mul, Integer scale) {
        BigDecimal bigDecimal = new BigDecimal(origin + mul * new Random().nextDouble());
        return bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

}
