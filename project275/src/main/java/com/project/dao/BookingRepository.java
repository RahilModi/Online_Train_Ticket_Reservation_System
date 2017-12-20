package com.project.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.model.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer>{

	@Modifying
	@Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
	@Secured(value = "ROLE_ADMIN")
	void deleteAll();
	
	@Query(value = " select * from booking where train_id = :train_id and datediff(departure_date, :d) = 0", nativeQuery = true )
	List<Booking> findAllByTrainIdDepartureDate(String train_id, Date d);
	
}
