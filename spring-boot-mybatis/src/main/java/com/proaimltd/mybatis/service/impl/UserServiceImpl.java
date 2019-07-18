package com.proaimltd.mybatis.service.impl;

import com.proaimltd.mybatis.model.entity.Info;
import com.proaimltd.mybatis.model.entity.User;
import com.proaimltd.mybatis.model.entity.dto.UserSignInReqDTO;
import com.proaimltd.mybatis.model.mapper.UserMapper;
import com.proaimltd.mybatis.service.ICarService;
import com.proaimltd.mybatis.service.IInfoService;
import com.proaimltd.mybatis.service.IUserRoleService;
import com.proaimltd.mybatis.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.proaimltd.mybatis.common.enums.EnumUser.FAILED_FIND_USER_BY_ID;

/**
 * @author: ziming.xing
 * @date: 2019/7/12 13:40
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IInfoService infoService;
    @Autowired
    private ICarService carService;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public int deleteUserByPrimaryKey(Long id) {
        User user = userMapper.selectUserByPrimaryKey(id);
        if (null == user) {
            log.info(FAILED_FIND_USER_BY_ID.getMessage());
        }
        userRoleService.deleteUserRoleByUid(id);
        infoService.deleteInfoByPrimaryKey(id);
        carService.deleteCarByUid(id);
        return userMapper.deleteUserByPrimaryKey(id);
    }

    @Override
    public int insertUser(User record) {
        return 0;
    }

    @Override
    public int insertUserSelective(User record) {
        return insertUserSelective(record);
    }


    @Override
    public User selectUserByPrimaryKey(Long id) {
        User user = userMapper.selectUserByPrimaryKey(id);
        if (null == user) {
            log.info(FAILED_FIND_USER_BY_ID.getMessage());
        }
        return user;
    }

    @Override
    public int updateUserByPrimaryKeySelective(User record) {
        User user = userMapper.selectUserByPrimaryKey(record.getId());
        if (null == user) {
            log.info(FAILED_FIND_USER_BY_ID.getMessage());
        }
        return userMapper.updateUserByPrimaryKeySelective(record);
    }

    @Override
    public int updateUserByPrimaryKey(User record) {
        return 0;
    }

    @Override
    public int SignIn(UserSignInReqDTO userSignInReqDTO) {
        User user = new User();
        Info info = new Info();

        BeanUtils.copyProperties(userSignInReqDTO, user);
        BeanUtils.copyProperties(userSignInReqDTO, info);

        int i = userMapper.insertUserSelective(user);
        infoService.insertInfoSelective(info);
        return i;
    }
}
