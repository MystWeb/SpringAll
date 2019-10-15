package com.proaimltd.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.proaimltd.kafka.constants.KafkaConstants.TOPIC_GROUP;


/**
 * @author: ziming.xing
 * @date: 2019/8/26 20:57
 * @description : kafka 配置类
 */
@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic groupTopic() {
        // 指定主题名称，分区数量，和复制因子
        return new NewTopic(TOPIC_GROUP, 10, (short) 2);
    }
}
