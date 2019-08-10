package com.proaimltd.mybatis.restful.controller;

import com.proaimltd.mybatis.restful.model.entity.Role;
import com.proaimltd.mybatis.restful.model.entity.dto.RoleCreateReqDTO;
import com.proaimltd.mybatis.restful.model.entity.dto.RoleUpdateReqDTO;
import com.proaimltd.mybatis.restful.model.entity.dto.RolesCreatePermissionsReqDTO;
import com.proaimltd.mybatis.restful.service.IPermissionService;
import com.proaimltd.mybatis.restful.service.IRoleService;
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
@Api(tags = "角色管理")
@RestController
@RequestMapping("/roles")
public class RoleController {
    @Resource
    private IRoleService roleService;
    @Resource
    private IPermissionService permissionService;

    @DeleteMapping("/{id}")
    @ApiOperation("根据角色ID删除角色")
    public ResponseEntity<Integer> deleteRoleById(@PathVariable Long id) {
        Role result = roleService.selectRoleById(id);
        //当无法找到 Role 对象的时候，我们可以返回 HTTP 404 错误。
        HttpStatus status = result != null ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        // 删除用户
        int count = roleService.deleteRoleById(id);
        return new ResponseEntity<>(count, status);
    }

    @PostMapping
    @ApiOperation("创建角色")
    public ResponseEntity<?> createRole(@RequestBody RoleCreateReqDTO reqDTO) {
        Role Role = new Role();
        BeanUtils.copyProperties(reqDTO, Role);
        // 创建用户
        int result = roleService.createRole(Role);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/permissions")
    @ApiOperation("角色添加权限")
    public ResponseEntity<Integer> createRolesAddPermissions(@RequestBody RolesCreatePermissionsReqDTO reqDTO) {
        int result = permissionService.createRolesAddPermissions(reqDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation("查询所有角色")
    public ResponseEntity<List<Role>> selectRoles() {
        List<Role> Roles = roleService.selectRoles();
        return ResponseEntity.ok(Roles);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据角色ID查询角色")
    public ResponseEntity<Role> selectRoleById(@PathVariable Long id) {
        Role result = roleService.selectRoleById(id);
        //当无法找到 Role 对象的时候，我们可以返回 HTTP 404 错误。
        HttpStatus status = result != null ? HttpStatus.FOUND : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(result, status);
    }

    @PutMapping
    @ApiOperation("根据角色ID更新角色")
    public ResponseEntity<Integer> updateRoleById(@RequestBody RoleUpdateReqDTO reqDTO) {
        Role Role = new Role();
        BeanUtils.copyProperties(reqDTO, Role);
        // 更新用户
        int result = roleService.updateRoleById(Role);
        return ResponseEntity.ok(result);
    }
}

