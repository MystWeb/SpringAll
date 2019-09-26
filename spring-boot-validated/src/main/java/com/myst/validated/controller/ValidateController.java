package com.myst.validated.controller;

import com.myst.validated.bo.ValidateBO;
import com.myst.validated.bo.ValidateByGroupBO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

/**
 * @author: ziming.xing
 * @date: 2019/9/26 16:39
 */
@RestController
public class ValidateController {

    @GetMapping(value = "/validate")
    public String validate(@Validated ValidateBO validateBO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).collect(Collectors.toList())) {
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        }
        return "success";
    }

    /**
     * 喝酒这个去校验了年龄值，因为只有adult这个组才去校验年龄
     *
     * @param validateByGroupBO 校验对象
     * @param bindingResult     绑定结果的通用API
     */
    @GetMapping(value = "/drink")
    public String drink(@Validated({ValidateByGroupBO.Adult.class}) ValidateByGroupBO validateByGroupBO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 处理错误
            return "false";
        }
        return "success";
    }

    /**
     * 生活不需要去校验adult的分组 就不去校验对应的age的最小值
     *
     * @param validateByGroupBO 校验对象
     * @param bindingResult     绑定结果的通用API
     */
    @GetMapping(value = "live")
    public String live(@Validated ValidateByGroupBO validateByGroupBO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 错误处理
            return "false";
        }
        return "success";
    }

    /**
     * @param name 方法上的参数加校验
     */
    @GetMapping("/aspect")
    public String aspect(@NotNull(message = "name不能为空") String name) {
        return name;
    }
}
