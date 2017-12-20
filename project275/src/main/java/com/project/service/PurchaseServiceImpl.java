package com.project.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.BookingRepository;
import com.project.dao.StationRepository;
import com.project.dao.TicketRepository;
import com.project.dao.TrainRepository;
import com.project.model.Booking;
import com.project.model.Ticket;
import com.project.model.TrainType;
import com.project.model.User;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private StationRepository stationRepository;
	
	@Autowired
	private TrainRepository trainRepository;
	
	@Override
	public boolean purchase(Map<String, Object> payload) {
		try{
			Object of = payload.get("forward_ticket");
			Object or = payload.get("reverse_ticket");
    		Map<String, Object> mapF= (Map<String, Object>)of;
    		Ticket t = new Ticket();
    		t.setRoundTrip(payload.size() == 1 ? false : true);
    		t.setCancelled(false);
    		t.setTicketType(TrainType.valueOf((String)mapF.get("ticket_type")));
    		User u = new User();
    		u.setEmail("xyz@gmail.com");
    		t.setUser(u);
    		List<Booking> ls = new ArrayList<>();
    		List<Map<String, String>> bookings= (List<Map<String, String>>)mapF.get("trains");
    		DateFormat df = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy");
    		for(Map<String, String> booking : bookings)
    		{
    			Booking b = new Booking();
    			String sart = booking.get("arrival_time");
    			b.setArrivalDate(df.parse(sart));
    			String dart = booking.get("depat_time");
    			b.setDepartureDate(df.parse(dart));
    			//b.setDepartureDate(df.parse(booking.get("departure_time")));
    			b.setDestination(stationRepository.findByName(booking.get("destination")));
    			b.setOrigin(stationRepository.findByName(booking.get("origin")));
    			b.setTrain(trainRepository.findOne(booking.get("train_name")));
    			b.setPassengerCount((int)mapF.get("passenger_count"));
    			b.setTicket(t);
    			ls.add(b);
    		}
    		t.setPrice((int)mapF.get("price"));
    		if(or != null)
    		{
    			Map<String, Object> mapR= (Map<String, Object>)or;
    			t.setPrice( t.getPrice() + (int)mapR.get("price"));
    			if(t.getTicketType().getCost() != 2)
    				t.setTicketType(TrainType.valueOf((String)mapR.get("ticket_type")));
    			List<Map<String, String>> bookingsR= (List<Map<String, String>>)mapR.get("trains");
        		for(Map<String, String> booking : bookingsR)
        		{
        			Booking b = new Booking();
        			b.setArrivalDate(df.parse(booking.get("arrival_time")));
        			b.setDepartureDate(df.parse(booking.get("depat_time")));
        			b.setDestination(stationRepository.findByName(booking.get("destination")));
        			b.setOrigin(stationRepository.findByName(booking.get("origin")));
        			b.setTrain(trainRepository.findOne(booking.get("train_name")));
        			b.setPassengerCount((int)mapF.get("passenger_count"));
        			b.setTicket(t);
        			ls.add(b);
        		}
    		}
    		t.setBookings(ls);
    		ticketRepository.save(t);
		}
		catch(Exception e){
			
		}
		return false;
	}

}
