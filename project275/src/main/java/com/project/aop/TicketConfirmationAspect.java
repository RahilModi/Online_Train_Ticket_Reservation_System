package com.project.aop;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.model.Ticket;
import com.project.service.EmailServiceImpl;

@Aspect
@Component
public class TicketConfirmationAspect {

	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	@AfterReturning(pointcut="execution(public * com.project.service.PurchaseService.purchase(..))", returning = "returnValue")
	public void onPurchase(JoinPoint jp , Map<String, Object> returnValue){
		
		String from = "cmpe275finalproject@gmail.com";
		String to = (String)returnValue.get("username");
		String message = returnValue.toString();
		emailServiceImpl.sendMail(from, to, message);
	}
}
