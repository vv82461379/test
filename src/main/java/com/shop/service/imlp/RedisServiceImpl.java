package com.shop.service.imlp;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.service.RedisService;

import redis.clients.jedis.JedisCluster;

@Service
public class RedisServiceImpl implements RedisService{
	
	@Autowired
    private JedisCluster jedisCluster;  //redis3.x ��Ⱥģʽ
	
	/**
	 * ����key��ȡֵ
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
	 * ����һ��key��value ָ������ʱ�� ��λ����
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
	 * ����һ���ɾ��
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
	 * ����key��ǰ׺����ɾ��
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
	 * �жϼ��Ƿ����
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(String key) {
		return jedisCluster.exists(key);
	}
	
	/**
	  * ���key�����ڣ���<key,val>���ã�������true�����key�Ѵ��ڣ������κβ���������false��
	  * @param key
	  * @param val
	  * @param expire key������ʱ�� ����Ϊ��λ��
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
	 * ��һ��ָ����key��ԭ������ 24Сʱ����
	 * 
	 * @param key
	 * @param seconds
	 * @return
	 */
	public Long getDailyCounter(String key) {
		/*ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String value = operations.get(key);
		if (value == null) { //˫��У��
			synchronized (this) {
				if (value == null) {
					operations.set(key, "0", 3600 * 24 , TimeUnit.SECONDS); //���ɵ�һ�β����ڣ�����0������24Сʱ����
				}
			}
		}
		Long afterInc = operations.increment(key, 1); //��ָ��keyԭ�ӵ���1
		return afterInc;*/
		/*String value = jedisCluster.get(key);
		if (value == null) { //˫��У��
			synchronized (this) {
				if (value == null) {
					//jedisCluster.set(key, "0", 3600 * 24 , TimeUnit.SECONDS); //���ɵ�һ�β����ڣ�����0������24Сʱ����
					jedisCluster.setex(key, 3600 * 24, value);
				}
			}
		}
		Long afterInc = jedisCluster.incrBy(key, 1L);
		return afterInc;*/
		
		Long initValue = 1L;
		boolean isSet = setNX(key, ""+initValue, 3600 * 24); //����һ�β����ڣ�����0������24Сʱ����
		if(!isSet){ //����ʧ��
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
