package com.proaimltd.mybatis.restful.model.mapper;

import com.proaimltd.mybatis.restful.model.entity.User;
import com.proaimltd.mybatis.restful.model.entity.UserExample;
import java.util.List;

public interface UserMapper {
    int deleteUserByPrimaryKey(Long id);

    int insertUser(User record);

    int insertUserSelective(User record);

    List<User> selectUserByExample(UserExample example);

    User selectUserByPrimaryKey(Long id);

    int updateUserByPrimaryKeySelective(User record);

    int updateUserByPrimaryKey(User record);
}