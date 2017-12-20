package com.project.service;

import com.project.model.User;

import java.util.Map;

public interface PurchaseService {

	boolean purchase(Map<String, Object> bookings, User user);
}
