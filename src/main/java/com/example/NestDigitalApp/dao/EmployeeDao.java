package com.example.NestDigitalApp.dao;

import com.example.NestDigitalApp.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {
}
