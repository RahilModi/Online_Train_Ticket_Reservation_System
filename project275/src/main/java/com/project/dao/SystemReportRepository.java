package com.project.dao;

import com.project.model.Train;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by kemy on 12/19/17.
 */
public interface SystemReportRepository extends CrudRepository<Train, String> {

    @Modifying
	@Transactional(propagation= Propagation.REQUIRED, rollbackFor = Exception.class)
    @Query(value = "SELECT train_id, origin_id, destination_id, passenger_count FROM booking where date(departure_date) = date(:date) group by train_id, origin_id, destination_id", nativeQuery = true)
    public List<Object> getReservationRates(@Param("date") Date date);

    @Modifying
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor = Exception.class)
    @Query(value = "SELECT distinct(date(departure_date)) FROM booking where (date(departure_date) >= date(:sDate) or date(departure_date) <= date(:eDate)) group by departure_date, train_id, origin_id, destination_id", nativeQuery = true)
    public List<Date> getDailyReservationRates(@Param("sDate") Date sDate, @Param("eDate") Date eDate);

}
