package com.example.NestDigitalApp.dao;

import com.example.NestDigitalApp.model.Leaves1;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface Leave1Dao extends CrudRepository<Leaves1, Integer>{
}
