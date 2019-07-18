package com.proaimltd.mybatis.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@ApiModel("信息")
public class Info implements Serializable {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("性别")
    private String sex;

    private static final long serialVersionUID = 1L;

}
