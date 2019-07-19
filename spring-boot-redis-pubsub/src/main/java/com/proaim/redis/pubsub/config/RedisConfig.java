package com.proaim.redis.pubsub.config;

import com.proaim.redis.pubsub.listener.ProMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author: ziming.xing
 * @date: 2019/7/19 14:39
 */
@Slf4j
@Configuration
@EnableCaching // 开启缓存
public class RedisConfig extends CachingConfigurerSupport {
    @Value("${redis.pubsub.channel.name}")
    private String channel;

    /**
     * 使用默认的工厂初始化redis操作String模板
     */
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    /**
     * 使用RedisConnection工厂初始化redis操作String模板
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setDefaultSerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }


    /**
     * 指定通道名称并创建通道
     */
    @Bean
    public ChannelTopic channelTopic() {
        return ChannelTopic.of(channel);
    }

    /**
     * 利用反射来创建监听到消息之后的执行方法
     *
     * @param proMessageListener 消息监听器
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(ProMessageListener proMessageListener) {
        return new MessageListenerAdapter(proMessageListener);
    }

    /**
     * 初始化监听器
     *
     * @param lettuceConnectionFactory LettuceConnection连接Redis
     * @param channelTopic             消息通道
     * @param messageListenerAdapter   消息监听适配器
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(LettuceConnectionFactory lettuceConnectionFactory, ChannelTopic channelTopic, MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(lettuceConnectionFactory);
        // 配置监听通道   channelTopic：通道的名字
        container.addMessageListener(messageListenerAdapter, channelTopic);
        log.info("初始化监听成功，监听通道：【" + channelTopic.getTopic() + "】");
        return container;
    }

    /**
     * 缓存管理器
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //设置CacheManager的值序列化方式为json序列化
        RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(jsonSerializer);
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(pair);
        //设置默认过期时间是7天
        defaultCacheConfig.entryTtl(Duration.ofDays(7));
        //初始化RedisCacheManager
        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
    }
}
