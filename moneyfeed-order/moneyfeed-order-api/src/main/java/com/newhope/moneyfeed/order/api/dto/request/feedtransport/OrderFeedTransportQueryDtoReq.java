package com.newhope.moneyfeed.order.api.dto.request.feedtransport;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("拉料信息")
public class OrderFeedTransportQueryDtoReq implements Serializable {

	private static final long serialVersionUID = 1252917375052736275L;
	@ApiModelProperty(name = "orderId", notes = "订单主键id")
	private Long orderId;

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderId() {
		return orderId;
	}

}
