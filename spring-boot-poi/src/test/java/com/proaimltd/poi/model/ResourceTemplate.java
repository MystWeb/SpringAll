package com.proaimltd.poi.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author: ziming.xing
 * @date: 2019/7/31 14:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceTemplate extends BaseRowModel implements Serializable {
    private static final long serialVersionUID = 5603677902941585221L;

    @ExcelProperty(value = "父ID", index = 0)
    private Long pid;

    @ExcelProperty(value = "站点ID", index = 1)
    private Long tenantId;

    @ExcelProperty(value = "资源创建者ID", index = 2)
    private Long createUserId;

    @ExcelProperty(value = "资源名称", index = 3)
    private String resName;

    @ExcelProperty(value = "元素ID", index = 4)
    private String elementId;

    @ExcelProperty(value = "描述", index = 5)
    private String description;

    @ExcelProperty(value = "资源类型", index = 6)
    private Integer category;

    @ExcelProperty(value = "资源的URL地址", index = 7)
    private String resUrl;

    @ExcelProperty(value = "是否显示", index = 8)
    private Boolean isVisiable;
}
