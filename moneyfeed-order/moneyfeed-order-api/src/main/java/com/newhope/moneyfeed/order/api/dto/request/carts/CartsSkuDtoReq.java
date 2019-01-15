package com.newhope.moneyfeed.order.api.dto.request.carts;

import java.io.Serializable;

public class CartsSkuDtoReq implements Serializable {

	private static final long serialVersionUID = 4467634800174725267L;

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
