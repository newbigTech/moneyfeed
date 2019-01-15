package com.newhope.moneyfeed.common.cache;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.newhope.cache.core.Cache;
import com.newhope.cache.core.RedisCache;

/**
* @ClassName: CacheData  
* @Description: 缓存数据类
* @author luoxl
* @date 2016年12月18日 下午8:51:02  
*/
@Component
public class CacheData {
	private static final Logger logger = LoggerFactory.getLogger(CacheData.class);
	
	@Autowired
	@Qualifier("dataCache")
	Cache dataCache;

	@PostConstruct
	void init() {
		this.dataCache = (RedisCache) dataCache;
	}
	
	public RedisCache getDataCache() {
		return (RedisCache) dataCache;
	}
	
	public boolean setData(String name,Object value){
		
		try {
			return dataCache.set(name, value);
		} catch (Exception e) {
			logger.error("[CacheData.setData]设置缓存失败", e);
		}
		return false;
	}

	public boolean setData(String name,Object value,Long time){
		try {
			return getDataCache().set(name, value,time);
		} catch (Exception e) {
			logger.error("[CacheData.setData]设置缓存失败", e);
		}
		return false;
	}


	public boolean removeData(String name){
		try {
			 return dataCache.remove(name);
		} catch (Exception e) {
			logger.error("[CacheData.removeData]清楚缓存失败", e);
		}
		return false;
	}


	public Object getData(String name) {
		Object data = null;
		try {
			data = dataCache.get(name);
		} catch (Exception e) {
			logger.error("[CacheData.getData]:获取缓存信息异常", e);
		}
		return data;
	}
}

















