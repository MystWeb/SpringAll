/*
 * © Copyright Process Asset Integration and Management Limited 2015 - 2019.
 * All rights reserved.
 */

package com.proaimltd.mybatis.config;

import com.proaimltd.mybatis.service.interceptor.HttpHeaderInterceptor;
import com.proaimltd.mybatis.service.interceptor.LoggerInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.proaimltd.mybatis.common.RouteConstants.URL_PREFIX;
import static com.proaimltd.mybatis.common.RouteConstants.URL_VERSION;

/**
 * web全局配置
 *
 * @author: ziming.xing
 * @date: 2019/7/11 14:57
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${cors.allowed.origins}")
    private String[] corsAllowedOrigins;

    @Value("${cors.allowed.methods}")
    private String[] corsAllowedMethods;

    @Value("${cors.max.age}")
    private long corsMaxAge;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(URL_PREFIX + URL_VERSION + "/**")
                .allowedOrigins(corsAllowedOrigins)
                .allowedMethods(corsAllowedMethods)
                .maxAge(corsMaxAge);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpHeaderInterceptor());
        registry.addInterceptor(new LoggerInterceptor());
    }
}
