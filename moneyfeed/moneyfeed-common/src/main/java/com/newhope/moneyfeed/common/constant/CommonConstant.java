package com.newhope.moneyfeed.common.constant;


public class CommonConstant {

	// cookie默认过期时间,一年
	public static final int COOKIE_MAX_AGE = 365 * 24 * 3600;
	// 聚宝行情点赞cookie key
	public static final String NEWS_USEFUL_COOKIE_FLAG = "NEWS_USEFUL_COOKIE_FLAG";
	public static final String NEWS_UNUSEFUL_COOKIE_FLAG = "NEWS_UNUSEFUL_COOKIE_FLAG";
	// redis session id name
	public static final String RSESSIONID_NAME = "RSESSIONID_NAME";
	
	public static final String TRADE_MODEL = "tradeMoldel";
	
	public static final String HTTP_HEADER_TOKEN = "TOKEN";
	
	public static final String HTTP_HEADER_SOURCE = "SOURCE";
	
	public static final String HTTP_HEADER_SOURCE_APP = "APP";
	
	public static final String HTTP_HEADER_SOURCE_WECHAT = "WECHAT";
	
	public static final String HTTP_HEADER_WECHAT = "WECHAT";
	
	public static final String HTTP_HEADER_APPCODE = "APP_CODE";
	
	public static final String APP_CODE_APP1 = "APP1";
	
	public static final String APP_SOURCE_WECHAT = "WECHAT";

	public static final String SHOP_AUTH_CODE="SHOP_AUTH_CODE_";
	/**逗号 */
	public static final String SYMBOL_OF_COMMA = ",";
	/**反斜杠 */
	public static final String SYMBOL_OF_BACKSLASH = "/";
	/**分号 */
	public static final String SYMBOL_OF_SEMICOLON = ";";

	// md5 salt
	public static final String salt = "^Salt#2202$";
	
	public static final String API_APP_PREFIX = "/api/v2/app";
	
	public static final String API_WECHAT_PREFIX = "/api/v2/wechat";
	
	public static final String API_THIRD_PREFIX = "/api/v2/integration";

	//阿里云oss图片目录
	public static final String OSS_MAIN_FOLDER = "trade";
	
	//用户重试支付密码最大次数
	public static final int USER_RETRY_PAY_PWD_COUNT = 3;
}
