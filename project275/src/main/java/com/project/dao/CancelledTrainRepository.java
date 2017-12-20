package com.project.dao;

import com.project.model.CancelledDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface CancelledTrainRepository extends JpaRepository<CancelledDate, Long> {

    CancelledDate findByDate(Date date);

    @Modifying
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor = Exception.class)
    @Query(value = "delete from cancelled_date_train", nativeQuery = true)
    public void deleteTrainMapping();
}
