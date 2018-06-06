package com.company.db;

import com.company.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeDatabase {
    private final Map<String, Employee> database;

    @Autowired
    public EmployeeDatabase() {
        database = new HashMap<>();
    }

    public void addEmployee(Employee employee) {
        database.put(employee.getId(), employee);
    }

    public Optional<Employee> getEmployeeById(String id) {
        return Optional.ofNullable(database.get(id));
    }

    public void deleteEmployee(String id) {
        database.remove(id);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(database.values());
    }

    public void removeAllEmployees() {
        database.clear();
    }

    private void setupSampleData() {
        this.database.put("e1111", new Employee("e1111", "John Adams", "2145678890", "", "CEO"));
        this.database.put("e2222", new Employee("e2222", "Michael Johnson", "2145679990", "" ,"Business Analyst"));
        this.database.put("e3333", new Employee("e3333", "Amanda Johnson", "2145679990", "", "Software Developer"));
    }
}
