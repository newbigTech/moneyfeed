package com.newhope.openapi.web.controller.shop;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;

import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.shop.ShopModifyReq;
import com.newhope.openapi.api.vo.response.shop.UcBmShopResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.rest.shop.ShopOpenAPI;
import com.newhope.openapi.api.vo.request.shop.ShopQueryReq;
import com.newhope.openapi.api.vo.response.shop.ShopListResult;
import com.newhope.openapi.biz.service.shop.ShopService;

@RestController
public class ShopOpenController extends AbstractController implements ShopOpenAPI {
	
	@Autowired
	ShopService shopService;

	@Override
	public BaseResponse<ShopListResult> queryShop(ShopQueryReq shopQueryReq) {
		ShopListResult result = new ShopListResult();
		result = shopService.queryShop(shopQueryReq);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<Result> modifyShop(@RequestBody ShopModifyReq requestBody) {
		Result result  = shopService.modifyShop(requestBody);
		return buildJson(result.getCode(), result.getMsg(), null);
	}

	@Override
	public BaseResponse<UcBmShopResult> shopDetail(Long shopId) {
		UcBmShopResult result  = shopService.shopDetail(shopId);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

}
