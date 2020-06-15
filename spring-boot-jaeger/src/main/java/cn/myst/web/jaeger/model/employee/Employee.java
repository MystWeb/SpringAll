package cn.myst.web.jaeger.model.employee;

import cn.myst.web.jaeger.model.employee.dto.EmployeeExtendedParameters;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ziming.xing
 * Create Time：2020/6/15
 */

/**
 * 员工
 */
@ApiModel(value = "员工")
@Data
public class Employee extends EmployeeExtendedParameters {
    /**
     * 员工ID
     */
    @ApiModelProperty(value = "员工ID")
    private Integer id;

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

    private static final long serialVersionUID = 1L;
}