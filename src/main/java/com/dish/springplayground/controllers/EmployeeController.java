package com.dish.springplayground.controllers;

import com.dish.springplayground.model.Employee;
import com.dish.springplayground.repositories.EmployeeRepository;
import com.dish.springplayground.views.EmployeeViews;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    @JsonView(EmployeeViews.NoSalary.class)
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        repository.findAll().forEach(employees::add);
        return employees;
    }

    @GetMapping("/admin/employees")
    public List<Employee> getAdminEmployees() {
        List<Employee> employees = new ArrayList<>();
        repository.findAll().forEach(employees::add);
        return employees;
    }
}
