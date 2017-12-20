package com.project.controller;

import com.project.model.Ticket;
import com.project.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "/cancelTicket/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> cancelTicket(@PathVariable("id") int ticket_id){
        Ticket ticket = null;
        try{
            int user_id = 1;
            Ticket canceledTicket = bookingService.cancelTicket(user_id, ticket_id);
            return new ResponseEntity<>(canceledTicket, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value="/bookings", method = RequestMethod.GET)
    public ResponseEntity<List<Ticket>> getAllTicketBookings(){
        List<Ticket> bookingsList = null;
        try{
            int user_id = 1; //need to get user_id from the session or request header;
            bookingsList = bookingService.getAllTickets(user_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(bookingsList, HttpStatus.OK);
    }
}
