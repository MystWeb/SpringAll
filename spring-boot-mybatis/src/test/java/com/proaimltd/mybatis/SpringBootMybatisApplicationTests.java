package com.proaimltd.mybatis;

import com.proaimltd.mybatis.model.entity.*;
import com.proaimltd.mybatis.model.entity.dto.UserAddCarsReqDTO;
import com.proaimltd.mybatis.model.entity.dto.UserAddRolesReqDTO;
import com.proaimltd.mybatis.model.entity.dto.UserSignInReqDTO;
import com.proaimltd.mybatis.model.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisApplicationTests {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private CarMapper carMapper;
    @Resource
    private InfoMapper infoMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    // 根据用户ID获取用户信息
    @Test
    public void getUserTest() {
        User user = userMapper.selectUserByPrimaryKey(1L);
        System.out.println(user.toString());
    }

    // 添加用户信息
    @Test
    public void addUserInfoTest() {
        Info info = new Info();
        info.setAge(0);
        infoMapper.insertInfoSelective(info);

    }

    // 添加用户
    @Test
    public void addUserTest() {
        UserSignInReqDTO userSignInReqDTO = new UserSignInReqDTO();
        userSignInReqDTO.setUsername("test");
        userSignInReqDTO.setPassword("123456");
        User user = new User();
        Info info = new Info();
        BeanUtils.copyProperties(userSignInReqDTO, user);
        BeanUtils.copyProperties(userSignInReqDTO, info);

        userMapper.insertUserSelective(user);
        infoMapper.insertInfoSelective(info);

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
    }

    // 添加角色
    @Test
    public void addRoleTest() {
        Role role = new Role();
        role.setRoleName("角色名称");
        roleMapper.insertRoleSelective(role);
    }

    // 用户赋予角色
    @Test
    public void userAddRolesTest() {
        UserAddRolesReqDTO userAddRolesReqDTO = new UserAddRolesReqDTO();
        userAddRolesReqDTO.setUid(3L);
        List<Long> rids = new ArrayList<>();
        rids.add(1L);
        rids.add(2L);
        userAddRolesReqDTO.setRids(rids);
        // 先清空该用户所有的角色，再把表单的已选的角色赋予给用户
        userRoleMapper.deleteUserRoleByUid(3L);

        List<Long> reqDTORids = userAddRolesReqDTO.getRids();
        reqDTORids.forEach(rid -> {
            UserRole userRole = new UserRole();
            userRole.setUid(userAddRolesReqDTO.getUid());
            userRole.setRid(rid);
            userRoleMapper.insertUserRoleSelective(userRole);
        });
    }

    // 用户买车
    @Test
    public void userAddCarsTest() {
        UserAddCarsReqDTO userAddCarsReqDTO = new UserAddCarsReqDTO();
        userAddCarsReqDTO.setUid(3L);
        List<Long> carIds = new ArrayList<>();
        carIds.add(1L);
        carIds.add(2L);

        userAddCarsReqDTO.setCarIds(carIds);
        carIds.forEach(cid -> {
            Car car = new Car();
            car.setUid(userAddCarsReqDTO.getUid());
            car.setId(cid);
            carMapper.updateCarByPrimaryKeySelective(car);
        });

    }
}
