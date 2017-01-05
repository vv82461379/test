package com.shop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 输出日志注解
 * @author garen_li
 *
 */
@Target(ElementType.METHOD)//这个注解是应用在方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggerAnnotation {

}
