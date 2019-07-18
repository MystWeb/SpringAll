package com.proaimltd.mybatis.model.mapper;

import com.proaimltd.mybatis.model.entity.User;

public interface UserMapper {
    int deleteUserByPrimaryKey(Long id);

    int insertUser(User record);

    int insertUserSelective(User record);

    User selectUserByPrimaryKey(Long id);

    int updateUserByPrimaryKeySelective(User record);

    int updateUserByPrimaryKey(User record);
}
