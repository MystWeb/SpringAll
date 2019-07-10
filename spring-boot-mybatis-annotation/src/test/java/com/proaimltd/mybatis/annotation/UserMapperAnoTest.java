package com.proaimltd.mybatis.annotation;

import com.proaimltd.mybatis.annotation.model.entity.User;
import com.proaimltd.mybatis.annotation.model.mapper.UserMapperAno;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperAnoTest {
    @Autowired
    private UserMapperAno userMapperAno;

    @Test
    public void testFindAll() {
        List<User> list = userMapperAno.findAll();
        list.forEach(user -> {
            log.info("user={}", user);
        });
    }

    @Test
    public void testFindById() {
        log.info("user={}", userMapperAno.findById(1L));
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("测试");
        user.setPassword("123");
        user.setCreateTime(new Date());
        userMapperAno.save(user);
        testFindAll();
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(4L);
        user.setUsername("测试呀");
        userMapperAno.update(user);
        testFindAll();
    }

    @Test
    public void delete() {
        userMapperAno.delete(3L);
        testFindAll();
    }
}
