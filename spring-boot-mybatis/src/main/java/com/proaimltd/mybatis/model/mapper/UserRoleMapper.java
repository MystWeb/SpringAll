package com.proaimltd.mybatis.model.mapper;

import com.proaimltd.mybatis.model.entity.UserRole;

public interface UserRoleMapper {
    int deleteUserRoleByPrimaryKey(Long id);

    int deleteUserRoleByUid(Long uid);

    int insertUserRole(UserRole record);

    int insertUserRoleSelective(UserRole record);

    UserRole selectUserRoleByPrimaryKey(Long id);

    int updateUserRoleByPrimaryKeySelective(UserRole record);

    int updateUserRoleByPrimaryKey(UserRole record);
}
