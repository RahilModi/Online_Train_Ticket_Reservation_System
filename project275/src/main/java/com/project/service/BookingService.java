package com.project.service;

import com.project.model.Ticket;

import java.util.List;
import java.util.Map;

public interface BookingService {

    public int cancelTicket(int user_id, int id);

    public List<Map<String, Object>> getAllTickets(int user_id);
}
