package com.project.aop;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.dao.TicketRepository;
import com.project.dao.UserRepository;
import com.project.model.Ticket;
import com.project.service.EmailServiceImpl;

@Aspect
@Component
public class TicketCancellationAspect {

	@Autowired
	private TicketRepository ticketRepo;
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	@Autowired
	private UserRepository userRepo;
	@Around("execution(public * com.project.service.BookingService.cancelTicket(..))")
	public Object validateCancellationTime(ProceedingJoinPoint jp)
	{
		Object[] arr = jp.getArgs();
		int ticket_id = (int)arr[1];
		Ticket t = ticketRepo.findById(ticket_id);
		Date d = t.getBookings().get(0).getDepartureDate();
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime ldt = LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault());
		System.out.println(now.getHour());
		long diff = java.time.Duration.between(now, ldt).toHours();
		int ret = 0;
		if(diff > 1 || diff < -1)
		{
			try {
				ret = (int) jp.proceed();
				if(ret != 0)
				{
					String from = "cmpe275finalproject@gmail.com";
					String to = userRepo.findById((int)arr[0]).getEmail();
					String message = "Ticket id "+ ticket_id + "cancelled.";
					emailServiceImpl.sendMail(from, to, message);
				}
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}
	
}
