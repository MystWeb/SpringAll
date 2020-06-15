package cn.myst.web.jaeger.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author ziming.xing
 * Create Time：2020/6/15
 */
@Configuration
public class CacheConfig {

    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> method.getReturnType().getSimpleName() + Arrays.asList(params).toString();
    }
}
