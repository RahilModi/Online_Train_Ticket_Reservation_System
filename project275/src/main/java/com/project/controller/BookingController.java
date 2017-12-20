package com.project.controller;

import com.project.model.User;

import com.project.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping(value = "/cancelTicket/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> cancelTicket(@PathVariable("id") int ticket_id, HttpServletRequest request){
        int canceledTicket;
        try{
            HttpSession httpSession = request.getSession();
            User user = (User) httpSession.getAttribute("User");
            System.out.println(user.getId());
            canceledTicket = bookingService.cancelTicket((int)user.getId(), ticket_id);
            if(canceledTicket != 0)return new ResponseEntity<>(canceledTicket, HttpStatus.OK);
            else return new ResponseEntity<Object>("Train is already cancelled",HttpStatus.CONFLICT);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value="/getTickets", method = RequestMethod.GET)
    public ResponseEntity<List<Map<String, Object>>> getAllTicketBookings(HttpServletRequest request){
        List<Map<String, Object>> bookingsList = null;
        try{
            HttpSession httpSession = request.getSession();
            User user = (User) httpSession.getAttribute("User");
            System.out.println(user.getId());
            bookingsList = bookingService.getAllTickets((int)user.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(bookingsList, HttpStatus.OK);
    }
}