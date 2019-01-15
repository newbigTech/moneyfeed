package com.newhope.moneyfeed.api.template;


public abstract interface BizCallBack<T> {
	
	public abstract void checkParameter();
	
	public abstract T process() throws RuntimeException;
}
