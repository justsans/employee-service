package com.company.dao;

import com.company.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
    List<Employee> getAllEmployees();
    void addEmployee(Employee employee);
    Optional<Employee> findEmployeeById(String id);
    void deleteEmployeeById(String id);
}
