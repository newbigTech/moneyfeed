package com.newhope.moneyfeed.common.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InventoryOperInfo {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static final ThreadLocal<String> TRANS_ID = new ThreadLocal<>();

    public static final int INIT_ROLLBACK_DELAY = 5; //单位 分钟
    
    public static final int MAX_RETRY = 10; //最大重试次数
}
