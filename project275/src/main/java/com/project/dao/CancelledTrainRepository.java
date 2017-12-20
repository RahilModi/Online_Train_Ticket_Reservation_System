package com.project.dao;

import com.project.model.CancelledDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CancelledTrainRepository extends JpaRepository<CancelledDate, Long> {

	@Query(value = "select cdt.train_name from cancelled_date cd inner join cancelled_date_train cdt on cd.id = cdt.cancelled_date_id where cd.date = :date", nativeQuery = true)
    List<String> findTrainsByDate(@Param("date")Date date);
	
	CancelledDate findByDate(@Param("date")Date date);
    
}
