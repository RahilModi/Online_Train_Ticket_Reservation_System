package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Ticket;
import com.project.service.TrainService;

@RestController
public class SearchController {

	@Autowired
    private TrainService trainService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@RequestParam(value = "origin", required = true) String origin,
                                        @RequestParam(value = "destination", required = true) String destination,
                                        @RequestParam(value = "departure_datetime", required = true) String departure_datetime,
                                        @RequestParam(value = "passenger_count", required = true) String passenger_count
                                        //@RequestParam(value = "trip_type", required = true) String trip_type
                                        //@RequestParam(value = "ticket_type", required = true) String ticket_type,
                                        //@RequestParam(value = "is_exact_time", required = true) String is_exact_time,
                                        //@RequestParam(value = "connection", required = true) String connection
                                        ) {

        try {
        	System.out.println(origin);
            List<Ticket> ticket = trainService.search(origin, destination, departure_datetime, Integer.parseInt(passenger_count));
            return new ResponseEntity<Object>(ticket, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
