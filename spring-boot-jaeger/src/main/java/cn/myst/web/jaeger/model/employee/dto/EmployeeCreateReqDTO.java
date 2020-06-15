package cn.myst.web.jaeger.model.employee.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ziming.xing
 * Create Time：2020/6/15
 */
@Data
public class EmployeeCreateReqDTO implements Serializable {

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String lastName;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer gender;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    private Integer dId;

    private static final long serialVersionUID = -637175078775342535L;
}
