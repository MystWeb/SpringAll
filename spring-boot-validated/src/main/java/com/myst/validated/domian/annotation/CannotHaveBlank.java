package com.myst.validated.domian.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author: ziming.xing
 * @date: 2019/9/26 17:16
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, ElementType.CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
// 这个注解是引入真正的去做验证的类
@Constraint(validatedBy = {CannotHaveBlankValidator.class})
public @interface CannotHaveBlank {
    // 默认错误信息
    String message() default "不能包含空格";

    // 分组
    Class<?>[] groups() default {};

    //负载
    Class<? extends Payload>[] payload() default {};

    //指定多个时使用
    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        CannotHaveBlank[] value();
    }
}
