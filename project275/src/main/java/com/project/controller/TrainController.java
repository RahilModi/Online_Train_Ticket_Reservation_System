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
import sun.plugin2.message.Message;

import java.util.Date;

@RestController
public class TrainController {

    @Autowired
    TrainService trainService;

    @RequestMapping(value="/canceltrain", method = RequestMethod.POST)
    public ResponseEntity<Object> cancelTrain(@RequestParam(value = "id", required = true) String trainName,
                                      @RequestParam(value = "date", required = true) Date date){
        this.trainService.cancelTrain(trainName,date);
        return new ResponseEntity<Object>("Train has been cancelled",HttpStatus.OK);
    }
}
