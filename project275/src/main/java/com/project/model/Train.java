package com.project.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@EnableAutoConfiguration
public class Train implements Serializable{

	@Id
	private String name;
	
	@Enumerated(EnumType.ORDINAL)
	private TrainType type;
	
	private int maxCapacity;
	
	@Enumerated(EnumType.ORDINAL)
	private Direction direction;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "objTrainStation.train", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
	@JsonIgnoreProperties("trainStation")
	@JsonManagedReference
	private Set<TrainStationMapping> trainStation;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TrainType getType() {
		return type;
	}

	public void setType(TrainType type) {
		this.type = type;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Set<TrainStationMapping> getTrainStation() {
		return trainStation;
	}

	public void setTrainStation(Set<TrainStationMapping> trainStation) {
		this.trainStation = trainStation;
	} 
	
}