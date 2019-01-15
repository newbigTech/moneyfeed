package com.newhope.moneyfeed.api.template;


public interface FilterBizCallBack<T> extends BizCallBack<T>{

	@Override
	public abstract void checkParameter();
	
	public abstract void filter();

	@Override
	public abstract T process() throws RuntimeException;
	
}
