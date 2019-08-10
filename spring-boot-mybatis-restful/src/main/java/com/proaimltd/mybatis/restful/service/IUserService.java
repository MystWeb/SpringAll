package com.proaimltd.mybatis.restful.service;

import com.proaimltd.mybatis.restful.model.entity.User;

import java.util.List;

/**
 * @author: ziming.xing
 * @date: 2019/8/10 16:29
 */
public interface IUserService {
    int deleteUserById(Long id);

    int createUser(User record);

    List<User> selectUsers();

    User selectUserById(Long id);

    int updateUserById(User record);
}
