package com.company.controller;

import com.company.data.EmployeeList;
import com.company.model.Employee;
import com.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @CrossOrigin
    @GetMapping("/list")
    @ResponseBody
    public EmployeeList listEmployees() {
         return new EmployeeList(employeeService.getAllEmployees());
    }

    @CrossOrigin
    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            produces = "application/json"
    )
    @ResponseBody
    public Employee addEmployee(@Valid @ModelAttribute Employee employee) {
        employeeService.addEmployee(employee);
        return employee;
    }

    @CrossOrigin
    @RequestMapping(value = "/delete/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json"
    )
    @ResponseBody
    public String addEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return id;
    }

}
