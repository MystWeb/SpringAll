package com.myst.validated.domian.annotation;

import javax.validation.groups.Default;
import java.lang.annotation.*;

/**
 * 用来表示开启hibernate校验的注解
 *
 * @author: ziming.xing
 * @date: 2019/9/26 17:42
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableValidate {
    Class<?>[] groups() default {Default.class};// 校验分组信息
}
