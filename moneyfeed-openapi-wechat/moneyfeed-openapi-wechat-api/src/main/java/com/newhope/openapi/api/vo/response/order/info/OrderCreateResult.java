package com.newhope.openapi.api.vo.response.order.info;


import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class OrderCreateResult extends Result {

	private static final long serialVersionUID = 8529156873659178671L;

	@ApiModelProperty(name = "orderNo", notes = "订单号")
	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
