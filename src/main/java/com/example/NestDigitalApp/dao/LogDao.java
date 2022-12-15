package com.example.NestDigitalApp.dao;

import com.example.NestDigitalApp.model.Log;
import com.example.NestDigitalApp.model.VisitorLog;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface LogDao extends CrudRepository<Log, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE `log` SET `exit_time`= :time WHERE `emp_id` = :empId AND `date`= :date", nativeQuery = true)
    void UpdateExitEntry(@Param("empId") Integer empId, @Param("time") String time, @Param("date") String date);

    @Query(value = "SELECT `id`, `date`, `emp_code`, `emp_id`, `entry_time`, `exit_time` FROM `log` WHERE `emp_id`= :empId ORDER BY `date` DESC" , nativeQuery = true)
    List<Log> GetEmpLog(@Param("empId") Integer empId);

    @Query(value = "SELECT l.`id`, l.`date`, l.`emp_code`, l.`emp_id`, l.`entry_time`, l.`exit_time`, e.`name`, e.emp_code FROM `log`AS l JOIN `employee`AS e ON l.`emp_id` = e.`id` ORDER BY `date` DESC" , nativeQuery = true)
    List<Map<String,String>> GetSecEmpLog();

    @Query(value = "SELECT l.`id`, l.`date`, l.`emp_code`, l.`emp_id`, l.`entry_time`, l.`exit_time`, e.name FROM `log` AS l JOIN `employee` AS e ON l.`emp_id` = e.`id` WHERE l.`date`= :date", nativeQuery = true)
    List<Map<String, String>> SearchEmpLog(@Param("date") String date);

}
