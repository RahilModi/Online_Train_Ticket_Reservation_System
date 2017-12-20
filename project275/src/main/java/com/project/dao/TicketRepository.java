package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.model.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Integer>{

	@Modifying
	@Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
	@Secured(value = "ROLE_ADMIN")
	void deleteAll();
	
	public List<Ticket> findByUser(int user_id);

    /**
     * find a Ticket by user_id and booking_id
     */
    @Query(value = "select * from ticket as t where passenger_id = :user_id and id = :ticket_id", nativeQuery = true)
    public Ticket findTicketByUserAndBooking(@Param("user_id") int user_id, @Param("ticket_id") int ticketId);

}
