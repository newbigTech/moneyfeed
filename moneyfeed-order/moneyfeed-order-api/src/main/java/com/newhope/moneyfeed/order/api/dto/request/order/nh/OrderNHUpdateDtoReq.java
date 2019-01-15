package com.newhope.moneyfeed.order.api.dto.request.order.nh;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class OrderNHUpdateDtoReq implements Serializable {

	private static final long serialVersionUID = -7824959135302320031L;

	private OrderNHCreateDtoReq createBody;

	@ApiModelProperty(name = "orderNo", notes = "订单id")
	private String orderNo;

	public OrderNHCreateDtoReq getCreateBody() {
		return createBody;
	}

	public void setCreateBody(OrderNHCreateDtoReq createBody) {
		this.createBody = createBody;
	}


	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
