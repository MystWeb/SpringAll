package com.proaimltd.mybatis.annotation.model.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: ziming.xing
 * @date: 2019/7/10 15:25
 */
@Data
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 9047644242833858924L;

    private Long id;
    private String username;
    private String password;
    private Date createTime;
}
