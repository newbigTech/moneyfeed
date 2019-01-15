package com.newhope.openapi.api.vo.request.order;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class OrderUpdateReq extends OrderBasePostReq {

	private static final long serialVersionUID = 8849648536024581922L;

	@NotBlank(message = "订单号不能为空")
	@ApiModelProperty(name = "orderNo", notes = "订单号", required = true)
	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
