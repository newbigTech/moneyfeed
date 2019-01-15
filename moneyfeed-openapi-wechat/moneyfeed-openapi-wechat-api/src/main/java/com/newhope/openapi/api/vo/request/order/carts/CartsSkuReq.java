package com.newhope.openapi.api.vo.request.order.carts;

import java.io.Serializable;

public class CartsSkuReq implements Serializable{

	private static final long serialVersionUID = 6244728649609287879L;

	private Long skuId;
	
	private Integer quantity;

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
}
