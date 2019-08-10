package com.proaimltd.mybatis.restful.service.impl;

import com.proaimltd.mybatis.restful.model.entity.User;
import com.proaimltd.mybatis.restful.model.entity.UserExample;
import com.proaimltd.mybatis.restful.model.mapper.UserMapper;
import com.proaimltd.mybatis.restful.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: ziming.xing
 * @date: 2019/8/10 16:30
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteUserById(Long id) {
        return userMapper.deleteUserByPrimaryKey(id);
    }

    @Override
    public int createUser(User record) {
        try {
            return userMapper.insertUserSelective(record);
        } catch (DuplicateKeyException e) {
            log.error("创建用户失败，您创建的用户名：" + record.getUsername() + "已经存在！", e);
            return 0;
            /*throw new DuplicateKeyException("创建用户失败，您创建的用户名：" + record.getUsername() + "已经存在！", e);*/
        }
    }

    @Override
    public List<User> selectUsers() {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(false);
        return userMapper.selectUserByExample(example);
    }

    @Override
    public User selectUserById(Long id) {
        return userMapper.selectUserByPrimaryKey(id);
    }

    @Override
    public int updateUserById(User record) {
        return userMapper.updateUserByPrimaryKeySelective(record);
    }
}
