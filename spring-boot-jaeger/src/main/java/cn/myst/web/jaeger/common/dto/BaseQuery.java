package cn.myst.web.jaeger.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ziming.xing
 * Create Time：2020/6/15
 */
@Data
@ApiModel(value = "基础查询")
public class BaseQuery implements Serializable {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty(value = "关键字")
    private transient String keyword;

    @ApiModelProperty(value = "排序字段集")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private transient List<SortKey> sortKeys;

    private static final long serialVersionUID = -4644684531874033663L;
}