package com.project.aop;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.dao.BookingRepository;
import com.project.dao.TicketRepository;
import com.project.model.Booking;
import com.project.model.Ticket;
import com.project.service.BookingService;
import com.project.service.EmailServiceImpl;

@Aspect
@Component
public class TrainCancellationAspect {

	@Autowired
	private TicketRepository ticketRepo;
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private BookingService bookingService;
	
	@Around("execution(public * com.project.service.TrainService.cancelTrain(..))")
	public Object onTrainCancellation(ProceedingJoinPoint jp)
	{
		boolean ret = false;
		Object[] arr = jp.getArgs();
		Date d = (Date)arr[1];
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime ldt = LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault());
		long diff = Math.abs(java.time.Duration.between(now, ldt).toHours());
		if(diff > 3)
		{
			try {
				ret = (boolean)jp.proceed();
				if(ret)
				{
					// I have train name and date
					// find next train
					// find available seats between each station
					List<Booking> ls =bookingRepo.findAllByTrainIdDepartureDate((String)arr[1], d);
					ls.stream().forEach(b -> {bookingService.cancelTicket((int)b.getTicket().getUser().getId(), b.getId());});
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
}
