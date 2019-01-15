package com.newhope.moneyfeed.common.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;


public class ManagedThreadPool extends ThreadPoolExecutor {

	@PreDestroy
	public void shutdown() {
		this.shutdown();
	}
	
	public ManagedThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	}
	
	public ManagedThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, workQueue);
	}

	protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
		return new NamedTask<T>(runnable, value);
	}

	protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
		return new NamedTask<T>(callable);
	}

}
