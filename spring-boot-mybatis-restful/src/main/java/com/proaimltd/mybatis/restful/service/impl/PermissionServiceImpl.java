package com.proaimltd.mybatis.restful.service.impl;

import com.proaimltd.mybatis.restful.model.entity.Permission;
import com.proaimltd.mybatis.restful.model.entity.RolePermission;
import com.proaimltd.mybatis.restful.model.entity.dto.RolesCreatePermissionsReqDTO;
import com.proaimltd.mybatis.restful.model.mapper.PermissionMapper;
import com.proaimltd.mybatis.restful.model.mapper.RolePermissionMapper;
import com.proaimltd.mybatis.restful.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: ziming.xing
 * @date: 2019/8/10 16:30
 */
@Slf4j
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public int deletePermissionById(Long id) {
        return permissionMapper.deletePermissionByPrimaryKey(id);
    }

    @Override
    public int createPermission(Permission record) {
        try {
            return permissionMapper.insertPermissionSelective(record);
        } catch (DuplicateKeyException e) {
            log.error("创建权限失败，您创建的权限名：" + record.getName() + "已经存在！", e);
            return 0;
            /*throw new DuplicateKeyException("创建权限失败，您创建的权限名：" + record.getName() + "已经存在！", e);*/
        }
    }

    @Override
    public int createRolesAddPermissions(RolesCreatePermissionsReqDTO reqDTO) {
        // 角色ID集
        Set<Long> roleIDs = reqDTO.getRoleIDs();

        // 权限ID集
        Set<Long> permissionIDs = reqDTO.getPermissionIDs();

        // AtomicInteger线程安全的计数器
        AtomicInteger result = new AtomicInteger();

        roleIDs.forEach(roleId -> {
            // 删除旧关系
            rolePermissionMapper.deleteRolePermissionByPrimaryKey(roleId);
            permissionIDs.forEach(permissionId -> {
                // 添加新关系
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(roleId);
                rolePermission.setPermissionId(permissionId);
                result.addAndGet(rolePermissionMapper.insertRolePermissionSelective(rolePermission));
            });
        });
        return result.getAndIncrement();
    }

    @Override
    public List<Permission> selectPermissions() {
        return permissionMapper.selectPermissionByExample(null);
    }

    @Override
    public Permission selectPermissionById(Long id) {
        return permissionMapper.selectPermissionByPrimaryKey(id);
    }

    @Override
    public int updatePermissionById(Permission record) {
        Permission Permission = permissionMapper.selectPermissionByPrimaryKey(record.getId());
        if (Permission == null) {
            log.error("根据权限ID未查找到权限信息");
        }
        return permissionMapper.updatePermissionByPrimaryKeySelective(record);
    }
}
