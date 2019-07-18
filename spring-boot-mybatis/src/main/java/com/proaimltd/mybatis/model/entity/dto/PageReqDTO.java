package com.proaimltd.mybatis.model.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: ziming.xing
 * @date: 2019/7/14 20:12
 */
@Data
@ApiModel("分页实体类")
public class PageReqDTO implements Serializable {
    private static final long serialVersionUID = -8200961672586371603L;

    @ApiModelProperty(value = "当前页", required = true)
    private Integer pageNum = 1;
    @ApiModelProperty(value = "行数")
    private Integer pageSize = 10;
}
