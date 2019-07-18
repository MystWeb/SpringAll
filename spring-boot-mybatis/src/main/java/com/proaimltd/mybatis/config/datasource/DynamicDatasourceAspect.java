/*
 * © Copyright Process Asset Integration and Management Limited 2015 - 2019.
 * All rights reserved.
 */

package com.proaimltd.mybatis.config.datasource;

import com.proaimltd.mybatis.annotation.TargetDatasource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: ziming.xing
 * @date: 2019/7/18 21:54
 */
@Slf4j
@Aspect
@Component
@Order(-1)
public class DynamicDatasourceAspect {
    @Before("@annotation(targetDatasource)")
    public void switchDatasource(JoinPoint point, TargetDatasource targetDatasource) {
        if (!DynamicDataSourceContextHolder.containDatasourceKey(targetDatasource.value())) {
            log.info("Datasource {} do not existed. use default datasource {} ", targetDatasource.value(), point.getSignature());
        } else {
            DynamicDataSourceContextHolder.setDatasourceKey(targetDatasource.value());
            log.info("Datasource switch to {} in method {}", DynamicDataSourceContextHolder.getDatasourceKey(), point.getSignature());
        }
    }

    @After("@annotation(targetDatasource)")
    public void restoreDatasource(JoinPoint point, TargetDatasource targetDatasource) {
        DynamicDataSourceContextHolder.clearDatasourceKey();
        log.info("Datasource restore to {} in method {}", DynamicDataSourceContextHolder.getDatasourceKey(), point.getSignature());
    }
}
