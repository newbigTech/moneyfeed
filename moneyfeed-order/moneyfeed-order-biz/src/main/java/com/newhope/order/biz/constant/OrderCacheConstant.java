package com.newhope.order.biz.constant;

public class OrderCacheConstant {

	public static final String CURRENT_ORDERNO_SEGMENT = "moneyfeed-order:number:current_segment";//当前订单生成segment
	
	public static final String ORDERNO_SEGMENT1 = "moneyfeed-order:number:segment1";//订单号 segment1
	
	public static final String ORDERNO_SEGMENT2 = "moneyfeed-order:number:segment2";//订单号 segment2
	
	public static final String ORDERNO_REDISLOCK_KEY = "moneyfeed-order:number:orderno_redislock_key";//订单号生成器redislock key
	
	
	
	public static final String CURRENT_PAYNO_SEGMENT = "moneyfeed-pay:number:current_segment";//当前订单生成segment
	
	public static final String PAYNO_SEGMENT1 = "moneyfeed-pay:number:segment1";//订单号 segment1
	
	public static final String PAYNO_SEGMENT2 = "moneyfeed-pay:number:segment2";//订单号 segment2
	
	public static final String PAYNO_REDISLOCK_KEY = "moneyfeed-pay:number:payno_redislock_key";//订单号生成器redislock key
}
