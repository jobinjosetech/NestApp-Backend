package com.example.NestDigitalApp.controller;

import com.example.NestDigitalApp.dao.EmployeeDao;
import com.example.NestDigitalApp.dao.Leave1Dao;
import com.example.NestDigitalApp.dao.SecurityDao;
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
public class AdminController {
    @Autowired
    private EmployeeDao empdao;
    @Autowired
    private Leave1Dao l1dao;
    @Autowired
    private SecurityDao sdao;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");

    @PostMapping(path = "/addEmployee", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> AddEmployee(@RequestBody Employee emp){
        List<Employee> emp1 = (List<Employee>) empdao.UserLoginDetailsByCred(emp.getUsername(), emp.getPassword(), emp.getEmpCode());
        HashMap<String, String> hashMap = new HashMap<>();
        if(emp1.size()==0){
            LocalDateTime now = LocalDateTime.now();
            empdao.save(emp);
            List<Employee> result = (List<Employee>) empdao.UserLoginDetailsById(emp.getEmpCode());
            Leaves1 l1 = new Leaves1();
            l1.setEmpId(String.valueOf(result.get(0).getId()));
            l1.setYear(dtf.format(now));
            l1.setCasualLeave(20);
            l1.setSickLeave(7);
            l1.setSpecialLeave(3);
            l1dao.save(l1);
            hashMap.put("status","success");
        }else{
            hashMap.put("status","failed");
        }
        return hashMap;
    }


    @PostMapping(path = "/searchEmployee", consumes = "application/json", produces = "application/json")
    public List<Employee> SearchEmployee(@RequestBody Employee emp){
        return  (List<Employee>) empdao.SearchEmployee(emp.getName());
    }

    @PostMapping(path = "/editEmployee", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> EditEmployee(@RequestBody Employee emp){
        System.out.println(emp.getId());
        empdao.EditEmployee(emp.getId(), emp.getDesignation(), emp.getEmail(), emp.getEmpCode(), emp.getName(), emp.getPassword(), emp.getPhone(), emp.getUsername());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("status","success");
        return hashMap;
    }

    @GetMapping("/viewAllEmployee")
    public List<Employee> GetAllEmployee(){
        return (List<Employee>) empdao.findAll();
    }

    @PostMapping(path = "/addSecurity", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> AddSecurity(@RequestBody Security sc){
        sdao.save(sc);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("status","success");
        return hashMap;
    }
}
