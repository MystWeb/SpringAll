# Spring Boot整合Redis

在程序中可以使用缓存的技术来节省对数据库的开销。Spring Boot对缓存提供了很好的支持，我们几乎不用做过多的配置即可使用各种缓存实现。这里主要介绍平日里个人接触较多的Redis缓存实现。

`spring-boot`也对nosql数据库进行了封装，比如这里要讲的`spring-boot-redis`。

## 项目基础环境

### 后端

- 基础框架：Spring Boot 2.1.3.RELEASE

### 开发环境

- 语言： JDK1.8
- IDE： IDEA 2018.3
- 依赖管理： Maven3
- 缓存： Redis5
- Linux系统：CentOS 7

## 起步

首先我们需要在本地或者自己的服务器上安装Redis程序

## 安装Docker

### 查看CentOS版本

升级软件包及内核（选做）(Docker要求CentOS系统的内核版本高于3.10)

```shell
uname -r
```

### Docker安装

```shell
yum install docker
```

### Docker开机自启动/关闭

```shell
systemctl enable docker
systemctl disable docker
```

### Docker启动/关闭/状态

```shell
service docker start/stop/status
```

### 启动Redis

#### 拉取/下载Redis

```
docker search redis
docker pull redis
```

#### 启动Redis镜像

```shell
docker run --name redis5 -d -p 6379:6379 redis
```

#### 查看Redis版本

```shell
docker exec -it redis5 bash
redis-server -v
```

#### 进入Redis控制台

```shell
redis-cli
```

#### [Redis可视化工具：Redis Desktop Manager](https://redisdesktop.com/)

`Error：Connection error: The proxy type is invalid for this operation`

请禁用代理或将系统代理设置添加到全局设置

#### [Redis 客户端命令](http://redis.cn/commands.html)

### POM导入依赖

```java
<!--redis-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

### 应用配置文件

#### application.properties格式配置

```java
spring.datasource.url=jdbc:mysql://192.168.100.130:3306/springboot_cache
spring.datasource.username=root
spring.datasource.password=proaim
#Spring Boot自动判断Url选择JDBC驱动
#spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

#开启驼峰命名匹配规则
mybatis.configuration.map-underscore-to-camel-case=true

#开启日志
logging.level.com.proaim.cache.mapper=debug

#开启debug模式看生效的缓存类
debug=true

#Redis配置
spring.redis.host=192.168.100.130
spring.redis.port=6379
```

#### application.yml格式配置

```yaml
debug: false
logging:
  level:
    com:
      proaim:
        cache:
          mapper: debug
mybatis:
  configuration:
    map-underscore-to-camel-case: true
spring:
  datasource:
    password: proaim
    url: jdbc:mysql://192.168.100.130:3306/springboot_cache
    username: root
  redis:
    host: 192.168.100.130
    port: 6379
