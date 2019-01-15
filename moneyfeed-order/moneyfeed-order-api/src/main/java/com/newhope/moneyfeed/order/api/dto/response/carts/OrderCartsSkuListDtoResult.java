package com.newhope.moneyfeed.order.api.dto.response.carts;

import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;

public class OrderCartsSkuListDtoResult extends Result {

	private static final long serialVersionUID = 4690791913585971159L;
	private List<OrderCartsSkuDtoResult> carts;

	public List<OrderCartsSkuDtoResult> getCarts() {
		return carts;
	}

	public void setCarts(List<OrderCartsSkuDtoResult> carts) {
		this.carts = carts;
	}

}
