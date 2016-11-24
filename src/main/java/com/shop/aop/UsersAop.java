package com.shop.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
public class UsersAop {
	@Pointcut("execution(* com.shop.service.UserService.queryById(..))")
	public void userAop(){}
	
	@Before("userAop()")
	public void beforeUsers(){
		System.out.println("beforeusers....................");
	}
	@AfterReturning("userAop()")
	public void afterUsers(){
		System.out.println("afterUsers.....................");
	}
}
