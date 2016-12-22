package com.shop.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * aop实现进入方法前检验token
 * @author garen_li
 *
 */
@Aspect  
@Component 
public class TokenAspect {
	
	 private static final Logger logger = LoggerFactory.getLogger(TokenAspect.class); 
	
	 /** 
     * 定义拦截规则：拦截com.xjj.web.controller包下面的所有类中，有@RequestMapping注解的方法。 
     */  
    @Pointcut("execution(* @annotation(com.ehsy.cloudshop.common.annotation.TokenValid)")  
    public void tokenValidPointcut(){}  
	 
	/**
	 * tokenValid注解的aop实现
	 * 验证是否是合法的token
	 * @param joinPoint
	 */
    @Around("controllerMethodPointcut()") 
	public void isAccessMethod(ProceedingJoinPoint joinPoint) throws Throwable{
		 
    	boolean isAccess = false;
		/*
		 * 1.获取访问目标方法应该具备的权限
		 *  为解析目标方法的PrivilegeInfo注解，根据我们定义的解析器，需要得到：目标类的class形式　方法的名称
		 */
		Class targetClass = joinPoint.getTarget().getClass();
		String methodName = joinPoint.getSignature().getName();
		logger.info("{}方法开始验证Token.................",methodName);
		//得到该方法的参数
		Object[] args = joinPoint.getArgs();  
		System.out.println("size："+ args.length +"!!!!!"+args);
		
		
		
		/*
		 * 3.如果用户拥有权限，则调用目标方法　，如果没有，则不调用目标方法，只给出提示
		 */
		if (isAccess) {
		    joinPoint.proceed();//调用目标方法
		} else {
		    System.out.println("你还没有登录！");
		    }
			 
		   
		 }
}
