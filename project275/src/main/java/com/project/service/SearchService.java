package com.project.service;

import java.util.List;
import java.util.Map;

import com.project.model.Ticket;

public interface SearchService {

	List<Map<String, Object>> search(String origin, String destination, String departure_datetime, int passenger_count, int ticket_type);
	
	List<Map<String, Object>> search(String origin, String destination, String departure_datetime, int passenger_count, int conn, int train_type);
	
	int cost(String o, String d, int pcount, int trainfare);
}
