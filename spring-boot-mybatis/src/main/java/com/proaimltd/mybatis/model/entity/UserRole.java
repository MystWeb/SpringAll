package com.proaimltd.mybatis.model.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@ApiModel("用户角色关系实体")
public class UserRole implements Serializable {
    private Long id;

    private Long uid;

    private Long rid;

    private static final long serialVersionUID = 1L;

}
