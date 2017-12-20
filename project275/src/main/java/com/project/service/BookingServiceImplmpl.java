package com.project.service;

import com.project.dao.TicketRepository;
import com.project.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImplmpl implements BookingService {

    @Autowired
    private TicketRepository ticketsRepository;

    /**
     * Cancel Booking
     */
    @Override
    public Ticket cancelTicket(int user_id, int id) {
        Ticket booking = null;
        try{
            booking = this.ticketsRepository.findTicketByUserAndBooking(user_id, id);
            if(booking == null){
                System.out.println("Booking not found");
            }else{
                booking.setCancelled(true);
            }
            return this.ticketsRepository.save(booking);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all booked tickets by the user
     */
    @Override
    public List<Ticket> getAllTickets(int user_id) {
        List<Ticket> BookedTicekts = null;
        try{
            BookedTicekts = this.ticketsRepository.findByUser(user_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return BookedTicekts;
    }
}
