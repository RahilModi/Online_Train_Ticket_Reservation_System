package com.project.service;

import com.project.model.Station;

public interface ExpressLookupService {
	int getTravelTime(Station origin, Station destination);	
}
