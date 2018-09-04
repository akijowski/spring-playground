package com.dish.springplayground.repositories;

import com.dish.springplayground.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
