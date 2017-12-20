package com.project.service;

import com.project.model.User;

import java.util.Map;

import com.project.model.Ticket;

public interface PurchaseService {

	Map<String, Object> purchase(Map<String, Object> bookings);
}
