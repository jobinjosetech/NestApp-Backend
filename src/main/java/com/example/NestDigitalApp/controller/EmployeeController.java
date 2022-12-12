package com.example.NestDigitalApp.controller;

import com.example.NestDigitalApp.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeDao empdao;

    @GetMapping("/")
    public String HomeRoute(){
        return "Welcome to Nest Digital App";
    }


}
