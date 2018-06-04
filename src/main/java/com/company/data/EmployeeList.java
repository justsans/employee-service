package com.company.data;

import com.company.model.Employee;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeList {
    private final List<Employee> employees;
}
