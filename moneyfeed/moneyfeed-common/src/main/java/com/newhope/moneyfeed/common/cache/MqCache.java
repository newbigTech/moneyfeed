package com.newhope.moneyfeed.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.newhope.cache.core.Cache;
import com.newhope.cache.core.RedisCache;

/**
* @ClassName: MqCache  
* @Description: 消息缓存类
*/
@Component
public class MqCache {
	
	/** 消息缓存*/
	@Autowired
	@Qualifier("mqRedisCache")
	Cache cache;

	public RedisCache getMqCache() {
		return (RedisCache) cache;
	}
}

















