package com.proaim.jpa.service;

import com.proaim.jpa.entity.Employee;
import com.proaim.jpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    public List<Employee> selectEmployee() {
        return employeeRepository.findAll();
    }

    public Employee selectEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee selectEmployeeByName(String lastName) {
        return employeeRepository.findByLastName(lastName);
    }
}
