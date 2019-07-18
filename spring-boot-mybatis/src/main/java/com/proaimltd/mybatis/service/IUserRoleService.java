package com.proaimltd.mybatis.service;

import com.proaimltd.mybatis.model.entity.UserRole;

/**
 * @author: ziming.xing
 * @date: 2019/7/14 20:03
 */
public interface IUserRoleService {
    int deleteUserRoleByPrimaryKey(Long id);

    int deleteUserRoleByUid(Long uid);

    int insertUserRole(UserRole record);

    int insertUserRoleSelective(UserRole record);

    UserRole selectUserRoleByPrimaryKey(Long id);

    int updateUserRoleByPrimaryKeySelective(UserRole record);

    int updateUserRoleByPrimaryKey(UserRole record);
}
