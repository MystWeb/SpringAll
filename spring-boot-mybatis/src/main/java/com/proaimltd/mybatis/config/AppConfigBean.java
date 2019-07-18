package com.proaimltd.mybatis.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: ziming.xing
 * @date: 2019/7/18 21:54
 */
/*@ConfigurationProperties*/
@Configuration(value = "appConfigBean")
@PropertySource("classpath:env/${spring.profiles.active}.properties")
@Getter
@ToString
public class AppConfigBean {

    //-------------------- jdbc datasource1 config read --------------------
    @Value("${jdbc.datasource.url1}")
    private String jdbcUrl1;
    @Value("${jdbc.datasource.driverClassName1}")
    private String jdbcDriverclassname1;
    @Value("${jdbc.datasource.username1}")
    private String jdbcUsername1;
    @Value("${jdbc.datasource.password1}")
    private String jdbcPassword1;

    //-------------------- jdbc datasource2 config write --------------------
    @Value("${jdbc.datasource.url2}")
    private String jdbcUrl2;
    @Value("${jdbc.datasource.driverClassName2}")
    private String jdbcDriverclassname2;
    @Value("${jdbc.datasource.username2}")
    private String jdbcUsername2;
    @Value("${jdbc.datasource.password2}")
    private String jdbcPassword2;

    //-------------------- druid config write --------------------
    @Value("${druid.filters:stat}")
    private String druidFilters;
    @Value("${jdbc.initialSize:10}")
    private Integer jdbcInitialSize;
    @Value("${jdbc.maxActive:50}")
    private Integer jdbcMaxActive;
    @Value("${jdbc.minIdle:10}")
    private Integer jdbcMinIdle;

    @Value("${jdbc.testConnection.query}")
    private String jdbcConnectionTestQuery;
    @Value("${jdbc.connectionTimeout}")
    private Long jdbcConnectionTimeout;
    @Value("${jdbc.clientIdleTimeout}")
    private Long jdbcIdleTimeout;
    @Value("${jdbc.maxLifetime}")
    private Long jdbcMaxLifetime;
    @Value("${jdbc.maxPoolSize}")
    private Integer jdbcMaxPoolSize;

    //---------------- redis config ----------------------
    /*@Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private Integer redisPort;
    @Value("${spring.redis.password}")
    private String redisPassword;
    @Value("${spring.redis.database}")
    private Integer redisDatabase;
    @Value("${spring.redis.maxIdle}")
    private Integer redisMaxIdle;
    @Value("${spring.redis.maxTotal}")
    private Integer redisMaxTotal;
    @Value("${spring.redis.timeout}")
    private Long redisTimeout;*/

    //secrutiy
    /*@Value("${security.jwt.secure.key}")
    private String jwtSecureKey;
    @Value("${security.jwt.expiration.seconds}")
    private Long jwtExpirationSeconds;
    @Value("${security.authenticated.urls}")
    private String[] authenticatedUrls;
    @Value("${security.jwt.remember.seconds}")
    private Long rememberMeSeconds;
    @Value("${security.userinfo.desensitization}")
    private String userInfoDesensitization;*/


    //thread pool
    @Value("${executor.pool.name.format}")
    private String poolNameFormat;
    @Value("${executor.pool.size}")
    private int poolSize;
    @Value("${executor.pool.idle}")
    private int poolIdle;
    @Value("${executor.pool.queue.size}")
    private int poolQueueSize;
    @Value("${executor.pool.keep.alive.time}")
    private long keepAliveTime;
}
