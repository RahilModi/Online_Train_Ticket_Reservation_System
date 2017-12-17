package com.project.model;

import java.util.HashMap;
import java.util.Map;

public enum Direction {

	SouthBound(0), NorthBound(1);
	
	private int direction;
	Direction(int v)
	{
		this.direction = v;
	}
	public int getDirection() {
		return direction;
	}

	private static Map<Integer, Direction> map = new HashMap<>();

    static {
        for (Direction d : Direction.values()) {
            map.put(d.direction, d);
        }
    }

    public static Direction valueOf(int direction) {
        return (Direction) map.get(direction);
    }
}
