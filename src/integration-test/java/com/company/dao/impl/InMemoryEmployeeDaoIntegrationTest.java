package com.company.dao.impl;

import com.company.dao.EmployeeDao;
import com.company.db.EmployeeDatabase;
import com.company.model.Employee;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InMemoryEmployeeDaoIntegrationTest {

    @Autowired
    private EmployeeDatabase database;

    @Autowired
    private EmployeeDao inMemoryEmployeeDao;

    @Test
    public void shouldAddEmployee() {
        inMemoryEmployeeDao.addEmployee(new Employee("1", "John", "222-222-2222", "", ""));

        List<Employee> result = inMemoryEmployeeDao.getAllEmployees();
        assertThat(result.size(), is(1));
    }

    @Test
    public void shouldFindEmployeeById() {
        Employee john = new Employee("1", "John", "222-222-2222", "", "");
        database.addEmployee(john);

        Employee employee = inMemoryEmployeeDao.findEmployeeById("1").get();

        assertThat(employee, is(equalTo(john)));
    }

    @Test
    public void shouldReturnAllEmployees() {
        database.addEmployee(new Employee("1", "John", "222-222-2222", "", ""));
        database.addEmployee(new Employee("2", "Michael", "222-222-2222", "", ""));

        List<Employee> result = inMemoryEmployeeDao.getAllEmployees();

        assertThat(result.size(), is(5));
    }

    @Test
    public void shouldDeleteEmployee() {
        database.addEmployee(new Employee("1", "John", "222-222-2222", "", ""));
        database.addEmployee(new Employee("2", "Michael", "222-222-2222", "", ""));

        inMemoryEmployeeDao.deleteEmployeeById("1");

        assertThat(database.getEmployeeById("1").isPresent(), is(false));
        assertThat(database.getEmployeeById("2").isPresent(), is(true));
    }

    @After
    public void cleanup() {
        database.removeAllEmployees();
    }
}
