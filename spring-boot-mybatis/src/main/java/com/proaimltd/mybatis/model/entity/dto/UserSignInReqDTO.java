package com.proaimltd.mybatis.model.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ziming.xing
 * @date: 2019/7/12 14:21
 */
@Data
@ApiModel("用户注册请求")
public class UserSignInReqDTO {
    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty(value = "年龄", hidden = true)
    private Integer age = 0;

    @ApiModelProperty(value = "性别", hidden = true)
    private String sex;
}
