package com.newhope.moneyfeed.api.template;


public class BizTemplate<T> {

	public T process(BizCallBack<T> callBack) throws RuntimeException {
		callBack.checkParameter();
		return callBack.process();
	}
	
	public T process(FilterBizCallBack<T> callBack) throws RuntimeException {
		callBack.checkParameter();
		callBack.filter();
		return callBack.process();
	}
}
