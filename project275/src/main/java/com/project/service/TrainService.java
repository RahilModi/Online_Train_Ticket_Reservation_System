package com.project.service;

import java.util.List;

import com.project.model.Ticket;


public interface TrainService {

	List<Ticket> search(String origin, String destination, String departure_datetime, int passenger_count);

}
