package com.newhope.openapi.web.controller.shop;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;

import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.shop.PmShopPageReq;
import com.newhope.openapi.api.vo.request.shop.ShopModifyReq;
import com.newhope.openapi.api.vo.request.shop.ShopReq;
import com.newhope.openapi.api.vo.response.shop.UcPmShopListResult;
import com.newhope.openapi.api.vo.response.shop.UcPmShopResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.rest.shop.ShopOpenAPI;
import com.newhope.openapi.api.vo.request.shop.ShopQueryReq;
import com.newhope.openapi.api.vo.response.shop.ShopListResult;
import com.newhope.openapi.biz.service.shop.ShopService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
	public BaseResponse<Result> createShop(@Valid @RequestBody ShopReq requestBody) {
		Result result = shopService.createShop(requestBody);
		return buildJson(result.getCode(), result.getMsg(), null);
	}

	@Override
	public BaseResponse<UcPmShopListResult> shopList(PmShopPageReq requestBody) {
		UcPmShopListResult result =  shopService.shopList(requestBody);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<Result> modifyShop(@RequestBody ShopModifyReq requestBody) {
		Result result  = shopService.modifyShop(requestBody);
		return buildJson(result.getCode(), result.getMsg(), null);
	}

	@Override
	public BaseResponse<UcPmShopResult> shopDetail(Long shopId) {
		UcPmShopResult result  = shopService.shopDetail(shopId);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<Result> exportShopList(PmShopPageReq requestBody, HttpServletResponse response) {
		Result result = shopService.exportShopList(requestBody,response);
		return buildJson(result);
	}

}
