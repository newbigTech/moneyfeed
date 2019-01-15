package com.newhope.moneyfeed.goods.api.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.SkuInitReq;

import io.swagger.annotations.ApiOperation;

public interface SkuApi {

	@ApiOperation(value = "初始化sku数据", notes = "必须在初始化商品数据之后,仅仅根据重量生成一条sku记录", protocols = "http")
	@RequestMapping(value = "/pc/sku/init", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public BaseResponse<?> skuInit(SkuInitReq req);
	
}
