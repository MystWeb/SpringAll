package com.proaimltd.mybatis.service;

import com.proaimltd.mybatis.model.entity.Role;

import java.util.List;

/**
 * @author: ziming.xing
 * @date: 2019/7/11 15:12
 */
public interface IRoleService {
    int deleteRoleByPrimaryKey(Long id);

    int insertRole(Role record);

    int insertRoleSelective(Role record);

    List<Role> selectRoles();

    Role selectRoleByPrimaryKey(Long id);

    int updateRoleByPrimaryKeySelective(Role record);

    int updateRoleByPrimaryKey(Role record);
}
