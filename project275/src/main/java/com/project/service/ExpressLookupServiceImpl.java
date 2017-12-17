package com.project.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.model.Station;

@Service
public class ExpressLookupServiceImpl implements ExpressLookupService{

	private static final int EXPRESS_STATIONS = 6;
	private int[][] lookup;
	private Map<String, Integer> map = new HashMap<String, Integer>();
	
	public ExpressLookupServiceImpl() {
		lookup = new int[EXPRESS_STATIONS][EXPRESS_STATIONS];
		lookup[0][1] = 25;lookup[0][2] = 53;lookup[0][3] = 81;lookup[0][4] = 109;lookup[0][5] = 137;
		
		char c = 'A'; int v = 0;
		do{
			map.put(String.valueOf(c), v++);
			c+= 5;
		}while( c <= 'Z');
		
		for(int i = 1; i < lookup.length; i++)
			for(int j = i+1; j < lookup.length; j++)
				lookup[i][j] = lookup[i-1][j-1];
	}
	
	

	public int[][] getLookup() {
		return lookup;
	}

	public void setLookup(int[][] lookup) {
		this.lookup = lookup;
	}

	@Override
	public int getTravelTime(Station origin, Station destination) {
		Station temp = origin;
		if(origin.getName().compareTo(destination.getName()) > 0 )
		{
			origin = destination;
			destination = temp;
		}
		if(this.map.get(origin.getName()) == null || this.map.get(destination.getName()) == null) return -1;
		return this.lookup[this.map.get(origin.getName())][map.get(destination.getName())];
	}

	
}