```

接着在Spring Boot入口类中加入`@EnableCaching`注解开启缓存功能：

```java
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
```

```
@MapperScan("com.proaim.cache.mapper")
@SpringBootApplication
// @EnableCaching 开启基于缓存的注解
@EnableCaching
public class SpringBootCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheApplication.class, args);
    }

}
```

在Spring Boot中可使用的缓存注解有：

### 缓存注解

1. `@CacheConfig`：主要用于配置该类中会用到的一些共用的缓存配置。在这里`@CacheConfig(cacheNames = "emp")`：配置了该数据访问对象中返回的内容将存储于名为emp的缓存对象中，我们也可以不使用该注解，直接通过`@Cacheable`自己配置缓存集的名字来定义；
2. `@Cacheable`：配置了queryStudentBySno函数的返回值将被加入缓存。同时在查询时，会先从缓存中获取，若不存在才再发起对数据库的访问。该注解主要有下面几个参数：
   - `value`、`cacheNames`：两个等同的参数（cacheNames为Spring 4新增，作为value的别名），用于指定缓存存储的集合名。由于Spring 4中新增了`@CacheConfig`，因此在Spring 3中原本必须有的value属性，也成为非必需项了；
   - `key`：缓存对象存储在Map集合中的key值，非必需，缺省按照函数的所有参数组合作为key值，若自己配置需使用SpEL表达式，比如：`@Cacheable(key = "#p0")`：使用函数第一个参数作为缓存的key值，更多关于SpEL表达式的详细内容可参考<https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#cache>；
   - `condition`：缓存对象的条件，非必需，也需使用SpEL表达式，只有满足表达式条件的内容才会被缓存，比如：`@Cacheable(key = "#p0", condition = "#p0.length() < 3")`，表示只有当第一个参数的长度小于3的时候才会被缓存；
   - `unless`：另外一个缓存条件参数，非必需，需使用SpEL表达式。它不同于condition参数的地方在于它的判断时机，该条件是在函数被调用之后才做判断的，所以它可以通过对result进行判断；
   - `keyGenerator`：用于指定key生成器，非必需。若需要指定一个自定义的key生成器，我们需要去实现`org.springframework.cache.interceptor.KeyGenerator`接口，并使用该参数来指定；
   - `cacheManager`：用于指定使用哪个缓存管理器，非必需。只有当有多个时才需要使用；
   - `cacheResolver`：用于指定使用那个缓存解析器，非必需。需通过org.springframework.cache.interceptor.CacheResolver接口来实现自己的缓存解析器，并用该参数指定；
3. `@CachePut`：配置于函数上，能够根据参数定义条件来进行缓存，其缓存的是方法的返回值，它与`@Cacheable`不同的是，它每次都会真实调用函数，所以主要用于数据新增和修改操作上。它的参数与`@Cacheable`类似，具体功能可参考上面对`@Cacheable`参数的解析；
4. `@CacheEvict`：配置于函数上，通常用在删除方法上，用来从缓存中移除相应数据。除了同`@Cacheable`一样的参数之外，它还有下面两个参数：
   - `allEntries`：非必需，默认为false。当为true时，会移除所有数据；
   - `beforeInvocation`：非必需，默认为false，会在调用方法之后移除数据。当为true时，会在调用方法之前移除数据。

### 缓存实现

要使用上Spring Boot的缓存功能，还需要提供一个缓存的具体实现。Spring Boot根据下面的顺序去侦测缓存实现：

- Generic
- JCache (JSR-107)
- EhCache 2.x
- Hazelcast
- Infinispan
- Redis
- Guava
- Simple

除了按顺序侦测外，我们也可以通过配置属性spring.cache.type来强制指定。

接下来主要介绍基于Redis和Ehcache的缓存实现。

```
@Slf4j
// 抽取缓存的公共配置
@CacheConfig(cacheNames = "emp")
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存；以后相同的数据直接会从缓存获取，不再调用方法；
     * CacheManager管理多个Cache组件，对缓存的真正CRUD操作在Cache组件中，每一个缓存组件有自己唯一一个名字；
     * 原理：
     *      1、自动配置类：CacheAutoConfiguration
     *      2、缓存的配置类：
     *      org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
     *      3、默认生效配置类：SimpleCacheConfiguration
     *      4、给容器中注册了一个cacheManager:ConcurrentMapCacheManager
     *      5、可以获取和创建ConcurrentMapCache类型的缓存组件；它的作用将数据保存在ConcurrentMap中；
     *      运行流程：
     *      @Cacheable：
     *      1、方法运行之前，先去查询Cache（缓存组件），按照cacheNames指定的名字获取；
     *      （CacheManager先获取响应的缓存）第一次获取缓存结果如果没有Cache组件会自动创建。
     *      2、去Cache中查找缓存的内容，使用一个key，默认就是方法的参数；
     *      key是按照某种策略生成的；默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key；
     *          SimpleKeyGenerator生成的key的默认策略；
     *              如果没有参数：key = new SimpleKey();
     *              如果有一个参数：key = 参数的值;
     *              如果有多个参数：key = new SimpleKey(params);
     *      3、没有查到缓存就调用目标方法；
     *      4、将目标方法返回的结果，放进缓存中
     *
     *      @Cacheable标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数的值作为key去查询缓存，
     *      如果没有就运行这个方法并将结果放入缓存；以后再来调用就可以直接使用缓存中的数据；
     *
     *      核心：
     *      1、使用CacheManager【ConcurrentMapCacheManager】按照名字得到Cache【ConcurrentMapCache】组件
     *      2、key使用KeyGenerator生成的，默认是SimpleKeyGenerator
     *
     * 属性：
     *      cacheNames/value：指定缓存的名字，将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存；
     *      key：缓存数据使用的key；可以用它来指定。默认是使用方法的参数的值 1——方法的返回值
     *              编写SpEL：#id（参数id的值） #a0 #p0 #root.args[0]
     *
     *      keyGenerator：key的生成器；可以自己指定key的生成器的组件id
     *              key/keyGenerator：二选一使用
     *      cacheManager：指定缓存管理器；或者cacheResolver指定获取解析器
     *      condition：指定符合条件的情况下才能缓存；
     *              condition = "#id>0" / "#a0>1" ：第一个参数的值 > 1时才进行缓存
     *      unless：否定缓存；当unless指定的条件为true，方法的返回值就不会被缓存；可以获取到结果进行判断
     *              unless = "#result == null"
     *              unless = "#p0 == 2" ：如果第一个参数的值是2，结果不缓存
     *      sync：是否使用异步模式
     */
    @Cacheable(cacheNames = "emp"/*, keyGenerator = "keyGenerator", condition = "#a0>1", unless = "#p0==2"*/)
    @Override
    public Employee getEmpById(Integer id) {
        log.info("查询" + id + "号员工");
        return employeeMapper.getEmpById(id);
    }

    /**
     * @Cacheing：定义复杂的缓存规则
     */
    @Caching(
            cacheable = {
                    @Cacheable(/*value = "emp", */key = "#lastName")
            },
            put = {
                    @CachePut(/*value = "emp", */key = "#result.id"),
                    @CachePut(/*value = "emp", */key = "#result.email")
            }
    )
    @Override
    public Employee getEmpByName(String lastName) {
        log.info("查询员工：" + lastName);
        return employeeMapper.getEmpByName(lastName);
    }

    /**
     * @CachePut：既调用方法，又更新缓存数据，同步更新缓存；
     * 修改了数据库的某个数据，同时又更新缓存；
     * 运行时机：
     *  1、先调用目标方法
     *  2、将目标方法的结果缓存起来
     *
     * 测试步骤：
     *  1、查询1号员工；查询的结果会放入缓存中
     *  2、以后查询还是之前的结果
     *  3、更新1号员工；[LastName：zhangsan；gender：0]
     *      将方法的返回值也放进缓存了；
     *      key：传入的employee对象   Value：返回的employee对象；
     *  4、查询1号员工
     *      应该是更新后的员工；
     *          key = "#employee.id" ：使用传入的参数的员工id；
     *          key = "#result.id" ：使用返回后的id
     *          @Cacheable不可以使用#result
     *      为什么是没更新前的？【1号员工没有在缓存中更新】
     *
     */
    @Override
    @Transactional
    @CachePut(/*value = "emp", */key = "#result.id")
    public Employee updateEmp(Employee employee) {
        log.info("更新员工：" + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * @CacheEvict：缓存清除
     * key = "#id"：指定要清除的数据
     * allEntries = true：指定清除这个缓存中所有的数据
     * beforeInvocation = false：缓存的清除是否在方法之前执行
     *      默认false，在方法执行之后执行；如果出现异常缓存就不会被清除
     * beforeInvocation = true：
     *      代表清除缓存操作在方法之前执行，无论方法是否出现异常，缓存都清除
     */
    @Override
    @Transactional
    @CacheEvict(/*value = "emp", */beforeInvocation = true)
    public void deleteEmpById(Integer id) {
        log.info("删除" + id + "号员工");
        /*employeeMapper.deleteEmpById(id);*/
        int i = 10 / 0;
    }

    @Override
    @Transactional
    public Employee insertEmployee(Employee employee) {
        log.info("添加员工：" + employee);
        employeeMapper.insertEmployee(employee);
        return employee;
    }
}
```

### Redis配置类

```java
package com.proaim.cache.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.time.Duration;

@Configuration
@EnableCaching // 开启Springboot缓存
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 自定义缓存key的生成策略。默认的生成策略是看不懂的(乱码内容) 通过Spring 的依赖注入特性进行自定义的配置注入并且此类是一个配置类可以更多程度的自定义配置
     */
    /*@Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }*/

    /**
     * 自定义序列化缓存模板
     */
    @Bean
    @ConditionalOnMissingBean(
            name = {"redisTemplate"}
    )
    public RedisTemplate<String, Object> redisCacheTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        // 配置redisTemplate
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer()); // key序列化
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer()); // value序列化
        template.setHashKeySerializer(new StringRedisSerializer()); // Hash key序列化
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer()); // Hash value序列化
        template.setConnectionFactory(redisConnectionFactory);
        return template;
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
```

