package com.proaimltd.mybatis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.proaimltd.mybatis.model.entity.Role;
import com.proaimltd.mybatis.model.entity.dto.PageReqDTO;
import com.proaimltd.mybatis.model.entity.dto.RoleRequestDTO;
import com.proaimltd.mybatis.service.IRoleService;
import com.proaimltd.web.entity.response.APIResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.proaimltd.mybatis.common.RouteConstants.*;

/**
 * @author: ziming.xing
 * @date: 2019/7/11 15:17
 */
@Slf4j
@Api(tags = "角色管理")
@RestController
@RequestMapping(URL_PREFIX + URL_VERSION + ROLE_URL)
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @PostMapping
    @ApiOperation("新增角色信息")
    public APIResponse<Integer> addRole(@RequestBody Role role) {
        return APIResponse.success(roleService.insertRoleSelective(role));
    }

    @DeleteMapping
    @ApiOperation("根据ID删除角色信息")
    public APIResponse<Integer> removeRoleById(@RequestBody List<RoleRequestDTO> ids) {
        AtomicInteger count = new AtomicInteger();
        ids.forEach(requestDTO -> count.addAndGet(roleService.deleteRoleByPrimaryKey(requestDTO.getId())));
        // 如果角色删除，看业务，是否需要删除关联角色的关系表数据
        return APIResponse.success(count.get());
    }

    @PutMapping
    @ApiOperation("根据角色ID更新角色信息")
    public APIResponse<Integer> updateRoleSelectiveById(@RequestBody Role role) {
        return APIResponse.success(roleService.updateRoleByPrimaryKeySelective(role));
    }

    @GetMapping
    @ApiOperation("查询所有的角色信息")
    public APIResponse<List<Role>> getRoles() {
        return APIResponse.success(roleService.selectRoles());
    }

    @GetMapping("/page")
    @ApiOperation("分页查询所有的角色信息")
    public APIResponse<PageInfo<Role>> getPageRoles(@RequestBody PageReqDTO pageReqDTO) {
        PageHelper.startPage(pageReqDTO.getPageNum(), pageReqDTO.getPageSize());
        List<Role> roles = roleService.selectRoles();
        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        return APIResponse.success(rolePageInfo);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据角色ID获取角色信息")
    public APIResponse<Role> getRoleById(@PathVariable("id") Long id) {
        return APIResponse.success(roleService.selectRoleByPrimaryKey(id));
    }

}
