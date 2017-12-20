package com.project.controller;

import com.project.model.Station;
import com.project.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kemy on 12/18/17.
 */

@RestController
public class StationController {

    @Autowired
    StationService stationService;

    @RequestMapping(value="/getstations", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllStations(){

        List<String> stationsList = null;
        try{
            stationsList = this.stationService.getAllStations();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(stationsList, HttpStatus.OK);
    }

}
