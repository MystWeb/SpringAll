package com.proaimltd.mybatis.restful.controller;

import com.proaimltd.mybatis.restful.model.entity.User;
import com.proaimltd.mybatis.restful.model.entity.dto.UserCreateReqDTO;
import com.proaimltd.mybatis.restful.model.entity.dto.UserUpdateReqDTO;
import com.proaimltd.mybatis.restful.model.entity.dto.UsersCreateRolesReqDTO;
import com.proaimltd.mybatis.restful.service.IRoleService;
import com.proaimltd.mybatis.restful.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: ziming.xing
 * @date: 2019/8/10 17:44
 */
//默认为单例，singleton = false表示启用多例
@Api(tags = "用户管理")
@RestController
@RequestMapping("/users")
public class UserController {
    // 线程安全Map
    /*Map<String, User> users = Collections.synchronizedMap(new HashMap<String, User>());*/

    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;

    @DeleteMapping("/{id}")
    @ApiOperation("根据用户ID删除用户")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        // 删除用户
        int result = userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    @ApiOperation("创建用户")
    public ResponseEntity<?> createUser(@RequestBody UserCreateReqDTO reqDTO) {
        User user = new User();
        BeanUtils.copyProperties(reqDTO, user);
        // 创建用户
        int result = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/roles")
    @ApiOperation("用户添加角色")
    public ResponseEntity<Integer> createUsersAddRoles(@RequestBody UsersCreateRolesReqDTO reqDTO) {
        int result = roleService.createUsersAddRoles(reqDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation("查询所有用户")
    public ResponseEntity<List<User>> selectUsers() {
        List<User> users = userService.selectUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据用户ID查询用户")
    public ResponseEntity<User> selectUserById(@PathVariable Long id) {
        User result = userService.selectUserById(id);
        //当无法找到 User 对象的时候，我们可以返回 HTTP 404 错误。
        HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(result, status);
    }

    @PutMapping
    @ApiOperation("根据用户ID更新用户")
    public ResponseEntity<Integer> updateUserById(@RequestBody UserUpdateReqDTO reqDTO) {
        User user = new User();
        BeanUtils.copyProperties(reqDTO, user);
        // 更新用户
        int result = userService.updateUserById(user);
        return ResponseEntity.ok(result);
    }
}
