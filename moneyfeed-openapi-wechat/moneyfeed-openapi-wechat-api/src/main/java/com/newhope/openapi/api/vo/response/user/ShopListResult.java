package com.newhope.openapi.api.vo.response.user;



import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class ShopListResult extends Result{

	private static final long serialVersionUID = 811654078679097661L;
	
	@ApiModelProperty(name = "shops", notes = "店铺集合")
    private List<ShopResult> shops = new ArrayList<ShopResult>();

	public List<ShopResult> getShops() {
		return shops;
	}

	public void setShops(List<ShopResult> shops) {
		this.shops = shops;
	}
    
}
