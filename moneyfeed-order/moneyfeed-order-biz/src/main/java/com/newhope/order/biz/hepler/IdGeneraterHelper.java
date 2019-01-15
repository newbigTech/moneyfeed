package com.newhope.order.biz.hepler;

import com.newhope.commons.lang.DateUtils;
public class IdGeneraterHelper {
	
	public static  int ORDER_SEGMENT_MAX_LEFT_QTY = 50;// orderno segment中订单号剩余最大数量
	public static  int ORDER_SEGMENT_MAX_QTY = 100;    // orderno segment中订单号最大数量
	public static  int PAY_SEGMENT_MAX_LEFT_QTY = 50;// payno segment中订单号剩余最大数量
	public static  int PAY_SEGMENT_MAX_QTY = 100;    // payno segment中订单号最大数量
	
	public static final long ENCODE_TIME_BASE = DateUtils.str2Date("20181212",
			DateUtils.yyyyMMdd).getTime();// 订单编号时间压缩编码基于20181212
	
	/**
	 * 订单号生成规则引擎： 
	 * 时间编码4位=T1+T2, eg:0123=01+23
	 * 随机值6位=S1+S2+S3, eg:123456=12+34+56
	 * 订单号规则：S3+T2+S2+T1+S1+业务编码
	 * @param bizCode
	 * @param daysEncode
	 * @param idValue
	 * @return 
	 */
	public static String orderGeneraterEngine(String bizCode, String daysEncode, String idValue) {
		String T1 = daysEncode.substring(0, daysEncode.length()/2);
		String T2 = daysEncode.substring(daysEncode.length()/2, daysEncode.length());
		String S1 = idValue.substring(0, idValue.length()/3);
		String S2 = idValue.substring(idValue.length()/3, idValue.length()/3*2);
		String S3 = idValue.substring(idValue.length()/3*2, idValue.length());
		return new StringBuilder().append(S3)
				.append(T2)
				.append(S2)
				.append(T1)
				.append(S1)
				.append(bizCode)
				.toString();
	}
	/**
	 * 时间编码格式化
	 * @param gap
	 * @return
	 */
	public static String getFormatTimeEncode(long gap) {
		return String.format("%1$04d", gap);
	}
	
	public static String getCurrentTimeEncode() {
		return getFormatTimeEncode(daysBetween(IdGeneraterHelper.ENCODE_TIME_BASE, 
				System.currentTimeMillis()));
	}
	
	public static long daysBetween( long startTime, long endTime ){
		return ( endTime - startTime ) / 86400000;
	}
	
}
