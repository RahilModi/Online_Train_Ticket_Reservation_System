package com.project.service;


import com.project.dao.CancelledTrainRepository;
import com.project.dao.BookingRepository;
import com.project.dao.CancelledTrainRepository;
import com.project.dao.TicketRepository;
import com.project.dao.TrainRepository;
import com.project.model.CancelledDate;
import com.project.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private CancelledTrainRepository cancelledTrainRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    @Transactional
    public boolean cancelTrain(String trainName, Date date) {
    	CancelledDate cancelledDate = null;
    	try{
            cancelledDate = cancelledTrainRepository.findByDate(date);
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
            cancelledDate = cancelledTrainRepository.save(cancelledDate);
        }catch (Exception e){
            e.printStackTrace();
            cancelledDate = null;
        }
        return cancelledDate == null ? false : true;
    }
    @Override
    @Transactional
    public void setCapacity(int capacity) {
        List<Train> trains = new ArrayList<>();

        try{
            trains = trainRepository.getAllTrains();
            for(Train t : trains) {
                t.setMaxCapacity(capacity);
                trainRepository.save(t);
            }
            cancelledTrainRepository.deleteAll();
            bookingRepository.deleteAll();
            ticketRepository.deleteAll();

        }catch (Exception e){
            e.printStackTrace();
        }
        return;
    }
}
