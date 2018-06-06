package com.company.service;

import com.company.dao.EmployeeDao;
import com.company.model.Employee;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class EmployeeService {
    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getAllEmployees() {
        log.info("Fetch employee");
        return employeeDao.getAllEmployees();
    }
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
        log.info("Added employee" + employee);
    }
    public void deleteEmployee(String id) {
        employeeDao.deleteEmployeeById(id);
        log.info("Deleted employee with id" + id);
    }
}

