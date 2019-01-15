package com.newhope.openapi.api.vo.response.user;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class ProvinceShopResult implements Serializable{

	private static final long serialVersionUID = 3390782343696489767L;
	
	@ApiModelProperty(name = "province", notes = "省份")
	private String province;
	
	@ApiModelProperty(name = "shops", notes = "店铺列表")
	private List<ShopResult> shops = new ArrayList<ShopResult>();

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public List<ShopResult> getShops() {
		return shops;
	}

	public void setShops(List<ShopResult> shops) {
		this.shops = shops;
	}
	

}
