package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Station;
import org.springframework.data.jpa.repository.Query;

public interface StationRepository extends JpaRepository<Station, String>{

	@Query(value = "select s.name from station s", nativeQuery = true)
	List<String> findAllStation();
}