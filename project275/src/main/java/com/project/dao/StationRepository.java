package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Station;

public interface StationRepository extends JpaRepository<Station, String>{

	//@Query("Select s from station s")
	List<Station> findAll();
}