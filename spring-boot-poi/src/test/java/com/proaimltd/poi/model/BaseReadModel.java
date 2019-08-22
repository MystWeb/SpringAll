package com.proaimltd.poi.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 演示@ExcelProperty 是否支持继承
 *
 * @author: ziming.xing
 * @date: 2019/7/29 15:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseReadModel extends BaseRowModel {
    @ExcelProperty(index = 0)
    protected String str;

    @ExcelProperty(index = 1)
    protected Float ff;
}
