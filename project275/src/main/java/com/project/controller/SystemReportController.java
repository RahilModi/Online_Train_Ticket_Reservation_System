package com.project.controller;

import com.project.service.SystemReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by kemy on 12/19/17.
 */
@RestController
public class SystemReportController {

    @Autowired
    SystemReportService systemReportService;

    @RequestMapping(value="/getReservationRate", method = RequestMethod.POST)
    public ResponseEntity<Object> getReservationRate(@RequestParam(value = "date", required = true) String date){

        Map<String, Double> reservationRateMap = null;
        List<Object> list = new ArrayList<>();
        try{
            reservationRateMap = this.systemReportService.getReservationRate(date);
            List<String> dates = new ArrayList<>();
            List<Double> rates = new ArrayList<>();
            for (Map.Entry<String, Double> entry : reservationRateMap.entrySet()) {

                String key = entry.getKey().toString();
                Double value = entry.getValue();
                dates.add(key);
                rates.add(value);
            }
            list.add(dates);
            list.add(rates);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value="/getDailyReservationRate", method = RequestMethod.POST)
    public ResponseEntity<Object> getReservationRate(@RequestParam(value = "sDate", required = true) String sDate, @RequestParam(value = "eDate", required = true) String eDate){
        System.out.println(sDate);
        System.out.println(eDate);
        Map<Date, Double> DailyReservationRateMap = null;
        List<Object> list = new ArrayList<>();
        try{
            DailyReservationRateMap = this.systemReportService.getDailyReservationRate(sDate, eDate);
            List<String> dates = new ArrayList<>();
            List<Double> rates = new ArrayList<>();
            for (Map.Entry<Date, Double> entry : DailyReservationRateMap.entrySet()) {

                String key = entry.getKey().toString();
                Double value = entry.getValue();
                dates.add(key);
                rates.add(value);
            }
            list.add(dates);
            list.add(rates);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
