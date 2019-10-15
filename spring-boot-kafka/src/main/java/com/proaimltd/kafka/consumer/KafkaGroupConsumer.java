package com.proaimltd.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import static com.proaimltd.kafka.constants.KafkaConstants.TOPIC_GROUP;

/**
 * 多个消费者群组可以共同读取同一个主题，彼此之间互不影响。
 *
 * @author: ziming.xing
 * @date: 2019/8/27 10:03
 */
@Slf4j
@Component
public class KafkaGroupConsumer {

    // 分组 1 中的消费者 1
    @KafkaListener(id = "consumer1-1", groupId = "group1", topicPartitions =
            {@TopicPartition(topic = TOPIC_GROUP, partitions = {"0", "1"})
            })
    public void consumer1_1(ConsumerRecord<String, Object> record) {
        log.info("consumer1-1 收到消息:" + record.value());
    }

    // 分组 1 中的消费者 2
    @KafkaListener(id = "consumer1-2", groupId = "group1", topicPartitions =
            {@TopicPartition(topic = TOPIC_GROUP, partitions = {"2", "3"})
            })
    public void consumer1_2(ConsumerRecord<String, Object> record) {
        log.info("consumer1-2 收到消息:" + record.value());
    }

    // 分组 1 中的消费者 3
    @KafkaListener(id = "consumer1-3", groupId = "group1", topicPartitions =
            {@TopicPartition(topic = TOPIC_GROUP, partitions = {"0", "1"})
            })
    public void consumer1_3(ConsumerRecord<String, Object> record) {
        log.info("consumer1-3 收到消息:" + record.value());
    }

    // 分组 2 中的消费者
    @KafkaListener(id = "consumer2-1", groupId = "group2", topics = TOPIC_GROUP)
    public void consumer2_1(ConsumerRecord<String, Object> record) {
        log.error("consumer2-1 收到消息:" + record.value());
    }
}
