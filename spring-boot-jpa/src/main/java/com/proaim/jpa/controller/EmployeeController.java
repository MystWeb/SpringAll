package com.proaim.jpa.controller;

import com.proaim.jpa.entity.Employee;
import com.proaim.jpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    @Transactional
    public Employee createEmployee(Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/employee/select/{id}")
    public Employee selectEmployeeById(@PathVariable("id") Integer id) {
        return employeeService.selectEmployeeById(id);
    }

    @GetMapping("/employee/{lastName}")
    public Employee selectEmployeeByName(@PathVariable("lastName") String lastName) {
        return employeeService.selectEmployeeByName(lastName);
    }

    @PutMapping("/employee")
    @Transactional
    public Employee updateEmployeeById(Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/employee/{id}")
    @Transactional
    public String deleteEmployeeById(@PathVariable("id") Integer id) {
        try {
            employeeService.deleteEmployee(id);
            return "删除成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "未知的错误";
        }
    }
}
