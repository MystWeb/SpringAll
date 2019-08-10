package com.proaimltd.mybatis.restful.model.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: ziming.xing
 * @date: 2019/8/10 18:42
 */
@Data
@ApiModel("权限创建请求")
public class PermissionCreateReqDTO implements Serializable {
    private static final long serialVersionUID = 7246639633594404666L;

    @ApiModelProperty(value = "权限名", required = true)
    private String name;

    @ApiModelProperty("描述")
    private String description;
}
