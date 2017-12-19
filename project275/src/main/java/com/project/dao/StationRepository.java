package com.project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.annotation.Secured;

import com.project.model.Station;

public interface StationRepository extends CrudRepository<Station, String>{

	//@Query("Select s from station s")
	@Secured(value = "ROLE_USER")
	List<Station> findAll();
}