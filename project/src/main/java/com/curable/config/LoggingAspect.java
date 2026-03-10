package com.curable.config;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Annadurai S
 *
 */
@Aspect
@Component
public class LoggingAspect {

	private final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

	/**
	 * Pointcut that matches all repositories, services and Web REST endpoints.
	 */
	@Pointcut("within(@org.springframework.stereotype.Repository *)"
			+ " || within(@org.springframework.stereotype.Service *)"
			+ " || within(@org.springframework.web.bind.annotation.RestController *)")
	public void springBeanPointcut() {

	}

	/**
	 * Pointcut that matches all Spring beans in the application's main packages.
	 */
	@Pointcut("within(com.curable.repository..*)" + " || within(com.curable.service..*)"
			+ " || within(com.curable.rest..*)")
	public void applicationPackagePointcut() {

	}

	@AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), e.getCause() != null ? e.getMessage() : "NULL");
	}

	@Around("applicationPackagePointcut() && springBeanPointcut()")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		final String joinPoints = Arrays.toString(joinPoint.getArgs());
		if (joinPoints != null) {
			log.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), joinPoints);
		}
		final Object result = joinPoint.proceed();
		log.info("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), result);
		return result;
	}

}
