package com.project.model;

import java.util.HashMap;
import java.util.Map;

public enum TrainType {
	Regular(1),
	Express(2);
	
	private int cost;
	
	private TrainType(int value) {
		this.cost = value;
	}

	public int getCost() {
		return cost;
	}
	
	private static Map<Integer, TrainType> map = new HashMap<>();

    static {
        for (TrainType trainType : TrainType.values()) {
            map.put(trainType.cost, trainType);
        }
    }

    public static TrainType valueOf(int trainType) {
        return (TrainType) map.get(trainType);
    }

}
