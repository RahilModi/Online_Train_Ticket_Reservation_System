package com.project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {

	private static final Logger log = LoggerFactory.getLogger(TransactionAspect.class);
	
	//@Around("execution(public * com.project.dao.TicketRepository.s(..))")
	public void addTransactionFee(ProceedingJoinPoint pjp) throws Throwable
	{
		//int cost = 0;
//		Object obj = pjp.proceed();
//		System.out.println(obj);
//		//cost = (int)obj + 1;
//		System.out.println("in around");
		//return obj;
	}
	
	@Before("execution(public * com.project.service.SearchService.search(..))")
	public void beforeSearch()
	{
		System.out.println("in AOp");
	}
	
	
}