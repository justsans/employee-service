package com.company.dao.impl;

import com.company.dao.EmployeeDao;
import com.company.db.EmployeeDatabase;
import com.company.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InMemoryEmployeeDao implements EmployeeDao {
    private final EmployeeDatabase database;

    public InMemoryEmployeeDao(EmployeeDatabase database) {
        this.database = database;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return database.getAllEmployees();
    }

    @Override
    public void addEmployee(Employee employee) {
        database.addEmployee(employee);
    }

    @Override
    public Optional<Employee> findEmployeeById(String id) {
        return database.getEmployeeById(id);
    }

    @Override
    public void deleteEmployeeById(String id) {
        database.deleteEmployee(id);
    }
}
