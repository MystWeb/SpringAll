package com.myst.validated.bo;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author: ziming.xing
 * @date: 2019/9/26 17:06
 */
@Data
public class ValidateByGroupBO {
    /**
     * 只有adult组内才进行 validate 校验
     */
    @Min(value = 18, groups = {Adult.class})
    private Integer age;

    public interface Adult {
    }

    public interface Minor {
    }
}
