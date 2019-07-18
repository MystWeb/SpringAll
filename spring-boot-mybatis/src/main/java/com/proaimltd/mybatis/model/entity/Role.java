package com.proaimltd.mybatis.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@ApiModel("角色")
public class Role implements Serializable {
    private static final long serialVersionUID = -4814926591045638778L;

    @ApiModelProperty(value = "角色ID")
    private Long id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updateTime;
}
