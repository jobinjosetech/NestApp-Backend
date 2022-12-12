package com.example.NestDigitalApp.dao;

import com.example.NestDigitalApp.model.Log;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LogDao extends CrudRepository<Log, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE `log` SET `exit_time`= :time WHERE `emp_id` = :empId AND `date`= :date", nativeQuery = true)
    void UpdateExitEntry(@Param("empId") Integer empId, @Param("time") String time, @Param("date") String date);

}
