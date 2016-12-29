package com.shop.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shop.model.Users;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * aop实现进入方法前检验token
 * @author garen_li
 *
 */
@Aspect  

public class TokenAspect {
	
	 private static final Logger logger = LoggerFactory.getLogger(TokenAspect.class); 
	
	 /** 
     * 定义拦截规则：拦截com.xjj.web.controller包下面的所有类中，有@RequestMapping注解的方法。 
     */  
    @Pointcut("@annotation(com.shop.annotation.TokenValid)")  
    public void tokenValidPointcut(){}  
	
	/**
	 * tokenValid注解的aop实现
	 * 验证是否是合法的token
	 * @param joinPoint
	 */
    @Around("tokenValidPointcut()") 
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
		Object obj= args[0];
		Class className = Arrays.asList(args).get(0).getClass();
	 
		 Field[] field = className.getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		 try{
			   for (int j = 0; j < field.length; j++){
			   
				 String nameold = field[j].getName(); // 获取属性的名字
	             String name = nameold.substring(0, 1).toUpperCase() + nameold.substring(1); // 将属性的首字符大写，方便构造get，set方法
	             String type = field[j].getGenericType().toString(); // 获取属性的类型
	             if(nameold.equals("id")){
	            	 System.out.println("id:");
	            	  Method m = className.getMethod("get" + name);
	                    Long value = (Long) m.invoke(obj); // 调用getter方法获取属性值
	                    System.out.println(value);
	             }
			   }
             
		 }catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		Users users = (Users)args[0];
		System.out.println(users);
		
		
		
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
