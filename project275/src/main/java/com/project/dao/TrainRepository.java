package com.project.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.model.Train;

import java.util.List;

public interface TrainRepository extends CrudRepository<Train, String> {

	@Modifying
//	@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	@Query(value = "update train t set t.max_capacity = :max_cap", nativeQuery = true)
	public void updateTrain(@Param("max_cap") int max_cap);

	@Modifying
//	@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	@Query(value = "select * from train", nativeQuery = true)
	public List<Train> getAllTrains();

	@Modifying
//	@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	@Query(value = "SET SQL_SAFE_UPDATES = 0", nativeQuery = true)
	public void disableSafeUpdate();

	@Modifying
//	@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	@Query(value = "SET SQL_SAFE_UPDATES = 1", nativeQuery = true)
	public void enableSafeUpdate();

}
