package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.model.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    /**
     * find Booked Tickets by the user
     */
    @Query(value = "select * from ticket as t where passenger_id = :user_id", nativeQuery = true)
    public List<Ticket> findByUserId(@Param("user_id") int user_id);

    @Query(value = "select * from ticket as t where id = :ticket_id", nativeQuery = true)
    public Ticket findById(@Param("ticket_id") int ticketId);

}
