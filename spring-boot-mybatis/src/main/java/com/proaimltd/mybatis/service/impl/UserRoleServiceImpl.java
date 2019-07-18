package com.proaimltd.mybatis.service.impl;

import com.proaimltd.mybatis.model.entity.UserRole;
import com.proaimltd.mybatis.model.mapper.UserRoleMapper;
import com.proaimltd.mybatis.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ziming.xing
 * @date: 2019/7/14 20:03
 */
@Slf4j
@Service
public class UserRoleServiceImpl implements IUserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public int deleteUserRoleByPrimaryKey(Long id) {
        return userRoleMapper.deleteUserRoleByPrimaryKey(id);
    }

    @Override
    public int deleteUserRoleByUid(Long uid) {
        return userRoleMapper.deleteUserRoleByUid(uid);
    }

    @Override
    public int insertUserRole(UserRole record) {
        return userRoleMapper.insertUserRole(record);
    }

    @Override
    public int insertUserRoleSelective(UserRole record) {
        return userRoleMapper.insertUserRoleSelective(record);
    }

    @Override
    public UserRole selectUserRoleByPrimaryKey(Long id) {
        return userRoleMapper.selectUserRoleByPrimaryKey(id);
    }

    @Override
    public int updateUserRoleByPrimaryKeySelective(UserRole record) {
        return userRoleMapper.updateUserRoleByPrimaryKeySelective(record);
    }

    @Override
    public int updateUserRoleByPrimaryKey(UserRole record) {
        return userRoleMapper.updateUserRoleByPrimaryKey(record);
    }
}
