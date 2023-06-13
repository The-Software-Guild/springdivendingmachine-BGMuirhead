package com.wileyedge.aop;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

//	@Pointcut(value = "execution (* *.*(..))")
	@Pointcut(value = "within (com.vending..*)")
	public void dummy() {
		
	}
	
	
//	@Around(value = "dummy()")
	public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
		Object returnValue=null;
		String methodName  = pjp.getSignature().getName();
		try {
			
			System.out.println("Entering " + methodName + " "+ LocalDateTime.now());
			
			//above .proceed is equivalent to a before advice
			returnValue = pjp.proceed(); // go to the business method - returns an object if there is one 
			//below .proceed is equivalent to an after advice
			
//			System.out.println("Exiting  " + methodName+ LocalDateTime.now());
		
		
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			throw e;
		}
		
		return returnValue;
	}
	
	@Before(value = "dummy()")
	public void logBefore(JoinPoint jp) {
		String methodName  = jp.getSignature().getName();
		System.out.println("Entering " + methodName + " "+ LocalDateTime.now());
	}
	
	
//	@AfterReturning(value = "dummy()", returning ="r")
	public void logAfter(JoinPoint jp, Object r) {
		String methodName  = jp.getSignature().getName();
		System.out.println("Exiting from " + methodName);
	}
	
	
	
	
}
