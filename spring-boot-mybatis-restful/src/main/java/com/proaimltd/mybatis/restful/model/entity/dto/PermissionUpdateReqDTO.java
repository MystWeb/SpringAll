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
@ApiModel("权限更新请求")
public class PermissionUpdateReqDTO extends PermissionCreateReqDTO {
    private static final long serialVersionUID = 7246639633594404666L;

    @ApiModelProperty(value = "权限ID", required = true, dataType = "int", example = "1")
    private Long id;
}
