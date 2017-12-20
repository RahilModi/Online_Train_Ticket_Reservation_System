package com.project.service;

import java.util.List;

import com.project.model.Ticket;
import java.util.Date;
import java.util.List;
import com.project.model.Train;

public interface TrainService {

    public void cancelTrain(String trainName, Date date);

    public void setCapacity(int capacity);

}
