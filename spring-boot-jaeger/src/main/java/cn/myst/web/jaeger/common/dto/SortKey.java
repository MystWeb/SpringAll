package cn.myst.web.jaeger.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ziming.xing
 * Create Time：2020/6/15
 */
@Data
@ApiModel("排序字段")
public class SortKey implements Serializable {

    /*
     * The ID of the attribute to sort by.
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty(value = "排序字段", notes = "要排序的属性的名称")
    private transient String field;


    /*
     * The sort order. Ascending order, by default.
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty(value = "排序顺序", notes = "排序顺序，默认为升序")
    private transient String sort;

    /**
     * access 要素更改getter或setter或object字段定义的逻辑属性的可见性。
     * access的值可以是以下之一:
     * Access.WRITE_ONLY：逻辑属性的可见性仅在我们将JSON数据设置为Java对象时，即在反序列化时才可用。
     * Access.READ_ONLY：逻辑属性的可见性仅在我们从Java对象获取JSON数据时才可用，即在序列化时。
     * Access.READ_WRITE：逻辑属性的可见性在序列化和反序列化时都可用。
     * Access.AUTO：将自动确定逻辑属性的可见性，这是access元素的默认值。
     */

    /*@JsonProperty(access = JsonProperty.Access.READ_WRITE)*/
    /*@JsonIgnore
    public String getSortString() {
        return this.getField() + " " + this.getSort();
    }*/

    private static final long serialVersionUID = -7102429316863559463L;
}
