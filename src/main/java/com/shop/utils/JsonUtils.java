package com.shop.utils;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 这个不会将int型的默认值null转为0
 * @author garen_li
 *
 */
public class JsonUtils {
	
	/**
	 * json转对象或MAP等
	 * @param <T>
	 * @param source
	 * @return
	 */
	public static <T> T json2Object(String source,Class<T> className) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(source, className);
		} catch (Exception e) {
			throw new RuntimeException("json转对象失败");
		}
	}
	
	
	/**
	 * 对象或MAP等转json
	 * @param source
	 * @return
	 */
	public static String object2Json(Object source) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(source);
		} catch (Exception e) {
			throw new RuntimeException("对象转json失败");
		}
	}
	


}
