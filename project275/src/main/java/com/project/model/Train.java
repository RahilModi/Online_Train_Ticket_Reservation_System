package com.project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

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
	
}


