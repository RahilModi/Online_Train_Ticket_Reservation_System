package com.project.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "train_station")
@AssociationOverrides({
	@AssociationOverride(name = "objTrainStation.train",
		joinColumns = @JoinColumn(name = "train_name")),
	@AssociationOverride(name = "objTrainStation.station",
		joinColumns = @JoinColumn(name = "station_name")) })
public class TrainStationMapping implements Serializable{

	
	@EmbeddedId
	private TrainStation objTrainStation;
	
	private Time departureTime;
	
	private Time arrivalTime;
	
	@Enumerated(EnumType.ORDINAL)
	private Direction direction;

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public TrainStation getObjTrainStation() {
		return objTrainStation;
	}

	public void setObjTrainStation(TrainStation objTrainStation) {
		this.objTrainStation = objTrainStation;
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		TrainStationMapping that = (TrainStationMapping) o;

		if (getObjTrainStation() != null ? !getObjTrainStation().equals(that.getObjTrainStation())
				: that.getObjTrainStation() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getObjTrainStation() != null ? getObjTrainStation().hashCode() : 0);
	}
	
}
