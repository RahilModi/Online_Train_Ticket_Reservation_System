package com.project.service;

import java.util.Date;

public interface TrainService {

    public boolean cancelTrain(String trainName, Date date);

    public void setCapacity(int capacity);

}
