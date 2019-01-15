package com.newhope.moneyfeed.order.api.dto.response.order;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "订单创建返回对象")
public class OrderCreateDtoResult extends DtoResult {

	private static final long serialVersionUID = -3975847748601870657L;
	@ApiModelProperty(name = "orderNo", notes = "订单号")
	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public static OrderCreateDtoResult newInstance(){
		return new OrderCreateDtoResult();
	}
}
