package com.newhope.moneyfeed.order.api.dto.request.carts;

import java.io.Serializable;

public class CartsQueryDtoReq implements Serializable{

	private static final long serialVersionUID = -5749423720294456483L;

	private Long ucShopId;
	
	private Long buyerId;
	
	private String customerNo;

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
	
	
}
