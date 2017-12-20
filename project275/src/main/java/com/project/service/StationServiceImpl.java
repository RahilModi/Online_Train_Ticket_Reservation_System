package com.project.service;

import com.project.dao.StationRepository;
import com.project.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kemy on 12/18/17.
 */

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public List<String> getAllStations() {
        return stationRepository.findAllStation();
    }
}
