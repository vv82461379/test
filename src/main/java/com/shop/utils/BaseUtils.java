package com.shop.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.UUID;


public class BaseUtils {

	 public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
	
	/**
	 * 遮挡手机号中间四位
	 * @param mobile
	 * @return
	 */
	public static String shelterMobile(String mobile){
		if(mobile.length() == 11){
			return mobile .substring(0,3) + "****" + mobile .substring(7, mobile .length());
		}else{
			throw new RuntimeException("手机号位数不对");
		}
	}
	
	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	/**
	 * 获取时间戳
	 * @return
	 */
	public static String create_timestamp() {
	    return Long.toString(System.currentTimeMillis() / 1000);
	}
	
    /** 
     * 返回一个定长的随机字符串(只包含大小写字母、数字) 
     *  
     * @param length 
     *         随机字符串长度 
     * @return 随机字符串 
     */  
    public static String generateString(int length) {  
        StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));  
        }  
        return sb.toString();  
    }  
	
	 /** 
     * 微信支付签名算法sign 
     * @param characterEncoding 
     * @param parameters 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public static String createSign(String characterEncoding,SortedMap<Object,Object> parameters){  
        StringBuffer sb = new StringBuffer();  
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）  
        Iterator it = es.iterator();  
        while(it.hasNext()) {  
            Map.Entry entry = (Map.Entry)it.next();  
            String k = (String)entry.getKey();  
            Object v = entry.getValue();  
            if(null != v && !"".equals(v)   
                    && !"sign".equals(k) && !"key".equals(k)) {  
                sb.append(k + "=" + v + "&");  
            }  
        }  
        String key = "KEY";	//秘钥
        sb.append("key=" + key);  
        String sign = Md5Utils.MD5Encode(sb.toString(), characterEncoding).toUpperCase();  
        return sign;  
    }  
	
	
}
