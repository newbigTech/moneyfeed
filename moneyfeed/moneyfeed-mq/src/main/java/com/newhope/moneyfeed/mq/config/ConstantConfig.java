package com.newhope.moneyfeed.mq.config;

public class ConstantConfig {

	public static final String MQ_PROPS_HEADER_PUB_RETRY_KEY = "mq_pub_retrys";
	public static final int MQ_PROPS_HEADER_PUB_RETRIES = 3;
	public static final String MQ_PROPS_HEADER_COMSUME_RETRY_KEY = "mq_comsume_retrys";
	public static final int MQ_PROPS_HEADER_COMSUME_RETRIES = 3;
	/** 消息缓存hash集合key*/
	public static final String MQ_CACHE_RETRY_KEY = "MQ_RETRY";
	/** 存储TradeSendDataEvent事件的数据*/
	public static final String MQ_TRADE_EVENT_DATA = "MQ_TRADE_EVENT_DATA";
	// TradeSendDataEvent事件参数数据，sevice类名
	public static final String MQ_TRADE_EVENT_PARAM_SERVER_CLASS = "MQ_TRADE_EVENT_PARAM_SERVER_CLASS";
	// TradeSendDataEvent事件参数数据，数据模型类名
	public static final String MQ_TRADE_EVENT_PARAM_MODEL_CLASS = "MQ_TRADE_EVENT_PARAM_MODEL_CLASS";
	// TradeSendDataEvent事件参数数据，数据对应表名
	public static final String MQ_TRADE_EVENT_PARAM_TABLE_NAME = "MQ_TRADE_EVENT_PARAM_TABLE_NAME";
	// TradeSendDataEvent事件参数数据，删除操作sql语句
	public static final String MQ_TRADE_EVENT_PARAM_DEL_SQL = "MQ_TRADE_EVENT_PARAM_DEL_SQL";
	// TradeSendDataEvent事件参数数据，业务方法名
	public static final String MQ_TRADE_EVENT_PARAM_BIZ_METHOD = "MQ_TRADE_EVENT_PARAM_BIZ_METHOD";
	/** 缓存消息的时间key*/
	public static final String MQ_CACHE_RETRY_TIME_KEY = "MQ_RETRY_TIME";
	/** 缓存消息要发送的EXCHANGE*/
	public static final String MQ_CACHE_RETRY_EXCHANGE = "MQ_RETRY_EXCHANGE";
	/** 缓存消息要发送的ROOTKEY*/
	public static final String MQ_CACHE_RETRY_ROOTKEY = "MQ_RETRY_ROOTKEY";
	/** 消息缓存时长，1分钟*/
	public static final Long MQ_CACHE_RETRY_TIME = 60000L;
	
}
