package com.newhope.openapi.api.rest.shop;

import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.shop.ShopModifyReq;
import com.newhope.openapi.api.vo.response.shop.UcBmShopResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.vo.request.shop.ShopQueryReq;
import com.newhope.openapi.api.vo.response.shop.ShopListResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Api(value = "shop", description = "商户中心", protocols = "http")
public interface ShopOpenAPI {
	
	@ApiOperation(value = "根据条件查询商户信息", notes = "根据条件查询商户信息", protocols = "http")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "店铺Id", paramType = "query", required = false, dataType = "Long"),
        @ApiImplicitParam(name = "ids", value = "店铺Id集合", paramType = "query", required = false, dataType = "Array"),
        @ApiImplicitParam(name = "ebsOrgId", value = "ebs组织主键id", paramType = "query", required = false, dataType = "Long"),
        @ApiImplicitParam(name = "page", value = "当前页", paramType = "query", required = false, dataType = "Long"),
        @ApiImplicitParam(name = "pageSize", value = "分页大小", paramType = "query", required = false, dataType = "Integer")
	})
	@RequestMapping(value = "/shop/list", method = RequestMethod.GET, produces = {"application/json"})
    public BaseResponse<ShopListResult> queryShop(ShopQueryReq shopQueryReq);

	@ApiOperation(value = "更新店铺", notes = "更新店铺", protocols = "http")
	@RequestMapping(value = "/shop/modify", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<Result> modifyShop(@Valid @RequestBody ShopModifyReq requestBody);

	@ApiOperation(value = "店铺详情查询", notes = "店铺详情查询", protocols = "http")
	@RequestMapping(value = "/shop/detail", method = RequestMethod.GET)
	public BaseResponse<UcBmShopResult> shopDetail(@RequestParam("shopId") Long shopId);


}
