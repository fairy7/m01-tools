package www.m01.tools.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.LoggingProducerListener;
import org.springframework.kafka.support.ProducerListener;

/**
 * KafkaConfig
 *
 * @author: hyr
 * @date: 2022-07-12
 **/
@Configuration
@Slf4j
public class KafkaConfig {

    @Bean
    public ProducerListener<Object, Object> kafkaProducerListener() {
        return new LoggingProducerListener(){
            @Override
            public void onSuccess(String topic, Integer partition, Object key, Object value,
                RecordMetadata recordMetadata) {
                log.info("success! {} : {} : {} : {}", topic, partition, key, value);
            }

            @Override
            public void onError(String topic, Integer partition, Object key, Object value,
                Exception exception) {
                log.error("error! {} : {} : {} : {}", topic, partition, key, value, exception);
            }
        };
    }

}
