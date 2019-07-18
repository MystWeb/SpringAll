/*
 * © Copyright Process Asset Integration and Management Limited 2015 - 2019.
 * All rights reserved.
 */

package com.proaimltd.mybatis.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author: ziming.xing
 * @date: 2019/7/11 14:57
 */
@Configuration
public class ThreadPoolConfig {

    @Autowired
    private AppConfigBean appConfigBean;


    @Bean
    public ExecutorService getExecutorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public ThreadFactory threadFactoryBuilder() {
        ThreadFactoryBuilder threadFactory = new ThreadFactoryBuilder();
        threadFactory.setNameFormat("pool-%d");
        return threadFactory.build();
    }

    @Bean(name = "executorServicePool")
    public ExecutorService executorServicePool() {
        return new ThreadPoolExecutor(appConfigBean.getPoolIdle(),
                appConfigBean.getPoolSize(),
                appConfigBean.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(appConfigBean.getPoolQueueSize()),
                threadFactoryBuilder(),
                new ThreadPoolExecutor.AbortPolicy());
    }
}
