package com.myst.validated.bo;

import com.myst.validated.domian.annotation.CannotHaveBlank;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author: ziming.xing
 * @date: 2019/9/26 16:35
 */
@Data
public class ValidateBO {
    @NotBlank(message = "name不能为空")
    private String name;

    @NotNull(message = "age不能为空")
    @Min(value = 18, message = "年龄不能小于18岁")
    private Integer age;

    @Email(message = "email格式错误")
    private String email;

    /**
     * 自定义注解 不能包含空格字符串
     */
    @CannotHaveBlank
    private String blank;

    /**
     * 正则校验
     */
    @Pattern(regexp = "^1([34578])\\d{9}$", message = "手机号码格式错误")
    private String phone;

}
