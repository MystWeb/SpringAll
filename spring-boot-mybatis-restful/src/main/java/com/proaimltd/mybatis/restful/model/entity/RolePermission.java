package com.proaimltd.mybatis.restful.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("角色权限关系")
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -6691768471758388443L;

    @ApiModelProperty(value = "ID", dataType = "int", example = "1")
    private Long id;

    @ApiModelProperty(value = "角色ID", dataType = "int", example = "1")
    private Long roleId;

    @ApiModelProperty(value = "权限ID", dataType = "int", example = "1")
    private Long permissionId;
}