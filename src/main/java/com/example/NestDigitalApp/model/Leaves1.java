package com.example.NestDigitalApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leavedetails")
public class Leaves1 {
    @Id
    @GeneratedValue()
    private int id;
    private String empId;
    private String year;
    private int casualLeave;
    private int sickLeave;
    private int specialLeave;

    public Leaves1() {
    }

    public Leaves1(int id, String empId, String year, int casualLeave, int sickLeave, int specialLeave) {
        this.id = id;
        this.empId = empId;
        this.year = year;
        this.casualLeave = casualLeave;
        this.sickLeave = sickLeave;
        this.specialLeave = specialLeave;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getCasualLeave() {
        return casualLeave;
    }

    public void setCasualLeave(int casualLeave) {
        this.casualLeave = casualLeave;
    }

    public int getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(int sickLeave) {
        this.sickLeave = sickLeave;
    }

    public int getSpecialLeave() {
        return specialLeave;
    }

    public void setSpecialLeave(int specialLeave) {
        this.specialLeave = specialLeave;
    }
}
