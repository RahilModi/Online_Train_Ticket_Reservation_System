package com.project.service;

import com.project.model.Train;

import java.util.Date;
import java.util.List;

public interface TrainService {

    public void cancelTrain(String trainName, Date date);

}
