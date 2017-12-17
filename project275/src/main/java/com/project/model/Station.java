package com.project.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Station implements Serializable{

	@Id
	private String name;
	
	@Enumerated(EnumType.ORDINAL)
	private TrainType stationType;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "objTrainStation.station", cascade=CascadeType.ALL)
	private Set<TrainStationMapping> trainStation;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TrainType getStationType() {
		return stationType;
	}

	public void setStationType(TrainType stationType) {
		this.stationType = stationType;
	}

	public Set<TrainStationMapping> getTrainStation() {
		return trainStation;
	}

	public void setTrainStation(Set<TrainStationMapping> trainStation) {
		this.trainStation = trainStation;
	}
	
}
