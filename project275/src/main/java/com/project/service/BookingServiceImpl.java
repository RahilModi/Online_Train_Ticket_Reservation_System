package com.project.service;

import com.project.dao.TicketRepository;
import com.project.model.Booking;
import com.project.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private TicketRepository ticketsRepository;

    /**
     * Cancel Booking
     */
    @Override
    @Transactional
    public int cancelTicket(int user_id, int ticket_id) {
//        Ticket ticket = null;
        try{
            Ticket ticket = this.ticketsRepository.findById(ticket_id);
            if(ticket == null){
                System.out.println("Booking not found");
            }
            else{
                if(!ticket.isCancelled()) {
                    ticket.setCancelled(true);
                    Ticket t = this.ticketsRepository.save(ticket);
                    return t.getId();
                }
                else return 0;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get all booked tickets by the user
     */
    @Override
    @Transactional
    public List<Map<String, Object>> getAllTickets(int user_id) {
        List<Ticket> BookedTickets = null;
        List<Map<String, Object>> tickets = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            BookedTickets = this.ticketsRepository.findByUserId(user_id);

            for(Ticket t : BookedTickets) {
//                System.out.println(t.getId());
                Map<String, Object> ticket = new HashMap<>();
                ticket.put("ticket_id", t.getId());
                ticket.put("ticket_type",t.getTicketType());
                ticket.put("price",t.getPrice());
                ticket.put("passenger_count", t.getBookings().get(0).getPassengerCount());
                ticket.put("cancelled",t.isCancelled());
                List<Booking> bookingsList = t.getBookings();
                List<Map<String, Object>> bookings = new ArrayList<>();
                for(Booking b : bookingsList) {
                    Map<String, Object> bookingMap = new HashMap<>();
                    bookingMap.put("origin", b.getOrigin().getName());
                    bookingMap.put("destination", b.getDestination().getName());
                    bookingMap.put("train_name",b.getTrain().getName());
                    Date d = df.parse(b.getDepartureDate().toString());
                    System.out.println(d.toString());
//                    System.out.println(b.getDepartureDate());
//                    System.out.println(b.getDepartureDate().toString());
                    bookingMap.put("departure_date", d.toString());
                    d = df.parse(b.getArrivalDate().toString());
                    bookingMap.put("arrival_date", d.toString());
                    bookings.add(bookingMap);
                }
                ticket.put("bookings", bookings);
                tickets.add(ticket);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return tickets;
    }
}
