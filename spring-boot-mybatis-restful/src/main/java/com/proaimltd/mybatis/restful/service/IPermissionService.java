package com.proaimltd.mybatis.restful.service;

import com.proaimltd.mybatis.restful.model.entity.Permission;
import com.proaimltd.mybatis.restful.model.entity.dto.RolesCreatePermissionsReqDTO;

import java.util.List;

/**
 * @author: ziming.xing
 * @date: 2019/8/10 16:29
 */
public interface IPermissionService {
    int deletePermissionById(Long id);

    int createPermission(Permission record);

    int createRolesAddPermissions(RolesCreatePermissionsReqDTO reqDTO);

    List<Permission> selectPermissions();

    Permission selectPermissionById(Long id);

    int updatePermissionById(Permission record);
}
