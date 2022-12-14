package com.example.NestDigitalApp.controller;

import com.example.NestDigitalApp.dao.Leave1Dao;
import com.example.NestDigitalApp.dao.LeaveDao;
import com.example.NestDigitalApp.model.Employee;
import com.example.NestDigitalApp.model.LeaveModel;
import com.example.NestDigitalApp.model.Leaves1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LeaveController {
    @Autowired
    private LeaveDao ldao;
    @Autowired
    private Leave1Dao l1dao;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/applyLeave", produces = "application/json", consumes = "application/json")
    public HashMap<String, String> ApplyLeave(@RequestBody LeaveModel lv){
        HashMap<String, String> hashMap = new HashMap<>();
        String leaveType = lv.getLeaveType();
        List<Leaves1> l1 = (List<Leaves1>) l1dao.GetLeaveDetails(lv.getEmpId());
        LocalDate dateBefore = LocalDate.parse(lv.getFromDate());
        LocalDate dateAfter = LocalDate.parse(lv.getToDate());
        int daysOfLeave = (int) ChronoUnit.DAYS.between(dateBefore, dateAfter)+1;
        if(leaveType.equals("casualLeave") && (l1.get(0).getCasualLeave()-daysOfLeave)>=0){
            LocalDateTime now = LocalDateTime.now();
            lv.setApplyDate(dtf.format(now));
            lv.setLeaveStatus(0);
            ldao.save(lv);
            hashMap.put("status","success");
        } else if (leaveType.equals("sickLeave") && (l1.get(0).getSickLeave()-daysOfLeave)>=0) {
            LocalDateTime now = LocalDateTime.now();
            lv.setApplyDate(dtf.format(now));
            lv.setLeaveStatus(0);
            ldao.save(lv);
            hashMap.put("status","success");
        }else if (leaveType.equals("specialLeave") && (l1.get(0).getSpecialLeave()-daysOfLeave)>=0){
            LocalDateTime now = LocalDateTime.now();
            lv.setApplyDate(dtf.format(now));
            lv.setLeaveStatus(0);
            ldao.save(lv);
            hashMap.put("status","success");
        }
        else{
            hashMap.put("status","failed");
        }
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

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/updateLeaves", produces = "application/json", consumes = "application/json")
    public HashMap<String, String> UpdateLeaves(@RequestBody LeaveModel lv) throws ParseException {
        HashMap<String, String> hashMap = new HashMap<>();
        List<Leaves1> l1 = (List<Leaves1>) l1dao.GetLeaveDetails(lv.getEmpId());
        LocalDate dateBefore = LocalDate.parse(lv.getFromDate());
        LocalDate dateAfter = LocalDate.parse(lv.getToDate());
        int daysOfLeave = (int) ChronoUnit.DAYS.between(dateBefore, dateAfter)+1;
        ldao.UpdateLeaves(lv.getId(), lv.getLeaveStatus());
        l1dao.UpdateLeave(lv.getEmpId(),(l1.get(0).getCasualLeave()-daysOfLeave),l1.get(0).getSickLeave(),l1.get(0).getSpecialLeave());
        hashMap.put("status","success");
        return hashMap;
    }

}
