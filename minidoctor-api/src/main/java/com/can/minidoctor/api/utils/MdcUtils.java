package com.can.minidoctor.api.utils;

import org.slf4j.MDC;

/**
 * @author LIUTM1
 * @date 2018年7月20日
 * 
 */
public class MdcUtils {

	public static final String OPENID="openId";

	public static final String requestId="requestId";


	/**
	 * 清空MDC数据
	 */
	public static void clear(){
		MDC.clear();
	}
	
	public static String get(String key){
		return MDC.get(key);
	}
	
	public static void put(String key,String value){
		MDC.put(key, value);
	}

}
