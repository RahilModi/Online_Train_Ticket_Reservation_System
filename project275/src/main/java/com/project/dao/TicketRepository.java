package com.project.dao;

import com.project.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    /**
     * find Booked Tickets by the user
     */
    public List<Ticket> findByUser(int user_id);

    /**
     * find a Ticket by user_id and booking_id
     */
    @Query(value = "select * from ticket as t where passenger_id = :user_id and id = :ticket_id", nativeQuery = true)
    public Ticket findTicketByUserAndBooking(@Param("user_id") int user_id, @Param("ticket_id") int ticketId);
}
