package cn.myst.web.jaeger.model.employee.dto;

import cn.myst.web.jaeger.common.dto.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ziming.xing
 * Create Time：2020/4/27
 */
@Data
@ApiModel(value = "员工扩展属性")
public class EmployeeExtendedParameters extends BaseQuery {

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String departmentName;

    private static final long serialVersionUID = -6938637791999861018L;
}
