package com.project.service;

import com.project.model.Ticket;

import java.util.List;

public interface BookingService {

    public Ticket cancelTicket(int user_id, int id);

    public List<Ticket> getAllTickets(int user_id);
}
