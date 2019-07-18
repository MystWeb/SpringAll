package com.proaimltd.mybatis.annotation;

import java.lang.annotation.*;

/**
 * @author: ziming.xing
 * @date: 2019/7/18 22:05
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDatasource {
    String value();
}
