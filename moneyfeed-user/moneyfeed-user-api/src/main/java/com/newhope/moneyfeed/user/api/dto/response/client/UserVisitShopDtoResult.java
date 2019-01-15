package com.newhope.moneyfeed.user.api.dto.response.client;


import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerPropertyModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UserVisitShopDtoResult implements Serializable {

	private static final long serialVersionUID = -4825535953289614733L;

	private UcShopModel shop;

	private Map<String,UcShopCustomerPropertyModel > shopCustomerRules;

	public UcShopModel getShop() {
		return shop;
	}

	public void setShop(UcShopModel shop) {
		this.shop = shop;
	}

	public Map<String, UcShopCustomerPropertyModel> getShopCustomerRules() {
		return shopCustomerRules;
	}

	public void setShopCustomerRules(Map<String, UcShopCustomerPropertyModel> shopCustomerRules) {
		this.shopCustomerRules = shopCustomerRules;
	}

    
}