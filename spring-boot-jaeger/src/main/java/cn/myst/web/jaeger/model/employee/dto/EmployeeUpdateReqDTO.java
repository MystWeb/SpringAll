package cn.myst.web.jaeger.model.employee.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ziming.xing
 * Create Time：2020/4/27
 */
@Data
public class EmployeeUpdateReqDTO implements Serializable {

    /**
     * 员工ID
     */
    @NotNull(message = "员工ID不允许为空")
    @ApiModelProperty(value = "员工ID", required = true)
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

    private static final long serialVersionUID = -4717730283496254571L;
}
