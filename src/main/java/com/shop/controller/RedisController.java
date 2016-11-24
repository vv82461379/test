package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.service.RedisService;

@Controller
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/test")
	public void test(){
		String value = redisService.get("test");
		System.out.println(value);
	}
}
