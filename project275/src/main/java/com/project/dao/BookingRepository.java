package com.project.dao;

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
}
