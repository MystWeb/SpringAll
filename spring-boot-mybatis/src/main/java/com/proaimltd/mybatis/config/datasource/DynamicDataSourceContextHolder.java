/*
 * © Copyright Process Asset Integration and Management Limited 2015 - 2019.
 * All rights reserved.
 */

package com.proaimltd.mybatis.config.datasource;


import com.proaimltd.mybatis.common.enums.EnumDatasource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ziming.xing
 * @date: 2019/7/18 21:54
 */
public class DynamicDataSourceContextHolder {
    public static final ThreadLocal<String> contextHolder = ThreadLocal.withInitial(() -> EnumDatasource.WRITE.name());

    public static List<Object> datasourceKeys = new ArrayList<>();

    public static void setDatasourceKey(String key) {
        contextHolder.set(key);
    }

    public static String getDatasourceKey() {
        return contextHolder.get();
    }

    public static void clearDatasourceKey() {
        contextHolder.remove();
    }

    public static boolean containDatasourceKey(String key) {
        return datasourceKeys.contains(key);
    }
}
