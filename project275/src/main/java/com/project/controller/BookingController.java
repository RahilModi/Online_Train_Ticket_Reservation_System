package com.project.controller;

import com.project.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "/cancelTicket/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> cancelTicket(@PathVariable("id") int ticket_id){
        int canceledTicket;
        try{
            int user_id = 1;
            canceledTicket = bookingService.cancelTicket(user_id, ticket_id);

            if(canceledTicket != 0)return new ResponseEntity<>(canceledTicket, HttpStatus.OK);
            else return new ResponseEntity<Object>("Train is already cancelled",HttpStatus.CONFLICT);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value="/getTickets", method = RequestMethod.GET)
    public ResponseEntity<List<Map<String, Object>>> getAllTicketBookings(){
        List<Map<String, Object>> bookingsList = null;
        try{
            int user_id = 2; //need to get user_id from the session or request header;
            bookingsList = bookingService.getAllTickets(user_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(bookingsList, HttpStatus.OK);
    }
}
