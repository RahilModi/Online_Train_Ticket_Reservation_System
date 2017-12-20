package com.project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.annotation.Secured;

import com.project.model.Station;
import org.springframework.data.jpa.repository.Query;

public interface StationRepository extends CrudRepository<Station, String>{
	
	Station findByName(String name);

	@Query(value = "select s.name from station s", nativeQuery = true)
	List<String> findAllStation();
}