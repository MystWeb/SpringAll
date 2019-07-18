package com.proaimltd.mybatis.model.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author: ziming.xing
 * @date: 2019/7/12 11:22
 */
@ApiModel("用户添加角色请求")
@Data
public class UserAddRolesReqDTO {
    private Long uid;
    private List<Long> rids;
}
