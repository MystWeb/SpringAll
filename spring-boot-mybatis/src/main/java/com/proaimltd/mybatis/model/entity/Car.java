package com.proaimltd.mybatis.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@ApiModel("汽车")
public class Car implements Serializable {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("车")
    private String car;

    @ApiModelProperty("用户ID")
    private Long uid;

    private static final long serialVersionUID = 1L;

}
