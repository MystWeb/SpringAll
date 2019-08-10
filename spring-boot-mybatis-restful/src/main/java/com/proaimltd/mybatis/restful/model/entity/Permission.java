package com.proaimltd.mybatis.restful.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("权限")
public class Permission implements Serializable {
    private static final long serialVersionUID = 4166574416610718728L;

    @ApiModelProperty(value = "权限ID", dataType = "int", example = "1")
    private Long id;

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;
}