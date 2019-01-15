package com.newhope.openapi.web.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopDtoListResult;
import com.newhope.openapi.api.rest.user.ShopOpenAPI;
import com.newhope.openapi.api.vo.response.user.ProvinceShopListResult;
import com.newhope.openapi.api.vo.response.user.ProvinceShopResult;
import com.newhope.openapi.api.vo.response.user.ShopListResult;
import com.newhope.openapi.api.vo.response.user.ShopResult;
import com.newhope.openapi.biz.rpc.feign.user.ShopFeignClient;

@RestController
public class ShopOpenController extends AbstractController implements ShopOpenAPI {


	@Autowired
	ShopFeignClient shopFeignClient;

	@Override
	public BaseResponse<ShopListResult> queryShop() {
		ShopListResult result = new ShopListResult();
		ShopQueryDtoReq queryParam = new ShopQueryDtoReq();
		BaseResponse<ShopDtoListResult> feignResult = shopFeignClient.queryShop(queryParam);
		if(feignResult.isSuccess()&&CollectionUtils.isNotEmpty(feignResult.getData().getShops())){
			for(UcShopModel shop:feignResult.getData().getShops()){
				ShopResult shopResult = new ShopResult();
				shopResult.setId(shop.getId());
				shopResult.setEbsOrgId(shop.getEbsOrgId());
				shopResult.setIntro(shop.getIntro());
				shopResult.setName(shop.getName());
				result.getShops().add(shopResult);
			}
		}
		result.setCode(feignResult.getCode());
		result.setMsg(feignResult.getMsg());
		return buildJson(result);
	}

	@Override
	public BaseResponse<ProvinceShopListResult> queryProvinceShop() {
		ProvinceShopListResult result = new ProvinceShopListResult();
		ShopQueryDtoReq queryParam = new ShopQueryDtoReq();
		BaseResponse<ShopDtoListResult> feignResult = shopFeignClient.queryShop(queryParam);
		if(feignResult.isSuccess()&&CollectionUtils.isNotEmpty(feignResult.getData().getShops())){
			Map<String, List<ShopResult>> map = new HashMap<String, List<ShopResult>>();
			for(UcShopModel shop:feignResult.getData().getShops()){
				ShopResult shopResult = new ShopResult();
				shopResult.setId(shop.getId());
				shopResult.setEbsOrgId(shop.getEbsOrgId());
				shopResult.setIntro(shop.getIntro());
				shopResult.setName(shop.getName());
				if(!map.containsKey(shop.getProvince())){
					map.put(shop.getProvince(), new ArrayList<>());
				}
				map.get(shop.getProvince()).add(shopResult);
			}
			Iterator<Map.Entry<String, List<ShopResult>>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry<String, List<ShopResult>> entry = iterator.next();
				ProvinceShopResult shopResult = new ProvinceShopResult();
				shopResult.setProvince(entry.getKey());
				shopResult.setShops(entry.getValue());
				result.getProvinceShops().add(shopResult);
			}
		}
		result.setCode(feignResult.getCode());
		result.setMsg(feignResult.getMsg());
		return buildJson(result);
	}

	@Override
	public BaseResponse<ShopResult> queryShopById(@PathVariable("shopId")  Long shopId) {
		ShopResult result = new ShopResult();
		ShopQueryDtoReq queryParam = new ShopQueryDtoReq();
		queryParam.setId(shopId);
		BaseResponse<ShopDtoListResult> feignResult = shopFeignClient.queryShop(queryParam);
		if(feignResult.isSuccess()&&CollectionUtils.isNotEmpty(feignResult.getData().getShops())){
			for(UcShopModel shop:feignResult.getData().getShops()){
				result.setId(shop.getId());
				result.setEbsOrgId(shop.getEbsOrgId());
				result.setIntro(shop.getIntro());
				result.setName(shop.getName());
			}
		}
		result.setCode(feignResult.getCode());
		result.setMsg(feignResult.getMsg());
		return buildJson(result);
	}
}
