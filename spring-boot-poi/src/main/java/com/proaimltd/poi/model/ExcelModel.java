package com.proaimltd.poi.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author: ziming.xing
 * @date: 2019/7/29 19:35
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@Data
public class ExcelModel extends BaseRowModel implements Serializable {
    @ExcelProperty(value = "姓名", index = 0)
    private String name;

    @ExcelProperty(value = "年龄", index = 1)
    private String age;

    @ExcelProperty(value = "邮箱", index = 2)
    private String email;

    @ExcelProperty(value = "地址", index = 3)
    private String address;

    @ExcelProperty(value = "性别", index = 4)
    private String sax;

    @ExcelProperty(value = "高度", index = 5)
    private String height;

    @ExcelProperty(value = "备注", index = 6)
    private String last;
}
