package com.newhope.openapi.biz.service.shop;

import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.user.api.dto.request.platform.ShopModifyDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmShopDtoResult;
import com.newhope.openapi.api.vo.request.shop.ShopModifyReq;
import com.newhope.openapi.api.vo.response.shop.UcBmShopResult;
import com.newhope.openapi.biz.rpc.feign.shop.BmShopFeignClient;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopDtoListResult;
import com.newhope.openapi.api.vo.request.shop.ShopQueryReq;
import com.newhope.openapi.api.vo.response.shop.ShopListResult;
import com.newhope.openapi.api.vo.response.shop.ShopResult;
import com.newhope.openapi.biz.rpc.feign.shop.ShopFeignClient;

@Service
public class ShopService {
	
	@Autowired
	ShopFeignClient shopFeignClient;
	@Autowired
	BmShopFeignClient bmShopFeignClient;
	
	public ShopListResult queryShop(ShopQueryReq shopQueryReq) {
		ShopListResult result = new ShopListResult();
		
		ShopQueryDtoReq queryDtoReq = new ShopQueryDtoReq();
		BeanUtils.copyProperties(shopQueryReq, queryDtoReq);
		BaseResponse<ShopDtoListResult> feignResp = shopFeignClient.queryShop(queryDtoReq);
		
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		if(!feignResp.isSuccess() || null == feignResp.getData() 
				|| CollectionUtils.isEmpty(feignResp.getData().getShops())) {
			return result;
		}
		
		List<ShopResult> shopList = new ArrayList<>();
		ShopResult shopResult = null;
		for (UcShopModel ucShopModel : feignResp.getData().getShops()) {
			shopResult = new ShopResult(); 
			BeanUtils.copyProperties(ucShopModel, shopResult);
			shopList.add(shopResult);
		}
		result.setShopList(shopList);
		
		result.setPage(shopQueryReq.getPage());
        result.setTotalCount(feignResp.getData().getTotalCount());
        result.setTotalPage(feignResp.getData().getTotalPage());
        result.setCode(feignResp.getCode());
        result.setMsg(feignResp.getMsg());
		
		result.setCode(ResultCode.SUCCESS.getCode());
		return result;
	}

	public Result modifyShop(ShopModifyReq requestBody) {
		Result result = new Result();
		ShopModifyDtoReq request = new ShopModifyDtoReq();
		BeanUtils.copyProperties(requestBody, request);
		BaseResponse<DtoResult> feignResp = bmShopFeignClient.modifyShop(request);
		BeanUtils.copyProperties(feignResp, result);
		return result;
	}

	public UcBmShopResult shopDetail(Long shopId) {
		UcBmShopResult result = new UcBmShopResult();
		BaseResponse<UcPmShopDtoResult> feignResp = bmShopFeignClient.shopDetail(shopId);
		BeanUtils.copyProperties(feignResp.getData(), result);
		return result;
	}
}
