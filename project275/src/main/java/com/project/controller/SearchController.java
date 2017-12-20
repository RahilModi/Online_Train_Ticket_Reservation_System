package com.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Ticket;
import com.project.service.PurchaseService;
import com.project.service.SearchService;

@RestController
public class SearchController {

	@Autowired
    private SearchService searchService;
	
	@Autowired
	private PurchaseService purchaseService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<Object> search(@RequestParam(value = "origin", required = true) String origin,
                                        @RequestParam(value = "destination", required = true) String destination,
                                        @RequestParam(value = "departure_datetime", required = true) String departure_datetime,
                                        @RequestParam(value = "passenger_count", required = true) String passenger_count,
                                        @RequestParam(value = "trip_type", required = true) boolean trip_type,
                                        @RequestParam(value = "r_departure_datetime", required = false) String r_departure_datetime,
                                        @RequestParam(value = "ticket_type", required = true) int ticket_type,
                                        @RequestParam(value = "is_exact_time", required = true) boolean is_exact_time,
                                        @RequestParam(value = "connection", required = true) int connection
                                        ) {

        try {
        	Map<String, List<Map<String, Object>>> map = null;
        	if (connection == 0)
        	{
        		List<Map<String, Object>> result = searchService.search(origin, destination, departure_datetime, Integer.parseInt(passenger_count), ticket_type);
            	map = new HashMap<>();
            	map.put("forward_trip", result);
            	if(trip_type)
            	{
            		result = searchService.search(destination, origin, r_departure_datetime, Integer.parseInt(passenger_count), ticket_type);
            		map.put("reverse_trip", result);
            	}                
        	}
        	else if(connection == 1)
        	{
        		List<Map<String, Object>> result = searchService.search(origin, destination, departure_datetime, Integer.parseInt(passenger_count), ticket_type);
            	map = new HashMap<>();
            	map.put("forward_trip", result);
            	if(trip_type)
            	{
            		result = searchService.search(destination, origin, r_departure_datetime, Integer.parseInt(passenger_count), ticket_type);
            		map.put("reverse_trip", result);
            	}
        	}
        	else
        	{
        		List<Map<String, Object>> result = searchService.search(origin, destination, departure_datetime, Integer.parseInt(passenger_count), ticket_type);
            	map = new HashMap<>();
            	map.put("forward_trip", result);
            	if(trip_type)
            	{
            		result = searchService.search(destination, origin, r_departure_datetime, Integer.parseInt(passenger_count), ticket_type);
            		map.put("reverse_trip", result);
            	}
        	}
        	return new ResponseEntity<Object>(map, HttpStatus.OK);	
        }catch(Exception e){
            return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public ResponseEntity<Object> purchase(@RequestBody Map<String, Object> payload){
    	try{
    		payload = purchaseService.purchase(payload);
    		return new ResponseEntity<Object>(payload == null ? false : true, HttpStatus.OK);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}
