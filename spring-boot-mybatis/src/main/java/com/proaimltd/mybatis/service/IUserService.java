package com.proaimltd.mybatis.service;

import com.proaimltd.mybatis.model.entity.User;
import com.proaimltd.mybatis.model.entity.dto.UserSignInReqDTO;

/**
 * @author: ziming.xing
 * @date: 2019/7/12 13:39
 */
public interface IUserService {
    int deleteUserByPrimaryKey(Long id);

    int insertUser(User record);

    int insertUserSelective(User record);

    User selectUserByPrimaryKey(Long id);

    int updateUserByPrimaryKeySelective(User record);

    int updateUserByPrimaryKey(User record);

    int SignIn(UserSignInReqDTO userSignInReqDTO);
}
