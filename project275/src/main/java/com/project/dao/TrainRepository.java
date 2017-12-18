package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.project.model.Train;

public interface TrainRepository extends JpaRepository<Train, String>{

	@Modifying
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	@Query("update train t set t.max_capacity = :max_cap")
	void updateTrain(@Param("max_cap") int max_cap);
	
}
