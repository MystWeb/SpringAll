package com.proaimltd.kafka.consumer;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proaimltd.kafka.model.Programmer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.proaimltd.kafka.constants.KafkaConstants.TOPIC_BEAN;

/**
 * @author: ziming.xing
 * @date: 2019/8/26 20:40
 */
@Slf4j
@Component
public class KafkaBeanConsumer {
    @Resource
    private ObjectMapper objectMapper;

    @KafkaListener(groupId = "beanGroup", topics = TOPIC_BEAN)
    public void consumer(ConsumerRecord<String, Object> record) {
        // com.fasterxml.jackson.databind.ObjectMappeJSON序列化及反序列化方式
        /*try {
            objectMapper.writeValueAsString(record.value().toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("消费者收到消息:" + objectMapper.convertValue(record.value().toString(),Programmer.class));*/
        // com.alibaba.fastjson.JSON序列化及反序列化方式
        /*JSON.toJSON(programmer).toString();*/
        log.info("消费者收到消息:" + JSON.parseObject(record.value().toString(), Programmer.class));
    }
}
