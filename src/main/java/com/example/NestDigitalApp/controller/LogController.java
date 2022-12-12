package com.example.NestDigitalApp.controller;

import com.example.NestDigitalApp.dao.EmployeeDao;
import com.example.NestDigitalApp.dao.Leave1Dao;
import com.example.NestDigitalApp.dao.LogDao;
import com.example.NestDigitalApp.dao.VisitorsLogDao;
import com.example.NestDigitalApp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@RestController
public class LogController {
    @Autowired
    private LogDao ldao;
    @Autowired
    private EmployeeDao empdao;
    @Autowired
    private VisitorsLogDao vldao;
    @Autowired
    private Leave1Dao l1dao;

    DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addEmpEntryLog")
    public HashMap<String, String> addEmpEntryLogs(@RequestBody Log l){
        LocalDateTime now = LocalDateTime.now();
        List<Employee> emp = (List<Employee>) empdao.UserLoginDetailsById(l.getEmpCode());
        HashMap<String, String> hashMap = new HashMap<>();
            if(emp.size()==0){
                hashMap.put("status","failed");
            }else{
                List<LeaveModel> result = (List<LeaveModel>) l1dao.GetLeaveUpdates(emp.get(0).getId(),df.format(now));
                if(result.size()==0 || result.get(0).getLeaveStatus()!=1){
                    l.setEmpId(emp.get(0).getId());
                    l.setDate(df.format(now));
                    l.setEntryTime(tf.format(now));
                    ldao.save(l);
                    hashMap.put("status","success");
                }else{
                    hashMap.put("status","failed");
                }
            }
        return hashMap;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addEmpExitLog")
    public HashMap<String, String> addEmpExitLogs(@RequestBody Log l){
        LocalDateTime now = LocalDateTime.now();
        List<Employee> emp = (List<Employee>) empdao.UserLoginDetailsById(l.getEmpCode());
        HashMap<String, String> hashMap = new HashMap<>();
        if(emp.size()==0){
            hashMap.put("status","failed");
        }else{
            l.setExitTime(tf.format(now));
            ldao.UpdateExitEntry(emp.get(0).getId(), l.getExitTime(),df.format(now));
            hashMap.put("status","success");
        }
        return hashMap;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addVisEntryLog")
    public HashMap<String, String> addVisEntryLogs(@RequestBody VisitorLog vl){
        LocalDateTime now = LocalDateTime.now();
        HashMap<String, String> hashMap = new HashMap<>();
        vl.setDate(df.format(now));
        vl.setEntryTime(tf.format(now));
        vldao.save(vl);
        hashMap.put("status","success");
        return hashMap;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addVisExitLog")
    public HashMap<String, String> addVisExitLogs(@RequestBody VisitorLog vl){
        LocalDateTime now = LocalDateTime.now();
        HashMap<String, String> hashMap = new HashMap<>();
            vl.setExitTime(tf.format(now));
            vldao.UpdateExitEntry(vl.getPhone(), vl.getExitTime());
            hashMap.put("status","success");
        return hashMap;
    }

}
