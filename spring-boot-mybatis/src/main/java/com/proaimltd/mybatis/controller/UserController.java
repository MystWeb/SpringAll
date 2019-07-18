package com.proaimltd.mybatis.controller;

import com.proaimltd.mybatis.model.entity.User;
import com.proaimltd.mybatis.model.entity.dto.UserSignInReqDTO;
import com.proaimltd.mybatis.service.IUserService;
import com.proaimltd.web.entity.response.APIResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.proaimltd.mybatis.common.RouteConstants.*;

/**
 * @author: ziming.xing
 * @date: 2019/7/12 13:46
 */
@Api(tags = "用户管理")
@RequestMapping(URL_PREFIX + URL_VERSION + USER_URL)
@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    @ApiOperation("根据ID获取用户信息")
    public User getUserById(@PathVariable Long id) {
        return userService.selectUserByPrimaryKey(id);
    }

    @PostMapping
    @ApiOperation("注册用户")
    public APIResponse<Integer> SignIn(UserSignInReqDTO userSignInReqDTO) {
        return APIResponse.success(userService.SignIn(userSignInReqDTO));
    }

    @DeleteMapping
    @ApiOperation("删除用户")
    public APIResponse<Integer> deleteUser(Long id) {
        return APIResponse.success(userService.deleteUserByPrimaryKey(id));
    }

}
