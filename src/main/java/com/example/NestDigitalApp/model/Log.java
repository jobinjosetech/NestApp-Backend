package com.example.NestDigitalApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue()
    private int id;
    private int empId;
    private String empCode;
    private String entryTime;
    private String exitTime;
    private String Date;

    public Log() {
    }

    public Log(int id, int empId, String empCode, String entryTime, String exitTime, String date) {
        this.id = id;
        this.empId = empId;
        this.empCode = empCode;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        Date = date;
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

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
