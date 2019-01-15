package com.newhope.moneyfeed.user.api.rest.client;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.client.HandleEbsCustomerVisitDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopCustomerPropertyQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.UpdateShopCustomerPropertyReq;
import com.newhope.moneyfeed.user.api.dto.request.client.UserShopQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopCustomerPropertyListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopDtoListResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(value = "Shop", description = "商户中心API", protocols = "http")
public interface ShopAPI {


    @ApiOperation(value = "控制ebs客户访问", notes = "控制ebs客户访问", protocols = "http")
    @RequestMapping(value = "/shop/customer/ebs/handle/visit", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<DtoResult> handleEbsCustomerVisit(@RequestBody HandleEbsCustomerVisitDtoReq userDtoReq);

    @ApiOperation(value = "根据条件查询商户信息", notes = "根据条件查询商户信息", protocols = "http")
    @RequestMapping(value = "/shop", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<ShopDtoListResult> queryShop(@RequestBody ShopQueryDtoReq queryDtoReq);

    @ApiOperation(value = "根据客户对应商户信息", notes = "根据客户对应商户信息", protocols = "http")
    @RequestMapping(value = "/customer/mapping/shop", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<ShopDtoListResult> queryCustomerMappingShop(@RequestParam(value = "customerId", required = true) Long customerId, @RequestParam(value = "status", required = false) String status);
    
    @ApiOperation(value = "查询商户对应客户权限", notes = "查询商户对应客户权限", protocols = "http")
    @RequestMapping(value = "/shop/customer/property", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<ShopCustomerPropertyListResult> queryShopCustomerProperty(@RequestBody ShopCustomerPropertyQueryDtoReq queryDto);

    @ApiOperation(value="修改系统配置数据", notes="修改系统配置数据", protocols="http")
	@RequestMapping(value = "/shop/customer/property/modify", method = RequestMethod.POST, produces = {"application/json"})
	public BaseResponse<DtoResult> modifyShopCustomerProperty(@Valid @RequestBody UpdateShopCustomerPropertyReq updateReq);

    @ApiOperation(value = "获取商户系统人员", notes = "获取商户系统人员", protocols = "http")
	@RequestMapping(value = "/shop/user", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public BaseResponse<UcBmUserListDtoResult> queryBmUserByShopId(@Valid @RequestBody UserShopQueryDtoReq userShopQueryDtoReq);
	
    
    
  }
