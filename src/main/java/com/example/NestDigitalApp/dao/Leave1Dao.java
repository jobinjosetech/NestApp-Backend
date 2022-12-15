package com.example.NestDigitalApp.dao;

import com.example.NestDigitalApp.model.LeaveModel;
import com.example.NestDigitalApp.model.Leaves1;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface Leave1Dao extends CrudRepository<Leaves1, Integer>{
    @Query(value = "SELECT `id`, `casual_leave`, `emp_id`, `sick_leave`, `special_leave`, `year` FROM `leavedetails` WHERE `emp_id`= :id AND `year`= :year", nativeQuery = true)
    List<Leaves1> GetLeaveDetails(@Param("id") Integer id, @Param("year") String year);

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leavedetails` SET `casual_leave`= :casualLeave,`sick_leave`= :sickLeave,`special_leave`= :specialLeave WHERE `emp_id`= :id", nativeQuery = true)
    void UpdateLeave(@Param("id") Integer id, @Param("casualLeave") Integer casualLeave, @Param("sickLeave") Integer sickLeave, @Param("specialLeave") Integer specialLeave);


}
