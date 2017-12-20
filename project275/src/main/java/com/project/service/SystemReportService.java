package com.project.service;

import java.util.Date;
import java.util.Map;

/**
 * Created by kemy on 12/19/17.
 */
public interface SystemReportService {

     Map<String, Double> getReservationRate(String date);
     Map<Date, Double> getDailyReservationRate(String sDate, String eDate);
}
