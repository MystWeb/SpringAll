package com.proaimltd.mybatis.restful.model.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: ziming.xing
 * @date: 2019/8/10 18:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("角色更新请求")
public class RoleUpdateReqDTO extends RoleCreateReqDTO {
    private static final long serialVersionUID = 7246639633594404666L;

    @ApiModelProperty(value = "角色ID", required = true, dataType = "int", example = "1")
    private Long id;
}
