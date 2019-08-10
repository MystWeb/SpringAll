package com.proaimltd.mybatis.restful.model.mapper;

import com.proaimltd.mybatis.restful.model.entity.UserRole;

public interface UserRoleMapper {
    int deleteUserRoleByPrimaryKey(Long userId);

    int insertUserRole(UserRole record);

    int insertUserRoleSelective(UserRole record);

    UserRole selectUserRoleByPrimaryKey(Long id);
}