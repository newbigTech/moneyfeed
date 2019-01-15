package com.newhope.openapi.api.vo.response.order.carts;

import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.response.order.base.OrderSysParamResult;

public class OrderCartsSkuListResult extends Result {

	private static final long serialVersionUID = -8304749882179935913L;
	
	private List<OrderCartsSkuResult> carts;
	
	private List<OrderSysParamResult> params;

	public List<OrderCartsSkuResult> getCarts() {
		return carts;
	}

	public void setCarts(List<OrderCartsSkuResult> carts) {
		this.carts = carts;
	}

	public List<OrderSysParamResult> getParams() {
		return params;
	}

	public void setParams(List<OrderSysParamResult> params) {
		this.params = params;
	}
	
}
