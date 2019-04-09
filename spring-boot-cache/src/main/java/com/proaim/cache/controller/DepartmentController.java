package com.proaim.cache.controller;

import com.proaim.cache.entity.Department;
import com.proaim.cache.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@Api(tags = "部门控制器")
@RequestMapping("/dept")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @ApiOperation(value = "查询某部门信息")
    @ApiImplicitParam(name = "id", value = "部门编号")
    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable(value = "id") Integer id) {
        log.info("查询部门");
        return departmentService.getDeptById(id);
    }
}
