package com.newhope.moneyfeed.order.api.dto.request.carts;

import java.io.Serializable;
import java.util.List;

public class CartsUpdateDtoReq implements Serializable {

	private static final long serialVersionUID = -1919897956787131530L;

	private Long ucShopId;

	private Long buyerId;

	private String customerNo;

	private List<CartsSkuDtoReq> items;

	public Long getUcShopId() {
		return ucShopId;
	}

	public void setUcShopId(Long ucShopId) {
		this.ucShopId = ucShopId;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public List<CartsSkuDtoReq> getItems() {
		return items;
	}

	public void setItems(List<CartsSkuDtoReq> items) {
		this.items = items;
	}

}
