package com.project.service;

import java.util.Date;

public interface TrainService {

    public void cancelTrain(String trainName, Date date);

    public void setCapacity(int capacity);

}
