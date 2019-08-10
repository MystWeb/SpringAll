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
@ApiModel("角色添加权限请求")
public class RolesCreatePermissionsReqDTO implements Serializable {
    private static final long serialVersionUID = 7246639633594404666L;

    @ApiModelProperty(value = "角色IDs", required = true)
    private Set<Long> roleIDs;

    @ApiModelProperty(value = "权限IDs", required = true)
    private Set<Long> permissionIDs;
}
