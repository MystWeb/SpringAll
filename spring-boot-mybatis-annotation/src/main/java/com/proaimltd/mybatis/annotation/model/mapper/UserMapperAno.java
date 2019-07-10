package com.proaimltd.mybatis.annotation.model.mapper;

import com.proaimltd.mybatis.annotation.model.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: ziming.xing
 * @date: 2019/7/10 15:26
 */
@Repository
public interface UserMapperAno {
    @Select("select * from user")
    @Results({
            @Result(property = "createTime", column = "create_time")
    })
    List<User> findAll();

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(property = "createTime", column = "create_time")
    })
    User findById(Long id);

    @Insert("insert into user(username,password,create_time) values(#{username},#{password},#{createTime})")
    void save(User user);

    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    void update(User user);

    @Delete("delete from user where id=#{id}")
    void delete(Long id);
}
