package com.proaim.redis.pubsub.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

/**
 * @author: ziming.xing
 * @date: 2019/7/19 14:44
 */
@Service
public class MessagePublisher {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic channelTopic;

    public MessagePublisher(RedisTemplate<String, Object> redisTemplate, ChannelTopic channelTopic) {
        this.redisTemplate = redisTemplate;
        this.channelTopic = channelTopic;
    }

    /**
     * 发布消息
     *
     * @param message 消息主题
     */
    public void publish(String message) {
        this.redisTemplate.convertAndSend(channelTopic.getTopic(), channelTopic.getTopic() + "：" + message);
    }
}
