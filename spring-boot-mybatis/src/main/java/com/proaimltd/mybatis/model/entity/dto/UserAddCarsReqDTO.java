package com.proaimltd.mybatis.model.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author: ziming.xing
 * @date: 2019/7/12 11:38
 */
@Data
@ApiModel("用户添加车辆请求")
public class UserAddCarsReqDTO {
    private Long uid;
    private List<Long> carIds;
}
