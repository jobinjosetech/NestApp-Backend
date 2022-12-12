package com.example.NestDigitalApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="leave2")
public class LeaveModel {
    @Id
    @GeneratedValue()
    private int id;
    private int empId;
    private int leaveStatus;
    private String fromDate;
    private String toDate;
    private String remarks;
    private String leaveType;
    private String applyDate;

    public LeaveModel() {
    }

    public LeaveModel(int id, int empId, int leaveStatus, String fromDate, String toDate, String remarks, String leaveType, String applyDate) {
        this.id = id;
        this.empId = empId;
        this.leaveStatus = leaveStatus;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.remarks = remarks;
        this.leaveType = leaveType;
        this.applyDate = applyDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(int leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }
}
