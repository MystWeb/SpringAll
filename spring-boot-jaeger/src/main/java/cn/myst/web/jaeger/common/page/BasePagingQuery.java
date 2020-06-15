package cn.myst.web.jaeger.common.page;

import cn.myst.web.jaeger.common.dto.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ziming.xing
 * Create Time：2020/6/15
 */
@Data
@ApiModel("基础分页查询")
public class BasePagingQuery extends BaseQuery {

    @ApiModelProperty(value = "当前页", dataType = "int", example = "1", required = true)
    private Integer pageNum = 1;

    @ApiModelProperty(value = "行数", dataType = "int", example = "10", required = true)
    private Integer pageSize = 10;

    private static final long serialVersionUID = 5587287994379503415L;
}
