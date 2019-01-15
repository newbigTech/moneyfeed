package com.newhope.moneyfeed.order.api.dto.request.present;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class OrderPresentFeedQueryDtoReq implements Serializable {

	/**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/
	private static final long serialVersionUID = -2532803656840451087L;
	@ApiModelProperty(name = "orderId", notes = "订单主键id")
	private Long orderId;

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderId() {
		return orderId;
	}

}
