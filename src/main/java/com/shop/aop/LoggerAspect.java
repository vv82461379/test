package com.shop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 进入退出controller时候，输出日志
 * @author garen_li
 *
 */
@Aspect  
@Component
public class LoggerAspect {

	 private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class); 
	 
	 /** 
     * 定义拦截规则：拦截所有@LoggerAnnotation注解的方法。 
     */  
	 @Pointcut("@annotation(com.shop.annotation.LoggerAnnotation)")  
	 public void loggerAnnotationPointcut(){} 
	 
	 @Before("loggerAnnotationPointcut()") 
	 public void isAccessMethod(JoinPoint joinPoint) throws Throwable{
		 //获取目标方法名
		 Signature signature = joinPoint.getSignature();
		 String methodName = signature.getName();
		 StringBuffer beginStr = new StringBuffer();
		 //获取方法的参数
		 Object[] args = joinPoint.getArgs();
		 for(int i = 0;i < args.length; i++){
			 beginStr.append(args[i].toString());
		 }
		 System.out.println(methodName + "方法开始:" + beginStr);
		 
		
	 }
	 @AfterReturning(returning = "result", pointcut = "loggerAnnotationPointcut()") 
	 public void doAfterReturning(JoinPoint joinPoint,Object result) {
		//获取目标方法名
		 Signature signature = joinPoint.getSignature();
		 String methodName = signature.getName();
		 System.out.println(methodName + "方法结束:" + result);
	 }
		 
		 
}
