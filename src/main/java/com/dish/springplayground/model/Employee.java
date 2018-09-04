package com.dish.springplayground.model;

import com.dish.springplayground.views.EmployeeViews;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(EmployeeViews.NoSalary.class)
    private Long id;
    @JsonView(EmployeeViews.NoSalary.class)
    private String name;
    private int salary;
    @JsonView(EmployeeViews.NoSalary.class)
    private Long managerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return getSalary() == employee.getSalary() &&
                Objects.equals(getId(), employee.getId()) &&
                Objects.equals(getName(), employee.getName()) &&
                Objects.equals(getManagerId(), employee.getManagerId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getSalary(), getManagerId());
    }
}
