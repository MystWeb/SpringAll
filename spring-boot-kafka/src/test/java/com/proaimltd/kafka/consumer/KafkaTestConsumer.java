package com.proaimltd.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import static com.proaimltd.kafka.constants.KafkaConstants.TOPIC_INTERNAL;

/**
 * @author: ziming.xing
 * @date: 2019/8/27 10:21
 */
@Slf4j
@Component
public class KafkaTestConsumer {
    public static final String TEST_TOPIC = "topic.quick.demo";

    // 简单消费者
    @KafkaListener(topics = TEST_TOPIC)
    public void consumer1_1(ConsumerRecord<String, Object> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, Consumer consumer) {
        log.info("消费者收到消息:" + record.value() + "; topic:" + topic);
        /*
         * 如果需要手工提交异步 consumer.commitSync();
         * 手工同步提交 consumer.commitAsync()
         */
    }
}
