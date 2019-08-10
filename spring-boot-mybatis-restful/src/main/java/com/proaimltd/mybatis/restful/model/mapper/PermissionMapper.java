package com.proaimltd.mybatis.restful.model.mapper;

import com.proaimltd.mybatis.restful.model.entity.Permission;
import com.proaimltd.mybatis.restful.model.entity.PermissionExample;

import java.util.List;

public interface PermissionMapper {
    int deletePermissionByPrimaryKey(Long id);

    int insertPermission(Permission record);

    int insertPermissionSelective(Permission record);

    List<Permission> selectPermissionByExample(PermissionExample example);

    List<Permission> getPermissionsByRoleId(Long roleId);

    Permission selectPermissionByPrimaryKey(Long id);

    int updatePermissionByPrimaryKeySelective(Permission record);

    int updatePermissionByPrimaryKey(Permission record);
}