package com.proaim.cache.controller;

import com.proaim.cache.entity.Employee;
import com.proaim.cache.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "员工控制器")
@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @ApiOperation(value = "查询某员工信息")
    @ApiImplicitParam(name = "id", value = "员工编号")
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmpById(id);
        return employee;
    }

    @ApiOperation(value = "查询某员工信息")
    @ApiImplicitParam(name = "lastName", value = "员工姓名")
    @GetMapping("/lastName/{lastName}")
    public Employee getEmployeeByName(@PathVariable("lastName") String lastName) {
        Employee employee = employeeService.getEmpByName(lastName);
        return employee;
    }

    @ApiOperation(value = "更新员工信息")
    @PutMapping("/")
    public String updateEmployee(Employee employee) {
        employeeService.updateEmp(employee);
        return "更新员工";
    }

    @ApiOperation(value = "删除员工")
    @DeleteMapping("/")
    public String deleteEmpById(Integer id) {
        employeeService.deleteEmpById(id);
        return "删除员工";
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/")
    public String insertEmployee(Employee employee) {
        employeeService.insertEmployee(employee);
        return "添加员工";
    }

}
