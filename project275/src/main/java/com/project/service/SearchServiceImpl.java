package com.project.service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.TrainRepository;
import com.project.model.Ticket;
import com.project.model.Train;

@Service
public class SearchServiceImpl implements TrainService{

	@Autowired
	private TrainRepository trainRepo;
	
//	@Autowired
//	private Cance
	
	@Override
	public List<Ticket> search(String origin, String destination, String departure_datetime, int passenger_count) {

		
		List<String> trains= null;
		List<String> cancelledTrains = null;
		try {
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        Date d = f.parse(departure_datetime);
	        DateFormat dfdate = new SimpleDateFormat("yyyy-MM-dd");
	        DateFormat dftime = new SimpleDateFormat("hh:mm:ss");
	        Date date = dfdate.parse(departure_datetime);
	        System.out.println("Time: " + dftime.format(d));
	        Time time = Time.valueOf(dftime.format(d));
			trains = this.trainRepo.findDirectTrains(origin, destination, date, time, passenger_count, origin.compareTo(destination) > 0 ? 1 : 0);
			cancelledTrains =  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
}
