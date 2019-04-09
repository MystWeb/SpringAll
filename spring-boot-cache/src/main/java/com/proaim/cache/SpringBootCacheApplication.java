package com.proaim.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 一、搭建基本环境
 * 1、导入数据库文件 创建出department、employee表
 * 2、创建JavaBean封装数据
 * 3、整合Mybatis操作数据库
 *      1.配置数据源
 *      2.使用注解版的MyBatis
 *          1·@MapperScan指定需要扫描的Mapper接口所在的包
 * 二、快速体验缓存
 *      步骤：
 *          1、开启基于缓存的注解
 *          @EnableCaching 开启基于缓存的注解
 *          2、标注缓存注解即可
 *          @Cacheable：主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
 *          @CacheEvict：清空缓存
 *          @CachePut：保证方法被调用，又希望结果被缓存
 * 默认使用的是ConcurrentMapCacheMapper==ConcurrentMapCache；将数据保存在ConcurrentMap<Object, Object>中；
 * 开发中使用缓存中间件；redis、memcached、ehcache；
 * 三、整合Redis作为缓存
 * Redis是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。
 * 1、使用Docker，安装Redis
 * 2、引入Redis的starter
 * 3、配置redis
 * 4、测试缓存
 *      原理：CacheManager===Cache 缓存组件来实际给缓存中存取数据
 *      (1)、引入Redis的Starter，容器中保存的是RedisCacheManager
 *      (2)、RedisCacheManager帮我们创建RedisCache来作为缓存组件，RedisCache通过操作Redis缓存数据
 *      (3)、默认保存数据 K - V 都是Object时，利用序列化保存；如何保存为Json
 *          1、引入了Redis的starter，CacheManager变为RedisCacheManager；
 *          2、默认创建的RedisCacheManager操作Redis的时候使用的是RedisCacheManager<Object, Object>
 *          3、RedisTemplate默认的序列化规则JdkSerializationRedisSerializer（classLoader）
 *      (4)、自定义RedisManager
 * */
@MapperScan("com.proaim.cache.mapper")
@SpringBootApplication
// @EnableCaching 开启基于缓存的注解
@EnableCaching
public class SpringBootCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheApplication.class, args);
    }

}