package com.project.service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.dao.CancelledTrainRepository;
import com.project.dao.StationRepository;
import com.project.dao.TrainRepository;
import com.project.model.Station;
import com.project.model.Train;
import com.project.model.TrainType;

@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	private TrainRepository trainRepo;
	
	@Autowired
	private StationRepository stationRepository; 
	
	@Autowired
	private CancelledTrainRepository CancelledTrainRepository;
//	@Autowired
//	private Cance
	
	@Override
	public List<Map<String, Object>> search(String origin, String destination, String departure_datetime, int passenger_count, int ticket_type) {

		List<Object> trains= null;
		List<String> cancelledTrains= null;
		List<Map<String, Object>> result = new  ArrayList<>();
		try {
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        Date d = f.parse(departure_datetime);
	        DateFormat dfdate = new SimpleDateFormat("yyyy-MM-dd");
	        DateFormat dftime = new SimpleDateFormat("HH:mm:ss");
	        Date date = dfdate.parse(departure_datetime);
	        Time time = Time.valueOf(dftime.format(d));
			trains = this.trainRepo.findDirectTrains(origin, destination, date, time, passenger_count, origin.compareTo(destination) > 0 ? 1 : 0);
			cancelledTrains = CancelledTrainRepository.findTrainsByDate(date);
			if(ticket_type == 0)
			{
				int count = 0;
				for(Object o : trains)
				{
					Object[] oarr = (Object[])o;
					String tname = oarr[0].toString();
					if(cancelledTrains.size() == 0 || !cancelledTrains.contains(tname) )
					{
						Map<String, Object> map = new HashMap<>();
						Time departureTime = this.trainRepo.findDepartureTimeByTrainNameStationName(tname, origin);
						count++;
						String stime = oarr[1].toString();
						Date d1 = dftime.parse(departureTime.toString());
						d1.setMonth(d.getMonth());d1.setYear(d.getYear());d1.setDate(d.getDate());// origin departure time
						Date d2 = dftime.parse(stime);
						d2.setMonth(d.getMonth());d2.setYear(d.getYear());d2.setDate(d.getDate());// origin departure time
						map.put("origin", origin);map.put("destination", destination);
						Train trn = trainRepo.findOne(tname);
						map.put("price", cost(origin, destination,passenger_count ,trn.getType().getCost()));
						map.put("ticket_type", TrainType.Undefined);
						map.put("passenger_count", passenger_count);
						List<Map<String, String>> ls1 = new ArrayList<>();
						Map<String, String> map1 = new HashMap<>();
						map1.put("train_name", tname);
						map1.put("origin", origin);
						map1.put("destination", destination);
						map1.put("depat_time", d1.toString());
						map1.put("arrival_time", d2.toString());
						ls1.add(map1);
						map.put("trains", ls1);
						result.add(map);
						if(count == 5){
							break;
						}
					}
				}
			}
			else if(ticket_type == 1){
				int count = 0;
				for(Object o : trains)
				{
					Object[] oarr = (Object[])o;
					String tname = oarr[0].toString();
					if((cancelledTrains.size() == 0 || !cancelledTrains.contains(tname)) && !tname.contains("00"))
					{
						Map<String, Object> map = new HashMap<>();
						Time departureTime = this.trainRepo.findDepartureTimeByTrainNameStationName(tname, origin);
						count++;
						String stime = oarr[1].toString();
						Date d1 = dftime.parse(departureTime.toString());
						d1.setMonth(d.getMonth());d1.setYear(d.getYear());d1.setDate(d.getDate());// origin departure time
						Date d2 = dftime.parse(stime);
						d2.setMonth(d.getMonth());d2.setYear(d.getYear());d2.setDate(d.getDate());// origin departure time
						map.put("origin", origin);map.put("destination", destination);
						Train trn = trainRepo.findOne(tname);
						map.put("price", cost(origin, destination,passenger_count ,trn.getType().getCost()));
						map.put("ticket_type", TrainType.Regular);
						map.put("passenger_count", passenger_count);
						List<Map<String, String>> ls1 = new ArrayList<>();
						Map<String, String> map1 = new HashMap<>();
						map1.put("train_name", tname);
						map1.put("origin", origin);
						map1.put("destination", destination);
						map1.put("depat_time", d1.toString());
						map1.put("arrival_time", d2.toString());
						ls1.add(map1);
						map.put("trains", ls1);
						result.add(map);
						if(count == 5){
							break;
						}
					}
				}
			}
			else{
				int count = 0;
				for(Object o : trains)
				{
					Object[] oarr = (Object[])o;
					String tname = oarr[0].toString();
					if((cancelledTrains.size() == 0 || !cancelledTrains.contains(tname)) && tname.contains("00"))
					{
						Map<String, Object> map = new HashMap<>();
						Time departureTime = this.trainRepo.findDepartureTimeByTrainNameStationName(tname, origin);
						count++;
						String stime = oarr[1].toString();
						Date d1 = dftime.parse(departureTime.toString());
						d1.setMonth(d.getMonth());d1.setYear(d.getYear());d1.setDate(d.getDate());// origin departure time
						Date d2 = dftime.parse(stime);
						d2.setMonth(d.getMonth());d2.setYear(d.getYear());d2.setDate(d.getDate());// origin departure time
						map.put("origin", origin);map.put("destination", destination);
						Train trn = trainRepo.findOne(tname);
						map.put("price", cost(origin, destination,passenger_count ,trn.getType().getCost()));
						map.put("ticket_type", TrainType.Express);
						map.put("passenger_count", passenger_count);
						List<Map<String, String>> ls1 = new ArrayList<>();
						Map<String, String> map1 = new HashMap<>();
						map1.put("train_name", tname);
						map1.put("origin", origin);
						map1.put("destination", destination);
						map1.put("depat_time", d1.toString());
						map1.put("arrival_time", d2.toString());
						ls1.add(map1);
						map.put("trains", ls1);
						result.add(map);
						if(count == 5){
							break;
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public int cost(String o, String d, int pcount, int trainfare)
	{
		char co = o.charAt(0);
		char cd = d.charAt(0);
		int diff = Math.abs(co-cd);
		int cost = diff/5;
		return (diff%5 > 0 ? cost + 1 : cost) * pcount * trainfare;
	}

	@Override
	public List<Map<String, Object>> search(String origin, String destination, String departure_datetime,
			int passenger_count, int conn, int train_type) {
		
		List<Object> trains= null;
		List<String> cancelledTrains= null;
		List<Map<String, Object>> result = new  ArrayList<>();
		int oCost = stationRepository.findOne(origin).getStationType().getCost();
		int dCost = stationRepository.findOne(destination).getStationType().getCost();
		if(oCost == 2 &&  dCost == 2)
			return this.search(origin, destination, departure_datetime, passenger_count, train_type);
		else if(oCost == 1 && dCost == 2)
		{
			//if origin is local and destination is express
			// you can do better by finding express trains reaching destinations before last train in direct.
			// check from which express station the seats are available and how can you reach that express station before that express train departs
			try {
				DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		        Date d = f.parse(departure_datetime);
		        DateFormat dfdate = new SimpleDateFormat("yyyy-MM-dd");
		        DateFormat dftime = new SimpleDateFormat("HH:mm:ss");
		        Date date = dfdate.parse(departure_datetime);
		        Time time = Time.valueOf(dftime.format(d));
				trains = this.trainRepo.findDirectTrains(origin, destination, date, time, passenger_count, origin.compareTo(destination) > 0 ? 1 : 0);
				cancelledTrains = CancelledTrainRepository.findTrainsByDate(date);
				List<String> result1 = new ArrayList<>();
				int count = 0;
				for(Object o : trains)
				{
					Object[] oarr = (Object[])o;
					String tname = oarr[0].toString();
					if(cancelledTrains.size() == 0 || !cancelledTrains.contains(tname) )
					{
						result1.add(tname);
						count++;
						if(count == 5){
							break;
						}
					}
				}
				int direction = origin.compareTo(destination) > 0 ? 1 : 0;
				char c = '\0';
				if(direction == 0 )
				{
					c = origin.charAt(0);
					while((c - 'A')%5 != 0 )
						c++;
					
				}
				else
				{
					c = origin.charAt(0);
					while((c - 'A')%5 != 0 )
						c--;
				}
				Station expressStation = stationRepository.findByName(String.valueOf(c));
				List<Object[]> trainsCombined = trainRepo.findTrainsWithOneStopRE(origin, String.valueOf(c), destination, time, passenger_count, origin.compareTo(destination) > 0 ? 1 : 0);
				Train lastTrain = trainRepo.findOne(result1.get(result1.size()-1));
				
				
				//finding better train
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		else if(oCost == 2 && dCost == 1)
		{
			//if origin is Express and destination is local
			// you can do better by finding earliest express trains departing from origin.
			// check from which express station the seats are available to farthest express station and how can you reach that regular station from there on.
		}	
		else
		{
			// if both stations are express
		}
		
				
		return null;

	}
}
