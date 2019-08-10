package com.proaimltd.mybatis.restful.service;

import com.proaimltd.mybatis.restful.model.entity.Role;
import com.proaimltd.mybatis.restful.model.entity.RoleExample;
import com.proaimltd.mybatis.restful.model.entity.dto.UsersCreateRolesReqDTO;

import java.util.List;

/**
 * @author: ziming.xing
 * @date: 2019/8/10 16:29
 */
public interface IRoleService {
    int deleteRoleById(Long id);

    int createRole(Role record);

    int createUsersAddRoles(UsersCreateRolesReqDTO reqDTO);

    List<Role> selectRoles();

    Role selectRoleById(Long id);

    int updateRoleById(Role record);
}
