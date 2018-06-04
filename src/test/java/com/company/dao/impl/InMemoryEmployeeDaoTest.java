package com.company.dao.impl;

import com.company.db.EmployeeDatabase;
import com.company.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryEmployeeDaoTest {

    private InMemoryEmployeeDao dao;

    @Mock
    private EmployeeDatabase mockEmployeeDatabase;
    private static final Employee JOHN = new Employee("1aa", "John", "234234234");
    private static final Employee MICHAEL = new Employee("2bb", "Michael", "234234234");
    ;

    @Before
    public void setup() {
        dao = new InMemoryEmployeeDao(mockEmployeeDatabase);
    }

    @Test
    public void shouldReturnAllEmployees() {
        //given
        List<Employee> employeeList = buildEmployees();
        when(mockEmployeeDatabase.getAllEmployees()).thenReturn(employeeList);

        //when
        List<Employee> result = dao.getAllEmployees();

        //then
        verify(mockEmployeeDatabase).getAllEmployees();
        assertThat(result, is(equalTo(employeeList)));
    }

    @Test
    public void shouldAddEmployee() {
        //given

        //when
        dao.addEmployee(JOHN);

        //then
        verify(mockEmployeeDatabase).addEmployee(JOHN);
    }

    @Test
    public void shouldDeleteEmployee() {
        //given

        //when
        dao.deleteEmployeeById(JOHN.getId());

        //then
        verify(mockEmployeeDatabase).deleteEmployee(JOHN.getId());
    }

    @Test
    public void shouldFindEmployeeById() {
        //given
        when(mockEmployeeDatabase.getEmployeeById(JOHN.getId())).thenReturn(Optional.of(JOHN));
        when(mockEmployeeDatabase.getEmployeeById(MICHAEL.getId())).thenReturn(Optional.of(MICHAEL));

        //when
        Employee john = dao.findEmployeeById(JOHN.getId()).get();
        Employee michael = dao.findEmployeeById(MICHAEL.getId()).get();

        //then
        assertThat(john, is(equalTo(JOHN)));
        assertThat(michael, is(equalTo(MICHAEL)));
    }

    private List<Employee> buildEmployees() {
        return newArrayList(JOHN, MICHAEL);
    }
}
