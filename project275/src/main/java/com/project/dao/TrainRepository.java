package com.project.dao;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.model.Station;
import com.project.model.Train;

public interface TrainRepository extends CrudRepository<Train, String>{

	@Modifying
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	@Query(value = "update train t set t.max_capacity = :max_cap;", nativeQuery=true)
	@Secured(value = "ROLE_ADMIN")
	void updateTrain(@Param("max_cap") int max_cap);
	
	@Secured(value = "ROLE_USER")
	List<Train> findAll();
	
	@Query(value="select t1.train_name, t1.arrival_time from train_station as t1 where t1.train_name in (" + 
" select Distinct(t.train_name)  from booking as b right join " + 
"(select Distinct(ts1.train_name) from train_station as ts1 inner join train_station as ts2 " + 
" where ts1.departure_time  >= :departure_time and ts1.station_name = :origin and ts1.direction = :dir and ts2.station_name = :destination) " + 
" as t on t.train_name = b.train_id where b.train_id is null or " +
" (not (datediff(b.arrival_date, :date) = 0)) " +
" or " +
" ( " + 
" datediff(b.arrival_date, :date) = 0 and " +
" (select t2.cancelled from ticket t2 where t2.id = b.ticket_id) = false and "+
"(((b.destination_id > :origin) and(b.origin_id < :origin)) or " + 
" ((b.origin_id > :origin)and(b.destination_id < :destination)) or " + 
"((b.origin_id < :destination)and(b.destination_id > :destination)) or " + 
"((b.origin_id < :origin)and(b.destination_id > :destination))) " + 
" ) "+  
"group by t.train_name having sum(b.passenger_count) is null or (sum(b.passenger_count) + :passenger_count) <= 15  "+
") and t1.station_name = :destination order by t1.arrival_time asc;",nativeQuery=true) 
	List<Object> findDirectTrains(@Param("origin") String origin,@Param("destination") String destination, @Param("date") Date date,
					@Param("departure_time") Time departure_time, @Param("passenger_count") int passenger_count, @Param("dir") int dir);
	
	@Query(value="Select ts.departure_time from train_station ts where ts.train_name = :trainName and ts.station_name = :stationName",nativeQuery = true)
	Time findDepartureTimeByTrainNameStationName(@Param("trainName") String trainName, @Param("stationName") String stationName); 
	
	@Modifying
//	@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	@Query(value = "select * from train", nativeQuery = true)
	public List<Train> getAllTrains();


}
