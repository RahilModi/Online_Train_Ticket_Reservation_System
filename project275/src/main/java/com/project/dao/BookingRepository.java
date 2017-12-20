package com.project.dao;

import com.project.model.Booking;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kemy on 12/19/17.
 */
public interface BookingRepository extends CrudRepository<Booking, Integer> {
}
