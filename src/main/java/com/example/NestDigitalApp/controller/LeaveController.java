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
        LocalDateTime now = LocalDateTime.now();
        lv.setApplyDate(dtf.format(now));
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

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/updateLeaves", produces = "application/json", consumes = "application/json")
    public HashMap<String, String> UpdateLeaves(@RequestBody LeaveModel lv) throws ParseException {
        String leaveType = lv.getLeaveType();
        System.out.println(lv.getEmpId());
        List<Leaves1> l1 = (List<Leaves1>) l1dao.GetLeaveDetails(lv.getEmpId());
        System.out.println(l1.get(0).getSpecialLeave());
        System.out.println(lv.getFromDate());
        System.out.println(lv.getToDate());
        String[] temp_from_date = lv.getFromDate().split("-", 3);
        String from_date = temp_from_date[2]+"-"+temp_from_date[1]+"-"+temp_from_date[0];
        String[] temp_to_date = lv.getToDate().split("-", 3);
        String to_date = temp_to_date[2]+"-"+temp_to_date[1]+"-"+temp_to_date[0];
        LocalDate dateBefore = LocalDate.parse(from_date);
        LocalDate dateAfter = LocalDate.parse(to_date);
        int daysOfLeave = (int) ChronoUnit.DAYS.between(dateBefore, dateAfter)+1;
        HashMap<String, String> hashMap = new HashMap<>();
            if(leaveType.equals("casualLeave") && (l1.get(0).getCasualLeave()-daysOfLeave)>=0){
                ldao.UpdateLeaves(lv.getId(), lv.getLeaveStatus());
                l1dao.UpdateLeave(lv.getEmpId(),(l1.get(0).getCasualLeave()-daysOfLeave),l1.get(0).getSickLeave(),l1.get(0).getSpecialLeave());
                hashMap.put("status","success");
            } else if (leaveType.equals("sickLeave") && (l1.get(0).getSickLeave()-daysOfLeave)>=0) {
                ldao.UpdateLeaves(lv.getId(), lv.getLeaveStatus());
                l1dao.UpdateLeave(lv.getEmpId(),l1.get(0).getCasualLeave(),(l1.get(0).getSickLeave()-daysOfLeave),l1.get(0).getSpecialLeave());
                hashMap.put("status","success");
            }else if (leaveType.equals("specialLeave") && (l1.get(0).getSpecialLeave()-daysOfLeave)>=0){
                ldao.UpdateLeaves(lv.getId(), lv.getLeaveStatus());
                l1dao.UpdateLeave(lv.getEmpId(),l1.get(0).getCasualLeave(),l1.get(0).getSickLeave(),(l1.get(0).getSpecialLeave()-daysOfLeave));
                hashMap.put("status","success");
            }else if(lv.getLeaveStatus()==-1){
                ldao.UpdateLeaves(lv.getId(), lv.getLeaveStatus());
                hashMap.put("status","success");
            }
            else{
                hashMap.put("status","failed");
            }

        return hashMap;
    }

}
