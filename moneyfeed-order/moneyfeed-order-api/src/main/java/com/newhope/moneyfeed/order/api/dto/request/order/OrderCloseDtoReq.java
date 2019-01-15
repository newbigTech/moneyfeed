package com.newhope.moneyfeed.order.api.dto.request.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * @author heping  
 * @date 2019年1月11日
 */
public class OrderCloseDtoReq implements Serializable {
	
	private static final long serialVersionUID = -1501945040467582970L;
	
	@ApiModelProperty(name = "orderId", notes = "订单主键id")
	private Long orderId;

	@ApiModelProperty(name = "orderNo", notes = "订单编号")
	private String orderNo;
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
