package www.m01.tools.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import www.m01.tools.kafka.constants.TopicConstants;

/**
 * TestController
 *
 * @author: hyr
 * @date: 2022-07-12
 **/
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/send")
    public String msgSend(String key, String msg) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(
                    TopicConstants.TOPIC_TEST, j + "-" + msg + System.currentTimeMillis());
                send.addCallback(new SuccessCallback<SendResult<String, String>>() {
                    @Override
                    public void onSuccess(SendResult<String, String> result) {
                        log.info("success! {}", result);
                    }
                }, new FailureCallback() {
                    @Override
                    public void onFailure(Throwable ex) {
                        log.error("error!", ex);
                    }
                });
            }
        }
        return "success";
    }


}
