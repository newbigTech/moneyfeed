package com.newhope.moneyfeed.goods.api.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.ProductSkusQueryDto;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/20 15:21
 */
@Api(value = "商品sku中心", description = "商品sku中心 API", protocols = "http")
public interface ProductSkuAPI {

    @ApiOperation(value = "查询商品sku列表", notes = "查询商品sku列表", protocols = "http")
    @RequestMapping(value = "/pc/product/sku/list", method = RequestMethod.POST, produces = {"application/json"})
    BaseResponse<ProductSkuListDtoResult> getProductSku(@RequestBody ProductSkusQueryDto dtoReq);
    
    @ApiOperation(value = "初始化产品和sku数据", notes = "初始化产品和sku数据", protocols = "http")
	@RequestMapping(value = "/pc/sku/allinit", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public BaseResponse<?> productSkuInit(@RequestBody String orgId);
}
