package com.newhope.moneyfeed.common.helper;

import com.newhope.moneyfeed.common.util.DateUtil;

public class IdGeneraterHelper {

	// segment最大剩余量，小于则预生成订单号到segment2
	public static int ORDER_SEGMENT_MAX_LEFT_QTY = 50;
	public static int ORDER_SEGMENT_MAX_QTY = 100;
	public static int DELIVERY_ORDER_SEGMENT_MAX_LEFT_QTY = 100;
	public static int DELIVERY_ORDER_SEGMENT_MAX_QTY = 1000;
	public static int GOODS_SEGMENT_MAX_LEFT_QTY = 100;
	public static int GOODS_SEGMENT_MAX_QTY = 1000;
	
	// 用户账户前缀
	public static final String USER_ACCOUNT_PREFIX = "ZXE";
	// 店铺编码前缀
	public static final String SHOP_CODE_PREFIX = "S";
	// 猪场编码前缀
	public static final String PIG_FARM_CODE_PREFIX = "F";
	// 店铺会员编码前缀
	public static final String SHOP_MEMBER_CODE_PREFIX = "V";
	// 店铺子账号编码前缀
	public static final String SHOP_SUBACCT_CODE_PREFIX = "M";
	// 订单编号时间压缩编码基于20170101
	public static final long ENCODE_TIME_BASE = DateUtil.getMillsecOfDate("20181206",DateUtil.YYYYMMDD);
	
	/**
	 * 时间编码格式化
	 * @param gap
	 * @return
	 */
	public static String getFormatTimeEncode(long gap) {
		return String.format("%1$04d", gap);
	}
	
	public static String getCurrentTimeEncode() {
		long days = DateUtil.getDateGap(IdGeneraterHelper.ENCODE_TIME_BASE, System.currentTimeMillis(), "D");
		return IdGeneraterHelper.getFormatTimeEncode(days);
	}
	
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
	 * 交割单号生成规则引擎： 
	 * 时间编码4位=T1+T2, eg:0123=01+23
	 * 随机值6位=S1+S2+S3, eg:123456=12+34+56
	 * 交割单规则：S3+T2+S2+T1+S1+业务编码
	 * @param bizCode
	 * @param daysEncode
	 * @param idValue
	 * @return
	 */
	public static String deliveryOrderGeneraterEngine(String bizCode, String daysEncode, String idValue) {
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
	 * 发布单号生成规则引擎： 
	 * 时间编码4位=T1+T2, eg:0123=01+23
	 * 随机值6位=S1+S2+S3, eg:123456=12+34+56
	 * 发布单号规则：S3+T2+S2+T1+S1+业务编码
	 * @param bizCode
	 * @param daysEncode
	 * @param idValue
	 * @return
	 */
	public static String goodsGeneraterEngine(String bizCode, String daysEncode, String idValue) {
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
	 * 用户账户生成规则引擎： 
	 * 时间编码4位=T1+T2, eg:0123=01+23
	 * 随机值5位=S1+S2+S3, eg:12345=12+3+45
	 * 订单号规则：S3+T2+S2+T1+S1+业务编码
	 * @param bizCode
	 * @param daysEncode
	 * @param idValue
	 * @return
	 */
	public static String userAccountEngine(String bizCode, String daysEncode, String idValue) {
		String T1 = daysEncode.substring(0, daysEncode.length()/2);
		String T2 = daysEncode.substring(daysEncode.length()/2, daysEncode.length());
		String S1 = idValue.substring(0, idValue.length()/2);
		String S2 = idValue.substring(idValue.length()/2, (idValue.length()/2 + 1));
		String S3 = idValue.substring((idValue.length()/2 + 1), idValue.length());
		
		return new StringBuilder().append(USER_ACCOUNT_PREFIX)
				.append(S3)
				.append(T2)
				.append(S2)
				.append(T1)
				.append(S1)
				.append(bizCode)
				.toString();
	}
	
	/**
	 * 用户编码生成规则：
	 * 业务编码+时间编码+随机编码
	 * @param bizCode
	 * @param daysEncode
	 * @param idValue
	 * @return
	 */
	public static String userCodeEngine(String bizCode, String daysEncode, String idValue) {
		return new StringBuilder().append(bizCode)
				.append(daysEncode)
				.append(idValue)
				.toString();
	}
	
	/**
	 * 店铺编码规则：
	 * S+业务编码+时间编码+随机编码
	 * @param bizCode
	 * @param daysEncode
	 * @param idValue
	 * @return
	 */
	public static String shopCodeEngine(String bizCode, String daysEncode, String idValue) {
		return new StringBuilder().append(SHOP_CODE_PREFIX)
				.append(bizCode)
				.append(daysEncode)
				.append(idValue)
				.toString();
	}
	
	/**
	 * 猪场编码规则：
	 * F+业务编码+时间编码+随机编码
	 * @param bizCode
	 * @param daysEncode
	 * @param idValue
	 * @return
	 */
	public static String pigFarmCodeEngine(String bizCode, String daysEncode, String idValue) {
		return new StringBuilder().append(PIG_FARM_CODE_PREFIX)
				.append(bizCode)
				.append(daysEncode)
				.append(idValue)
				.toString();
	}
	
	/**
	 * 店铺会员编码规则：
	 * V+店铺编码+随机编码
	 * @param bizCode
	 * @param daysEncode
	 * @param idValue
	 * @return
	 */
	public static String shopMemberCodeEngine(String shopCode, String idValue) {
		return new StringBuilder().append(SHOP_MEMBER_CODE_PREFIX)
				.append(shopCode)
				.append(idValue)
				.toString();
	}
	
	/**
	 * 店铺子账号编码规则：
	 * M+店铺编码+随机编码
	 * @param bizCode
	 * @param daysEncode
	 * @param idValue
	 * @return
	 */
	public static String shopSubacctCodeEngine(String shopCode, String idValue) {
		return new StringBuilder().append(SHOP_SUBACCT_CODE_PREFIX)
				.append(shopCode)
				.append(idValue)
				.toString();
	}
}
