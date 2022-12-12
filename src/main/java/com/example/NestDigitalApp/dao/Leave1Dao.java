package com.example.NestDigitalApp.dao;

import com.example.NestDigitalApp.model.LeaveModel;
import com.example.NestDigitalApp.model.Leaves1;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Leave1Dao extends CrudRepository<Leaves1, Integer>{
    @Query(value = "SELECT `id`, `casual_leave`, `emp_id`, `sick_leave`, `special_leave`, `year` FROM `leavedetails` WHERE `emp_id`= :id", nativeQuery = true)
    List<Leaves1> GetLeaveDetails(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leavedetails` SET `casual_leave`= :casualLeave,`sick_leave`= :sickLeave,`special_leave`= :specialLeave WHERE `emp_id`= :id", nativeQuery = true)
    void UpdateLeave(@Param("id") Integer id, @Param("casualLeave") Integer casualLeave, @Param("sickLeave") Integer sickLeave, @Param("specialLeave") Integer specialLeave);

    @Query(value = "SELECT `id`, `apply_date`, `emp_id`, `from_date`, `leave_status`, `leave_type`, `remarks`, `to_date` FROM `leave2` WHERE `emp_id`= :empId  AND :date BETWEEN `from_date` AND `to_date`", nativeQuery = true)
    List<LeaveModel> GetLeaveUpdates(@Param("empId") Integer empId, @Param("date") String date);
}
