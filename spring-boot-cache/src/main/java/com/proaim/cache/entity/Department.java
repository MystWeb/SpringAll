package com.proaim.cache.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("部门模型实体类")
public class Department implements Serializable {
    private static final long serialVersionUID = 1305827436885427654L;
    @ApiModelProperty(name = "id", value = "部门编号", example = "1")
    private Integer id;
    @ApiModelProperty(name = "departmentName", value = "部门名称", example = "2")
    private String departmentName;
}
