package com.proaimltd.poi.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author: ziming.xing
 * @date: 2019/7/31 14:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("资源Excel模板")
public class ResourceTemplate extends BaseRowModel implements Serializable {
    private static final long serialVersionUID = 5603677902941585221L;

    @ExcelProperty(value = "父ID", index = 0)
    @ApiModelProperty(value = "父ID")
    private Long pid = 0L;

    @ExcelProperty(value = "租户ID", index = 1)
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;

    @ExcelProperty(value = "资源创建者ID", index = 2)
    @ApiModelProperty(value = "资源创建者ID")
    private Long createUserId;

    @ExcelProperty(value = "资源名称", index = 3)
    @ApiModelProperty(value = "资源名称")
    private String resourceName;

    @ExcelProperty(value = "请求类型", index = 4)
    @ApiModelProperty(value = "请求类型")
    private String method;

    @ExcelProperty(value = "元素ID", index = 5)
    @ApiModelProperty(value = "元素ID")
    private String elementId = "";

    @ExcelProperty(value = "描述", index = 6)
    @ApiModelProperty(value = "描述")
    private String description = "";

    @ExcelProperty(value = "资源类型", index = 7)
    @ApiModelProperty(value = "资源类型")
    private Integer category;

    @ExcelProperty(value = "资源的URL地址", index = 8)
    @ApiModelProperty(value = "资源的URL地址")
    private String resUrl;

    @ExcelProperty(value = "是否显示", index = 9)
    @ApiModelProperty(value = "是否显示", notes = "如果资源类型是页面元素,例如: button. 设置为false,这个button就一个不显示")
    private Boolean isVisiable = true;
}
