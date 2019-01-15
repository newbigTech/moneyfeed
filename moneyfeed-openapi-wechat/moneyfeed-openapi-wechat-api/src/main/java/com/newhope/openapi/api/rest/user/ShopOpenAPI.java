package com.newhope.openapi.api.rest.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.vo.response.user.ProvinceShopListResult;
import com.newhope.openapi.api.vo.response.user.ShopListResult;
import com.newhope.openapi.api.vo.response.user.ShopResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="ShopOpenAPI", description="商户中心REST API", protocols="http")
public interface ShopOpenAPI {
	
	@ApiOperation(value="获取商户列表", notes="获取商户列表", protocols="http")
	@RequestMapping(value = "/shop", method = RequestMethod.GET, produces={"application/json"})
	public BaseResponse<ShopListResult> queryShop();

	@ApiOperation(value="获取省份商户列表", notes="获取省份商户列表", protocols="http")
	@RequestMapping(value = "/shop/provience", method = RequestMethod.GET, produces={"application/json"})
	public BaseResponse<ProvinceShopListResult> queryProvinceShop();
	
	@ApiOperation(value="获取商户列表", notes="获取商户列表", protocols="http")
	@RequestMapping(value = "/shop/{shopId}", method = RequestMethod.GET, produces={"application/json"})
	public BaseResponse<ShopResult> queryShopById(@PathVariable("shopId")  Long shopId);
}
