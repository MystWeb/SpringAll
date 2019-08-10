package com.proaimltd.mybatis.restful.service.impl;

import com.proaimltd.mybatis.restful.model.entity.Role;
import com.proaimltd.mybatis.restful.model.entity.UserRole;
import com.proaimltd.mybatis.restful.model.entity.dto.UsersCreateRolesReqDTO;
import com.proaimltd.mybatis.restful.model.mapper.RoleMapper;
import com.proaimltd.mybatis.restful.model.mapper.UserRoleMapper;
import com.proaimltd.mybatis.restful.service.IRoleService;
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
public class RoleServiceImpl implements IRoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public int deleteRoleById(Long id) {
        return roleMapper.deleteRoleByPrimaryKey(id);
    }

    @Override
    public int createRole(Role record) {
        try {
            return roleMapper.insertRoleSelective(record);
        } catch (DuplicateKeyException e) {
            log.error("创建角色失败，您创建的角色名：" + record.getName() + "已经存在！", e);
            return 0;
            /*throw new DuplicateKeyException("创建角色失败，您创建的角色名：" + record.getName() + "已经存在！", e);*/
        }
    }

    @Override
    public int createUsersAddRoles(UsersCreateRolesReqDTO reqDTO) {
        // 用户ID集
        Set<Long> userIDs = reqDTO.getUserIDs();

        // 角色ID集
        Set<Long> roleIDs = reqDTO.getRoleIDs();

        // AtomicInteger线程安全的计数器
        AtomicInteger result = new AtomicInteger();

        userIDs.forEach(userId -> {
            // 删除旧关系
            userRoleMapper.deleteUserRoleByPrimaryKey(userId);
            roleIDs.forEach(roleId -> {
                // 添加新关系
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                result.addAndGet(userRoleMapper.insertUserRoleSelective(userRole));
            });
        });
        return result.getAndIncrement();
    }

    @Override
    public List<Role> selectRoles() {
        return roleMapper.selectRoleByExample(null);
    }

    @Override
    public Role selectRoleById(Long id) {
        return roleMapper.selectRoleByPrimaryKey(id);
    }

    @Override
    public int updateRoleById(Role record) {
        Role Role = roleMapper.selectRoleByPrimaryKey(record.getId());
        if (Role == null) {
            log.error("根据角色ID未查找到角色信息");
        }
        return roleMapper.updateRoleByPrimaryKeySelective(record);
    }
}
