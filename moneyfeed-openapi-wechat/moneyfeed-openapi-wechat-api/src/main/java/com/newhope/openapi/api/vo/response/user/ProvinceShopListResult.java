package com.newhope.openapi.api.vo.response.user;



import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class ProvinceShopListResult extends Result{

	private static final long serialVersionUID = 8458146634608582391L;
	
	@ApiModelProperty(name = "shops", notes = "店铺列表")
	private List<ProvinceShopResult> provinceShops = new ArrayList<ProvinceShopResult>();

	public List<ProvinceShopResult> getProvinceShops() {
		return provinceShops;
	}

	public void setProvinceShops(List<ProvinceShopResult> provinceShops) {
		this.provinceShops = provinceShops;
	}

}
