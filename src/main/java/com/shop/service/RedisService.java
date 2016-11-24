package com.shop.service;


public interface RedisService {

	public String get(String key);
	
	 /**
	  * 设置一对key和value
	  * @param key
	  * @param value
	  */
	 public void set(String key, String value);
	 
	 /**
	  * 设置一对key和value  指定过期时间 单位是秒
	  * @param key
	  * @param value
	  * @param seconds
	  */
	 public void set(String key, String value, int seconds);
	 
	 /**
	  * 根据一组键删除
	  * @param keys
	  */
	 public void del(String...keys);
	 
	 /**
	  * 根据key的前缀批量删除
	  * @param pattern
	  */
	 public void delByPreifx(String preifx);
	 
	 /**
	  * 判断键是否存在
	  * @param key
	  * @return
	  */
	 public boolean exists(String key);
	 
	 /**
	  * 如果key不存在，按<key,val>设置，并返回true。如果key已存在，不做任何操作，返回false。
	  * @param key
	  * @param val
	  * @param seconds key的生存时间 以秒为单位。
	  * @return
	  */
	 public boolean setNX(String key, String val , int seconds);
	 
	 /**
	  * 对一个指定的key做原子增长  24小时过期
	  * @param key
	  * @param seconds
	  * @return
	  */
	 public Long getDailyCounter(String key);
}
