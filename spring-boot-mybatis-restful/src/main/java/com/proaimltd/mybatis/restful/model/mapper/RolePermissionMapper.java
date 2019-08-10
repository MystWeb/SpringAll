package com.proaimltd.mybatis.restful.model.mapper;

import com.proaimltd.mybatis.restful.model.entity.RolePermission;

public interface RolePermissionMapper {
    int deleteRolePermissionByPrimaryKey(Long roleId);

    int insertRolePermission(RolePermission record);

    int insertRolePermissionSelective(RolePermission record);

    RolePermission selectRolePermissionByPrimaryKey(Long id);
}