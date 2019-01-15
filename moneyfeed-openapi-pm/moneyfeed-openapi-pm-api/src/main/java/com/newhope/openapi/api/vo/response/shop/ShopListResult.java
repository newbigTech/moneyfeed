package com.newhope.openapi.api.vo.response.shop;

import java.util.List;

import com.newhope.moneyfeed.api.vo.response.PageResult;

public class ShopListResult extends PageResult {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9140006056713929109L;
	
	private List<ShopResult> shopList;

	public List<ShopResult> getShopList() {
		return shopList;
	}

	public void setShopList(List<ShopResult> shopList) {
		this.shopList = shopList;
	}
    
}
