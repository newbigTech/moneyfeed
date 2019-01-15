package com.newhope.openapi.api.rest.shop;

import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.shop.PmShopPageReq;
import com.newhope.openapi.api.vo.request.shop.ShopModifyReq;
import com.newhope.openapi.api.vo.request.shop.ShopReq;
import com.newhope.openapi.api.vo.response.shop.UcPmShopListResult;
import com.newhope.openapi.api.vo.response.shop.UcPmShopResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.vo.request.shop.ShopQueryReq;
import com.newhope.openapi.api.vo.response.shop.ShopListResult;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

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

	@ApiOperation(value = "新建店铺", notes = "新建店铺", protocols = "http")
	@RequestMapping(value = "/shop/save", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<Result> createShop(@Valid @RequestBody ShopReq requestBody);

	@ApiOperation(value = "店铺列表查询", notes = "店铺列表查询", protocols = "http")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "shopName", value = "店铺名称", paramType = "query", required = false, dataType = "String"),
			@ApiImplicitParam(name = "orgName", value = "公司名称", paramType = "query", required = false, dataType = "String"),
			@ApiImplicitParam(name = "startDate", value = "开始日期", paramType = "query", required = false, dataType = "String"),
			@ApiImplicitParam(name = "endDate", value = "结束日期", paramType = "query", required = false, dataType = "String"),
			@ApiImplicitParam(name = "status", value = "店铺状态", paramType = "query", required = false, dataType = "String"),
			@ApiImplicitParam(name = "page", value = "当前页", paramType = "query", required = false, dataType = "Long"),
			@ApiImplicitParam(name = "pageSize", value = "分页大小", paramType = "query", required = false, dataType = "Integer")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public BaseResponse<UcPmShopListResult> shopList(PmShopPageReq requestBody);

	@ApiOperation(value = "更新店铺", notes = "更新店铺", protocols = "http")
	@RequestMapping(value = "/shop/modify", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<Result> modifyShop(@Valid @RequestBody ShopModifyReq requestBody);

	@ApiOperation(value = "店铺详情查询", notes = "店铺详情查询", protocols = "http")
	@RequestMapping(value = "/shop/detail", method = RequestMethod.GET)
	public BaseResponse<UcPmShopResult> shopDetail(@RequestParam("shopId") Long shopId);


	@ApiOperation(value = "店铺列表查询", notes = "店铺列表查询", protocols = "http")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "shopName", value = "店铺名称", paramType = "query", required = false, dataType = "String"),
			@ApiImplicitParam(name = "orgName", value = "公司名称", paramType = "query", required = false, dataType = "String"),
			@ApiImplicitParam(name = "startDate", value = "开始日期", paramType = "query", required = false, dataType = "String"),
			@ApiImplicitParam(name = "endDate", value = "结束日期", paramType = "query", required = false, dataType = "String"),
			@ApiImplicitParam(name = "status", value = "店铺状态", paramType = "query", required = false, dataType = "String"),
			@ApiImplicitParam(name = "page", value = "当前页", paramType = "query", required = false, dataType = "Long"),
			@ApiImplicitParam(name = "pageSize", value = "分页大小", paramType = "query", required = false, dataType = "Integer")
	})
	@RequestMapping(value = "/list/export", method = RequestMethod.GET)
	public BaseResponse<Result> exportShopList(PmShopPageReq requestBody, HttpServletResponse response);
}
