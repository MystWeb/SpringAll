package com.proaimltd.mybatis.restful.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("用户角色关系")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 6687922148842283767L;
    @ApiModelProperty(value = "ID", dataType = "int", example = "1")
    private Long id;

    @ApiModelProperty(value = "用户ID", dataType = "int", example = "1")
    private Long userId;

    @ApiModelProperty(value = "角色ID", dataType = "int", example = "1")
    private Long roleId;
}