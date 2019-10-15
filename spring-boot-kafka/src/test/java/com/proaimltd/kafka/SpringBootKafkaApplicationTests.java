package com.proaimltd.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.proaimltd.kafka.consumer.KafkaTestConsumer.TEST_TOPIC;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootKafkaApplicationTests {
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testTemplateSend() {
        //发送带有时间戳的消息
        /*kafkaTemplate.send(TEST_TOPIC, 0, System.currentTimeMillis(), "0", "send message with timestamp");*/

        //使用ProducerRecord发送消息
        /*ProducerRecord record = new ProducerRecord(TEST_TOPIC, "use ProducerRecord to send message");
        kafkaTemplate.send(record);*/

        //使用Message发送消息
        // 过时的方法
        Map map = new HashMap();
        map.put(KafkaHeaders.TOPIC, TEST_TOPIC);
        map.put(KafkaHeaders.PARTITION_ID, 0);
        map.put(KafkaHeaders.MESSAGE_KEY, 0);
        GenericMessage message = new GenericMessage("use Message to send message", new MessageHeaders(map));
        kafkaTemplate.send(message);
    }

}
