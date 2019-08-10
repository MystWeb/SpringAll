package com.proaimltd.mybatis.restful.model.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author: ziming.xing
 * @date: 2019/8/10 18:42
 */
@Data
@ApiModel("用户添加角色请求")
public class UsersCreateRolesReqDTO implements Serializable {
    private static final long serialVersionUID = 7246639633594404666L;

    @ApiModelProperty(value = "用户IDs", required = true)
    private Set<Long> userIDs;

    @ApiModelProperty(value = "角色IDs", required = true)
    private Set<Long> roleIDs;
}
