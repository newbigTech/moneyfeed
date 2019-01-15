package com.newhope.moneyfeed.common.cache.redislock;

import java.util.concurrent.locks.Lock;

/**
 * 基于redis的分布式锁
 * @author liming
 */
public interface RedisLock extends Lock {
	
	/**
	 * 判断是否已经被任线程锁定
	 * @return 是返回<b>true</b>,否则返回<b>false</b>
	 */
	public boolean isLocked();
}
