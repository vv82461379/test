package com.shop.service;


public interface RedisService {

	public String get(String key);
	
	 /**
	  * ����һ��key��value
	  * @param key
	  * @param value
	  */
	 public void set(String key, String value);
	 
	 /**
	  * ����һ��key��value  ָ������ʱ�� ��λ����
	  * @param key
	  * @param value
	  * @param seconds
	  */
	 public void set(String key, String value, int seconds);
	 
	 /**
	  * ����һ���ɾ��
	  * @param keys
	  */
	 public void del(String...keys);
	 
	 /**
	  * ����key��ǰ׺����ɾ��
	  * @param pattern
	  */
	 public void delByPreifx(String preifx);
	 
	 /**
	  * �жϼ��Ƿ����
	  * @param key
	  * @return
	  */
	 public boolean exists(String key);
	 
	 /**
	  * ���key�����ڣ���<key,val>���ã�������true�����key�Ѵ��ڣ������κβ���������false��
	  * @param key
	  * @param val
	  * @param seconds key������ʱ�� ����Ϊ��λ��
	  * @return
	  */
	 public boolean setNX(String key, String val , int seconds);
	 
	 /**
	  * ��һ��ָ����key��ԭ������  24Сʱ����
	  * @param key
	  * @param seconds
	  * @return
	  */
	 public Long getDailyCounter(String key);
}
