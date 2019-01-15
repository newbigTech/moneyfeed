package com.newhope.moneyfeed.order.api.dto.request.order;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * 订单中心表聚合查询入参对象 
 * 是否表关联规则  = {@code Boolean relaXXX},其中XXX = 表缩写
 * 
 *
 */


@ApiModel(description="订单中心聚合查询入参对象")
public class OrderInfoSelectDtoReq implements Serializable{

	private static final long serialVersionUID = -5139449432296949659L;
	
	@ApiModelProperty(name="relatRule",notes="是否关联订单规则")
	private Boolean relatRule;
	
	@ApiModelProperty(name="orderStatus",notes="订单状态")
	private String orderStatus;

	@ApiModelProperty(name="ucShopId",notes="商户id")
	private Long ucShopId;
	
	public void setRelatRule(Boolean relatRule) {
		this.relatRule = relatRule;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Long getUcShopId() {
		return ucShopId;
	}

	public void setUcShopId(Long ucShopId) {
		this.ucShopId = ucShopId;
	}

	public Boolean getRelatRule() {
		return relatRule;
	}
	
	
}
