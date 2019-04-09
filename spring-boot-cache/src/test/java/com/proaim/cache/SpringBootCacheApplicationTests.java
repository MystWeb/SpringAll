package com.proaim.cache;

import com.proaim.cache.entity.Employee;
import com.proaim.cache.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootCacheApplicationTests {
    @Resource
    private EmployeeMapper employeeMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate; // 操作K - V字符串

    @Autowired
    private RedisTemplate redisTemplate; // 操作K - V 都是Object对象类型

    @Resource
    private RedisTemplate<String, Object> redisCacheTemplate;

    /*@Autowired
    private RedisTemplate<String, Object> redisCacheTemplate;*/

    /**
     * Redis常见的五大数据类型
     * String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
     * stringRedisTemplate.opsForValue()[String（字符串）]
     * stringRedisTemplate.opsForList()[List（列表）]
     * stringRedisTemplate.opsForSet()[Set（集合）]
     * stringRedisTemplate.opsForHash()[Hash（散列）]
     * stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
     * <p>
     * stringRedisTemplate.opsForGeo()[Geo（地理位置）]
     */
    @Test

    public void testRedis01() {
        // 保存String（字符串）
        /*stringRedisTemplate.opsForValue().append("msg","Hello");*/
        // 打印Redis数据
        log.info(stringRedisTemplate.opsForValue().get("msg"));
        // 保存List（列表）
        stringRedisTemplate.opsForList().leftPush("mylist", "1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");
        stringRedisTemplate.opsForList().leftPush("mylist", "3");
    }

    // 测试保存对象
    @Test
    public void testRedis02() {
        Employee empById = employeeMapper.getEmpById(1);
        // 默认如果保存对象，使用JDK序列化机制，序列化后的数据保存到Redis中
        /*redisTemplate.opsForValue().set("emp-01", empById);*/
        /**
         * 1、将数据已Json的方式保存
         *  (1)自己将对象转换为Json
         *  (2)redisTemplate默认的序列化规则JdkSerializationRedisSerializer（classLoader）
         *  (3)创建RedisConfig，改变默认序列化规则
         */

        redisTemplate.opsForValue().set("emp-02", empById);
    }

    @Test
    public void contextLoads() {

    }

}
