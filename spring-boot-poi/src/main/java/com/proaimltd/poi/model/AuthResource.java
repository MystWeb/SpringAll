/*
 * © Copyright Process Asset Integration and Management Limited 2013 - 2019.
 *  All rights reserved.
 */

package com.proaimltd.poi.model;

/**
 * @project: trinity-parent
 * @packageName: com.proaimltd.web.common.entity.security.resource.dto
 * @author: Administrator
 * @date: 2019/7/26 14:58
 * @description：TODO
 */

import com.proaimltd.poi.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "资源")
public class AuthResource extends BaseEntity {
    @ApiModelProperty(value = "父ID")
    private Long pid;

    @ApiModelProperty(value = "资源创建者ID")
    private Long createUserId;

    @ApiModelProperty(value = "资源名称")
    private String resourceName;

    @ApiModelProperty(value = "请求类型")
    private String method;

    @ApiModelProperty(value = "元素ID")
    private String elementId;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "资源类型")
    private Integer category;

    @ApiModelProperty(value = "资源的URL地址")
    private String resUrl;

    @ApiModelProperty(value = "是否显示", notes = "如果资源类型是页面元素,例如: button. 设置为false,这个button就一个不显示")
    private Boolean isVisiable;

    @ApiModelProperty(value = "子资源")
    private Set<AuthResource> children = new HashSet<>();

    private static final long serialVersionUID = 1L;
}

