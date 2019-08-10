package com.proaimltd.mybatis.restful.controller;

import com.proaimltd.mybatis.restful.model.entity.Permission;
import com.proaimltd.mybatis.restful.model.entity.dto.PermissionCreateReqDTO;
import com.proaimltd.mybatis.restful.model.entity.dto.PermissionUpdateReqDTO;
import com.proaimltd.mybatis.restful.service.IPermissionService;
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
@Api(tags = "权限管理")
@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @Resource
    private IPermissionService PermissionService;

    @DeleteMapping("/{id}")
    @ApiOperation("根据权限ID删除权限")
    public ResponseEntity<Integer> deletePermissionById(@PathVariable Long id) {
        Permission result = PermissionService.selectPermissionById(id);
        //当无法找到 Permission 对象的时候，我们可以返回 HTTP 404 错误。
        HttpStatus status = result != null ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        // 删除权限
        int count = PermissionService.deletePermissionById(id);
        return new ResponseEntity<>(count, status);
    }

    @PostMapping
    @ApiOperation("创建权限")
    public ResponseEntity<Integer> createPermission(@RequestBody PermissionCreateReqDTO reqDTO) {
        Permission Permission = new Permission();
        BeanUtils.copyProperties(reqDTO, Permission);
        // 创建权限
        int result = PermissionService.createPermission(Permission);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation("查询所有权限")
    public ResponseEntity<List<Permission>> selectPermissions() {
        List<Permission> Permissions = PermissionService.selectPermissions();
        return ResponseEntity.ok(Permissions);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据权限ID查询权限")
    public ResponseEntity<Permission> selectPermissionById(@PathVariable Long id) {
        Permission result = PermissionService.selectPermissionById(id);
        //当无法找到 Permission 对象的时候，我们可以返回 HTTP 404 错误。
        HttpStatus status = result != null ? HttpStatus.FOUND : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(result, status);
    }

    @PutMapping
    @ApiOperation("根据权限ID更新权限")
    public ResponseEntity<Integer> updatePermissionById(@RequestBody PermissionUpdateReqDTO reqDTO) {
        Permission Permission = new Permission();
        BeanUtils.copyProperties(reqDTO, Permission);
        // 更新权限
        int result = PermissionService.updatePermissionById(Permission);
        return ResponseEntity.ok(result);
    }
}
