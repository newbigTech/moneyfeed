package com.newhope.moneyfeed.order.api.dto.response.order;

import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="订单中心聚合集合对象")
public class OrderInfoListDtoResult extends DtoResult {

	private static final long serialVersionUID = -142556063469045511L;
	
	@ApiModelProperty(name="",notes="订单中心聚合集合")
	private List<OrderInfoDtoResult> ordersInfo;

	public List<OrderInfoDtoResult> getOrdersInfo() {
		return ordersInfo;
	}

	public void setOrdersInfo(List<OrderInfoDtoResult> ordersInfo) {
		this.ordersInfo = ordersInfo;
	}

}
