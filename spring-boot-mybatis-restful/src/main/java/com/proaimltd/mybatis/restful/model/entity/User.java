package com.proaimltd.mybatis.restful.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@ApiModel("用户")
public class User implements Serializable {
    private static final long serialVersionUID = 8592623340797981219L;

    @ApiModelProperty(value = "用户ID", dataType = "int", example = "1")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty(value = "是否删除", notes = "0：未删除，1：删除")
    private Boolean isDelete = false;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "角色组", notes = "用户拥有的角色")
    private Set<Role> roles = new HashSet<>();
}