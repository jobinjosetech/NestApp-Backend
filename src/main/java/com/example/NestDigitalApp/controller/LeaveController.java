package com.example.NestDigitalApp.controller;

import com.example.NestDigitalApp.dao.LeaveDao;
import com.example.NestDigitalApp.model.Employee;
import com.example.NestDigitalApp.model.LeaveModel;
import com.example.NestDigitalApp.model.Leaves1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LeaveController {
    @Autowired
    private LeaveDao ldao;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/applyLeave", produces = "application/json", consumes = "application/json")
    public HashMap<String, String> ApplyLeave(@RequestBody LeaveModel lv){
        ldao.save(lv);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("status","success");
        return hashMap;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getAllLeaves")
    public List<Map<String, String>> GetAllLeaves(){
        return ldao.GetAllLeaves();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/getEmployeeLeaves", produces = "application/json", consumes = "application/json")
    public List<LeaveModel> GetEmployeeLeaves(@RequestBody Employee emp){
        return ldao.GetEmployeeLeaves(Integer.valueOf(emp.getId()));
    }

}
