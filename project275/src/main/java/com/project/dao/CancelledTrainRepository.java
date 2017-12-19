package com.project.dao;

import com.project.model.CancelledDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface CancelledTrainRepository extends JpaRepository<CancelledDate, Long> {

    CancelledDate findByDate(Date date);
}
