package com.yashdevs.optiTracker.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.yashdevs.optiTracker.resource.OptiTrackerConstants;

@Configuration
@Aspect
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final String SERVICE_PACKAGE = "execution(* com.yashdevs.optiTracker.service.*.*(..))";
	private static final String CONTROLLER_PACKAGE = "execution(* com.yashdevs.optiTracker.controller.*.*(..))";

	@Before(SERVICE_PACKAGE + OptiTrackerConstants.COMMA_SEPERATOR + CONTROLLER_PACKAGE)
	public void logMethodCallBeforeExecution(JoinPoint joinPoint) {
		logger.info("Before Aspect - {} is called with arguments: {}", joinPoint, joinPoint.getArgs());
	}

	@After(SERVICE_PACKAGE + OptiTrackerConstants.COMMA_SEPERATOR + CONTROLLER_PACKAGE)
	public void logMethodCallAfterExecution(JoinPoint joinPoint) {
		logger.info("After Aspect - {} has executed", joinPoint);
	}

	@AfterThrowing(pointcut = SERVICE_PACKAGE + OptiTrackerConstants.COMMA_SEPERATOR
			+ CONTROLLER_PACKAGE, throwing = "exception")
	public void logMethodCallAfterException(JoinPoint joinPoint, Exception exception) {
		logger.info("AfterThrowing Aspect - {} has thrown an exception {}", joinPoint, exception);
	}

	/*
	 * @AfterReturning( pointcut =
	 * "com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.dataPackageConfig()",
	 * returning = "resultValue" ) public void
	 * logMethodCallAfterSuccessfulExecution(JoinPoint joinPoint, Object
	 * resultValue) { logger.info("AfterReturning Aspect - {} has returned {}" ,
	 * joinPoint, resultValue); }
	 */

}
