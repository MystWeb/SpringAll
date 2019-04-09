package com.proaim.cache.service;

import com.proaim.cache.entity.Employee;

public interface EmployeeService {
    Employee getEmpById(Integer id);

    Employee getEmpByName(String lastName);

    Employee updateEmp(Employee employee);

    void deleteEmpById(Integer id);

    Employee insertEmployee(Employee employee);
}
