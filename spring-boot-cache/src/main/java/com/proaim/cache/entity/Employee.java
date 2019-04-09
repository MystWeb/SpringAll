package com.proaim.cache.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("员工模型实体类")
public class Employee implements Serializable {
    private static final long serialVersionUID = 8823284355918922939L;
    @ApiModelProperty(name = "id", value = "员工编号", example = "1")
    private Integer id;
    @ApiModelProperty(name = "lastName", value = "姓名", example = "2")
    private String lastName;
    @ApiModelProperty(name = "email", value = "邮箱", example = "3")
    private String email;
    @ApiModelProperty(name = "gender", value = "性别", example = "4")
    private Integer gender; //性别 1男  0女
    @ApiModelProperty(name = "dId", value = "部门编号", example = "5")
    private Integer dId;
}
