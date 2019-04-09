package com.proaim.jpa.repository;

import com.proaim.jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// 继承JpaRepository来完成对数据库的操作
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public Employee findByLastName(String lastName);

}
