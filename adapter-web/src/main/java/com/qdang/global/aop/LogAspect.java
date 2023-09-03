package com.qdang.global.aop;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LogAspect {

	@Pointcut("within(com.qdang.adapter..*)")
	public void onRequest() {
	}

	@Before("onRequest()")
	public void beforeRequest(JoinPoint joinPoint) {
		log.info("###Start request {}", joinPoint.getSignature().toShortString());
		Arrays.stream(joinPoint.getArgs())
				.map(Object::toString)
				.map(str -> "\t" + str)
				.forEach(log::info);
	}

	@AfterReturning(pointcut = "onRequest()", returning = "returnValue")
	public void afterReturningLogging(JoinPoint joinPoint, Object returnValue) {
		log.info("###End request {}", joinPoint.getSignature().toShortString());
		if (returnValue == null) {
			return;
		}
		log.info("\t{}", returnValue.toString());
	}

	@AfterThrowing(pointcut = "onRequest()", throwing = "e")
	public void afterThrowingLogging(JoinPoint joinPoint, Exception e) {
		log.error("###Occured error in request {}", joinPoint.getSignature().toShortString());
		log.error("\t{}", e.getMessage());
	}
}
