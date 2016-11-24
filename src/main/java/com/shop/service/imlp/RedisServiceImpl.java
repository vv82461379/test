package com.shop.service.imlp;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.service.RedisService;

import redis.clients.jedis.JedisCluster;

@Service
public class RedisServiceImpl implements RedisService{
	
	@Autowired
    private JedisCluster jedisCluster;  //redis3.x 集群模式
	
	/**
	 * 根据key获取值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		
		return jedisCluster.get(key);
	}
	
	public void set(String key, String value) {
		/*ValueOperations<String, String> operations = redisTemplate.opsForValue();
		operations.set(key, value);*/
		jedisCluster.set(key, value);
	}
	
	/**
	 * 设置一对key和value 指定过期时间 单位是秒
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public void set(String key, String value, int seconds) {
		/*ValueOperations<String, String> operations = redisTemplate.opsForValue();
		operations.set(key, value, seconds, TimeUnit.SECONDS);*/
		jedisCluster.setex(key, seconds, value);
	}

	/**
	 * 根据一组键删除
	 * 
	 * @param keys
	 */
	public void del(String... keys) {
		/*List<String> list = Arrays.asList(keys);
		redisTemplate.delete(list);*/
		if(keys.length > 0){
			for(String key : keys){
				jedisCluster.del(key);
			}
		}
	}

	/**
	 * 根据key的前缀批量删除
	 * 
	 * @param preifx
	 */
	public void delByPreifx(String preifx) {
		/*Set<String> keys = redisTemplate.keys(preifx);
		if (keys != null && keys.size() > 0) {
			redisTemplate.delete(keys);
		}*/
		Set<String> keys = jedisCluster.hkeys(preifx);
		if (keys != null && keys.size() > 0) {
			for(String key : keys){
				jedisCluster.del(key);
			}
		}
	}

	/**
	 * 判断键是否存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(String key) {
		return jedisCluster.exists(key);
	}
	
	/**
	  * 如果key不存在，按<key,val>设置，并返回true。如果key已存在，不做任何操作，返回false。
	  * @param key
	  * @param val
	  * @param expire key的生存时间 以秒为单位。
	  * @return
	  */
	public boolean setNX(String key, String val , int seconds) {
		/*Charset charset = Charset.forName("UTF8");
    	RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
    	byte[] keyBytes = key.getBytes(charset);
    	byte[] valBytes = val.getBytes(charset);
    	if(redisConnection.setNX(keyBytes, valBytes)){
    		redisConnection.expire(keyBytes, expire);
    		return true;
    	}
    	return false;*/
		if(jedisCluster.setnx(key, val) == 1L){
			jedisCluster.expire(key, seconds);
			return true;
		}
		return false;
    	
	}
	
	/**
	 * 对一个指定的key做原子增长 24小时过期
	 * 
	 * @param key
	 * @param seconds
	 * @return
	 */
	public Long getDailyCounter(String key) {
		/*ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String value = operations.get(key);
		if (value == null) { //双锁校验
			synchronized (this) {
				if (value == null) {
					operations.set(key, "0", 3600 * 24 , TimeUnit.SECONDS); //若干第一次不存在，设置0，并且24小时过期
				}
			}
		}
		Long afterInc = operations.increment(key, 1); //对指定key原子递增1
		return afterInc;*/
		/*String value = jedisCluster.get(key);
		if (value == null) { //双锁校验
			synchronized (this) {
				if (value == null) {
					//jedisCluster.set(key, "0", 3600 * 24 , TimeUnit.SECONDS); //若干第一次不存在，设置0，并且24小时过期
					jedisCluster.setex(key, 3600 * 24, value);
				}
			}
		}
		Long afterInc = jedisCluster.incrBy(key, 1L);
		return afterInc;*/
		
		Long initValue = 1L;
		boolean isSet = setNX(key, ""+initValue, 3600 * 24); //若第一次不存在，设置0，并且24小时过期
		if(!isSet){ //设置失败
			Long afterInc = jedisCluster.incrBy(key, 1L);
			return afterInc;
		}
		return initValue;
	}
    
	/*public String safelySet(String key,String optVal){
		redisTemplate.watch(key);
		String val = get(key);
		val = val + optVal;
		redisTemplate.multi();
		set(key,val);
		redisTemplate.exec();
		redisTemplate.unwatch();
	}*/
}
