package com.project.controller;

import com.project.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class TrainController {

    @Autowired
    TrainService trainService;

    @RequestMapping(value="/setCapacity", method = RequestMethod.POST)
    public ResponseEntity<Object> setCapacity(@RequestParam(value = "capacity", required = true) int capacity){

        try{
            this.trainService.setCapacity(capacity);
            return new ResponseEntity<>("Maximum capacity has been updated.", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Maximum capacity could not updated.", HttpStatus.CONFLICT);
        }

    }

    @RequestMapping(value="/canceltrain", method = RequestMethod.POST)
    public ResponseEntity<Object> cancelTrain(@RequestParam(value = "id", required = true) String trainName,
                                      @RequestParam(value = "date", required = true) String date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d = null;
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean bflag = this.trainService.cancelTrain(trainName,d);
        if(bflag)
        	return new ResponseEntity<Object>("Train has been cancelled",HttpStatus.OK);
        else
        	return new ResponseEntity<Object>("Train cannot be cancelled",HttpStatus.OK);
    }
}