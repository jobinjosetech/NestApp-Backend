package com.example.NestDigitalApp.dao;

import com.example.NestDigitalApp.model.Employee;
import com.example.NestDigitalApp.model.Security;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecurityDao extends CrudRepository<Security, Integer> {
    @Query(value = "SELECT `id`, `email`, `name`, `password`, `phone`, `username` FROM `security` WHERE `username`= :username AND `password`= :password", nativeQuery = true)
    List<Security> SecurityLoginDetails(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT `id`, `email`, `name`,`phone`, `username`,`password` FROM `security` WHERE `id`= :id", nativeQuery = true)
    List<Security> GetSecurityProfile(@Param("id") int id);
}
