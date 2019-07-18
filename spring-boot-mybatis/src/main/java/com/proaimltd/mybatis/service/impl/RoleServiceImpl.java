package com.proaimltd.mybatis.service.impl;

import com.proaimltd.mybatis.model.entity.Role;
import com.proaimltd.mybatis.model.mapper.RoleMapper;
import com.proaimltd.mybatis.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.proaimltd.mybatis.common.enums.EnumRole.FAILED_FIND_ROLE_BY_ID;

/**
 * @author: ziming.xing
 * @date: 2019/7/11 15:13
 */
@Slf4j
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public int deleteRoleByPrimaryKey(Long id) {
        Role role = roleMapper.selectRoleByPrimaryKey(id);
        if (null == role) {
            log.info(FAILED_FIND_ROLE_BY_ID.getMessage());
        }
        return roleMapper.deleteRoleByPrimaryKey(id);
    }

    @Override
    public int insertRole(Role record) {
        return 0;
    }

    @Override
    public int insertRoleSelective(Role record) {
        return roleMapper.insertRoleSelective(record);
    }

    @Override
    public List<Role> selectRoles() {
        return roleMapper.selectRoles();
    }

    @Override
    public Role selectRoleByPrimaryKey(Long id) {
        Role role = roleMapper.selectRoleByPrimaryKey(id);
        if (null == role) {
            log.info(FAILED_FIND_ROLE_BY_ID.getMessage());
        }
        return role;
    }

    @Override
    public int updateRoleByPrimaryKeySelective(Role record) {
        Role role = roleMapper.selectRoleByPrimaryKey(record.getId());
        if (null == role) {
            log.info(FAILED_FIND_ROLE_BY_ID.getMessage());
        }
        return roleMapper.updateRoleByPrimaryKeySelective(record);
    }

    @Override
    public int updateRoleByPrimaryKey(Role record) {
        return 0;
    }
}
