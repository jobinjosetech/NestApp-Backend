package com.example.NestDigitalApp.dao;

import com.example.NestDigitalApp.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {
    @Query(value = "SELECT `id`, `designation`, `email`, `emp_code`, `name`, `password`, `phone`, `username` FROM `employee` WHERE `username`= :username AND `password`= :password", nativeQuery = true)
    List<Employee> UserLoginDetails(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT `id`, `designation`, `email`, `emp_code`, `name`, `password`, `phone`, `username` FROM `employee` WHERE `name` LIKE %:name%", nativeQuery = true)
    List<Employee> SearchEmployee(@Param("name") String name);
}
