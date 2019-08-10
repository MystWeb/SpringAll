package com.proaimltd.mybatis.restful.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@ApiModel("角色")
public class Role implements Serializable {
    private static final long serialVersionUID = 2408244216366619624L;

    @ApiModelProperty(value = "角色ID", dataType = "int", example = "1")
    private Long id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty(value = "权限组", notes = "角色拥有的权限")
    private Set<Permission> permissions = new HashSet<>();
}