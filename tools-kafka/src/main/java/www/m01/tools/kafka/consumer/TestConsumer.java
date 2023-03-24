package www.m01.tools.kafka.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.util.Date;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import www.m01.tools.kafka.constants.TopicConstants;

/**
 * TestConsumer
 *
 * @author: hyr
 * @date: 2022-07-12
 **/
@Service
@Slf4j
public class TestConsumer {

    private static final String GROUP_ID = "new_c20220718";

//    @KafkaListener(id = "id-consumer01", topics = {TopicConstants.TOPIC_TEST}, groupId = GROUP_ID)
//    public void consumer01(ConsumerRecord<String, String> record, Acknowledgment ack)
//        throws Exception {
//        if (record.value().startsWith("q")) {
//            throw new Exception("error");
//        }
////        log.info("{} : {} : {} : {}",record.value().split("-")[0], Thread.currentThread().getName(), System.currentTimeMillis(), record.value());
//        ack.acknowledge();
//    }


    @KafkaListener(id = "id-tai-consumer01", topics = "taicang_taicang",  groupId = "test-taicang")
    public void consumer01(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        try {
            OriginDeviceData data = new OriginDeviceData();
            JSONObject req = JSON.parseObject((String) JSON.parse(value));
            String dataId = req.getString("dataId");
            if (StringUtils.isBlank(dataId)) {
                dataId = UUID.randomUUID().toString().replaceAll("-", "");
            }
            data.setDataId(dataId);
            data.setDeviceId(req.getString("deviceId"));
            data.setTime(new Date());
            String serviceId = req.getString("serviceId");
            JSONObject d = req.getJSONObject("data");
            if (StringUtils.isNotBlank(serviceId)) {
                JSONObject json = new JSONObject();
                json.put("serviceId", serviceId);
                json.put("data", req.getJSONObject("data"));
                d = json;
            }
            data.setData(d.toJSONString());
            if (data.getDeviceId().contains("MHC2023011")) {
                log.info("\n{}\n{}", data.getDeviceId(), JSON.toJSONString(data, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteDateUseDateFormat));
            } else {
                log.info("\n{}\n{}", data.getDeviceId(), JSON.toJSONString(data, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteDateUseDateFormat));
            }
        } catch (Exception e) {
            log.error("iot平台数据获取失败, data : {}", record.value(), e);
        } finally {
            ack.acknowledge();
        }
    }

//    @KafkaListener(topics = {TopicConstants.TOPIC_TEST}, groupId = GROUP_ID)
//    public void consumer02(ConsumerRecord<String, String> record, Acknowledgment ack) {
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        log.info("{} : {} : {}", "consumer02", System.currentTimeMillis(), record.value());
//        ack.acknowledge();
//    }
//
//    @KafkaListener(topics = {TopicConstants.TOPIC_TEST}, groupId = GROUP_ID)
//    public void consumer03(ConsumerRecord<String, String> record, Acknowledgment ack) {
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        log.info("{} : {} : {}", "consumer03", System.currentTimeMillis(), record.value());
//        ack.acknowledge();
//    }
}
