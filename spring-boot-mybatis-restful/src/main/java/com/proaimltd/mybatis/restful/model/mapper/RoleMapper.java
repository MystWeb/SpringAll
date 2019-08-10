package com.proaimltd.mybatis.restful.model.mapper;

import com.proaimltd.mybatis.restful.model.entity.Role;
import com.proaimltd.mybatis.restful.model.entity.RoleExample;
import java.util.List;

public interface RoleMapper {
    int deleteRoleByPrimaryKey(Long id);

    int insertRole(Role record);

    int insertRoleSelective(Role record);

    List<Role> selectRoleByExample(RoleExample example);

    List<Role> getRolesByUserId(Long userId);

    Role selectRoleByPrimaryKey(Long id);

    int updateRoleByPrimaryKeySelective(Role record);

    int updateRoleByPrimaryKey(Role record);
}