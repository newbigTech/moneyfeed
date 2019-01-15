package com.newhope.moneyfeed.user.api.dto.response.client;


import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerPropertyModel;


public class ShopCustomerPropertyListResult extends DtoResult {
	
	private static final long serialVersionUID = -3652359293902898915L;
	
	private List<UcShopCustomerPropertyModel> propertys;

	public List<UcShopCustomerPropertyModel> getPropertys() {
		return propertys;
	}

	public void setPropertys(List<UcShopCustomerPropertyModel> propertys) {
		this.propertys = propertys;
	}

    
}