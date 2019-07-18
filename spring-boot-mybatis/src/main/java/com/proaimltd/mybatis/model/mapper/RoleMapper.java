package com.proaimltd.mybatis.model.mapper;

import com.proaimltd.mybatis.model.entity.Role;

import java.util.List;

public interface RoleMapper {
    int deleteRoleByPrimaryKey(Long id);

    int insertRole(Role record);

    int insertRoleSelective(Role record);

    List<Role> selectRoles();

    List<Role> selectRolesByUserId();

    Role selectRoleByPrimaryKey(Long id);

    int updateRoleByPrimaryKeySelective(Role record);

    int updateRoleByPrimaryKey(Role record);
}
