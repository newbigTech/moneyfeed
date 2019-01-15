package com.newhope.moneyfeed.integration.api.vo.response.third;

import java.io.Serializable;
import java.util.List;


public class ShopProductPriceResult implements Serializable {

	private static final long serialVersionUID = -8288985379782157595L;

	private Long shopId;
	
	private String shopName;
	
	private String areaName;
	
	private List<OaProductResult> productList;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<OaProductResult> getProductList() {
		return productList;
	}

	public void setProductList(List<OaProductResult> productList) {
		this.productList = productList;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

}