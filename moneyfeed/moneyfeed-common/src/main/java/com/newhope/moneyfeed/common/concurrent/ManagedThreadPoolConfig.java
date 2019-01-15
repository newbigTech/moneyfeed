package com.newhope.moneyfeed.common.concurrent;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class ManagedThreadPoolConfig {
	
	@Value("${newhope.trade.threadpool.coresize}")
	private int coreSize;
	@Value("${newhope.trade.threadpool.maxsize}")
	private int maxSize;
	@Value("${newhope.trade.threadpool.keepalive}")
	private long keepaliveTime;
	
	@Bean
	public ManagedThreadPool managedThreadPool() {
		ManagedThreadPool managedThreadPool = new ManagedThreadPool(coreSize, maxSize, keepaliveTime);
		return managedThreadPool;
	}
	
	@Bean
	public CompletionService<Object> completionService() {
		ManagedThreadPool managedThreadPool = new ManagedThreadPool(coreSize, maxSize, keepaliveTime);
		CompletionService<Object> completionService = new ExecutorCompletionService<Object>(managedThreadPool);
		return completionService;
	}
}
