package com.example.NestDigitalApp.controller;

import com.example.NestDigitalApp.dao.EmployeeDao;
import com.example.NestDigitalApp.dao.Leave1Dao;
import com.example.NestDigitalApp.model.Employee;
import com.example.NestDigitalApp.model.Leaves1;
import com.example.NestDigitalApp.model.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeDao empdao;

    @GetMapping("/")
    public String HomeRoute(){
        return "Welcome to Nest Digital App";
    }

    @PostMapping(path = "/employeeLogin", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> EmployeeLogin(@RequestBody Employee emp){
        HashMap<String, String> hashMap = new HashMap<>();
        List<Employee> empDetails = empdao.UserLoginDetails(emp.getUsername(),emp.getPassword());
        if(empDetails.size() == 0){
            hashMap.put("status","failed");
        }else{
            hashMap.put("status","success");
            hashMap.put("userInfo",String.valueOf(empDetails.get(0).getId()));
        }
        return hashMap;
    }

    @PostMapping(path = "/employeeProfile", consumes = "application/json", produces = "application/json")
    public List<Employee> getEmployeeProfile(@RequestBody Employee emp){
        return (List<Employee>) empdao.GetEmployeeProfile(emp.getId());
    }
}
