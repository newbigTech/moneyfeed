package com.newhope.moneyfeed.user.api.dto.response.client;


import java.io.Serializable;
import java.util.List;

import com.newhope.moneyfeed.user.api.bean.client.UcCustomerModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;

public class CustomerExDtoResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6838950268849683402L;

	private UcCustomerModel customer;

	private List<UcShopModel> shopList;

	public UcCustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(UcCustomerModel customer) {
		this.customer = customer;
	}

	public List<UcShopModel> getShopList() {
		return shopList;
	}

	public void setShopList(List<UcShopModel> shopList) {
		this.shopList = shopList;
	}
	
}