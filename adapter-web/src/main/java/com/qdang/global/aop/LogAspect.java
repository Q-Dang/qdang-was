package com.qdang.global.aop;

import com.qdang.global.exception.BusinessException;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Slf4j
@Aspect
public class LogAspect {

	@Pointcut("@within(com.qdang.global.http.WebAdapter)")
	public void onRequest() {
	}

	@Before("onRequest()")
	public void beforeRequest(JoinPoint joinPoint) {
		HttpServletRequest request =
				((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		log.info("Start Request : [{}] {}", request.getMethod(), request.getRequestURI());
		log.info("method : {}", joinPoint.getSignature().toShortString());
		Arrays.stream(joinPoint.getArgs())
				.map(Object::toString)
				.map(str -> "\t" + str)
				.forEach(log::info);
	}

	@AfterReturning(pointcut = "onRequest()", returning = "returnValue")
	public void afterReturningLogging(JoinPoint joinPoint, Object returnValue) {
		log.info("End request : {}", joinPoint.getSignature().toShortString());
		if (returnValue == null) {
			return;
		}
		log.info("\t{}", returnValue.toString());
	}

	@AfterThrowing(pointcut = "onRequest()", throwing = "e")
	public void afterThrowingLogging(JoinPoint joinPoint, Exception e) {
		log.error("Occured error in request : {}", joinPoint.getSignature().toShortString());
		if (e instanceof BusinessException) {
			log.error("\t{}", e.getMessage());
		} else {
			log.error("{}", e.getMessage(), e);
		}
	}
}
