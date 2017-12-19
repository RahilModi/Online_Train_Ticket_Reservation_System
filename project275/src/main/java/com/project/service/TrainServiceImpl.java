package com.project.service;

import com.project.dao.CancelledTrainRepository;
import com.project.dao.TrainRepository;
import com.project.model.CancelledDate;
import com.project.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private CancelledTrainRepository cancelledTrainRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Override
    public void cancelTrain(String trainName, Date date) {
        try{
            CancelledDate cancelledDate = cancelledTrainRepository.findByDate(date);
            Train t = trainRepository.findOne(trainName);
            if(cancelledDate != null)
            {
                cancelledDate.getTrain().add(t);
            }
            else
            {
                cancelledDate = new CancelledDate();
                cancelledDate.setDate(date);
                List<Train> ls = new ArrayList<>();
                ls.add(t);
                cancelledDate.setTrain(ls);
            }
            cancelledTrainRepository.save(cancelledDate);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
