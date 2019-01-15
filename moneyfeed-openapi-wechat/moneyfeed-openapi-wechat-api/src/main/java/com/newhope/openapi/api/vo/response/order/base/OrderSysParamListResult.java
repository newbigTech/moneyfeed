package com.newhope.openapi.api.vo.response.order.base;

import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;

public class OrderSysParamListResult extends Result {

	private static final long serialVersionUID = -9192992467629688548L;

	private List<OrderSysParamResult> params;

	public List<OrderSysParamResult> getParams() {
		return params;
	}

	public void setParams(List<OrderSysParamResult> params) {
		this.params = params;
	}

}
