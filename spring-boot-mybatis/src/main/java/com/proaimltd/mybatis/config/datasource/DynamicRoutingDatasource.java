/*
 * © Copyright Process Asset Integration and Management Limited 2015 - 2019.
 * All rights reserved.
 */

package com.proaimltd.mybatis.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author: ziming.xing
 * @date: 2019/7/18 21:54
 */

@Slf4j
public class DynamicRoutingDatasource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("Current Datasource is: {}", DynamicDataSourceContextHolder.getDatasourceKey());
        return DynamicDataSourceContextHolder.getDatasourceKey();
    }
}
