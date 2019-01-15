package com.newhope.openapi.api.vo.request.order.carts;

import java.io.Serializable;
import java.util.List;

public class CartsUpdateReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5601074531878301538L;
	
	private List<CartsSkuReq> items;

	public List<CartsSkuReq> getItems() {
		return items;
	}

	public void setItems(List<CartsSkuReq> items) {
		this.items = items;
	}

}
