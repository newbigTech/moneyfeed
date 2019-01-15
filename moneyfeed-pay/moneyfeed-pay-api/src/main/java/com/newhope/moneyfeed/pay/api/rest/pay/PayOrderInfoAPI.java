package com.newhope.moneyfeed.pay.api.rest.pay;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderCallbackDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderInfoDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PaySpecialDtoReq;
import com.newhope.moneyfeed.pay.api.dto.response.PayCallbackDtoResult;
import com.newhope.moneyfeed.pay.api.dto.response.PayOrderCreateDtoResult;
import com.newhope.moneyfeed.pay.api.dto.response.PayOrderListDtoResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "支付API", description = "支付API", protocols = "http")
public interface PayOrderInfoAPI {

	@ApiOperation(value="创建商城订单的支付订单,", notes="创建商城订单的支付订单", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="reqBody", value="reqBody", required=true, paramType="body", dataType="PayOrderInfoDtoReq")
	})
	@RequestMapping(value = "/pay/create/order", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<PayOrderCreateDtoResult> createPayOrder(@RequestBody PayOrderInfoDtoReq payOrderInfoDtoReq);
	
	@ApiOperation(value="创建用户的充值的支付订单,", notes="创建用户的充值的支付订", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="reqBody", value="reqBody", required=true, paramType="body", dataType="PayOrderInfoDtoReq")
	})
	@RequestMapping(value = "/pay/create/user", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<PayOrderCreateDtoResult> createPayUser(@RequestBody PayOrderInfoDtoReq payOrderInfoDtoReq);
	
	@ApiOperation(value="支付回调地址", notes="支付回调地址", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="reqBody", value="reqBody", required=true, paramType="body", dataType="PayOrderCallbackDtoReq")
	})
	@RequestMapping(value = "/pay/callback", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<PayCallbackDtoResult> payCallback(@RequestBody PayOrderCallbackDtoReq reqBody);
	
	@ApiOperation(value="特殊处理", notes="特殊处理", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="reqBody", value="reqBody", required=true, paramType="body", dataType="PaySpecialDtoReq")
	})
	@RequestMapping(value = "/pay/special", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<DtoResult> paySpecial(@RequestBody PaySpecialDtoReq paySpecialDtoReq);
	
	@ApiOperation(value="查询支付订单基本列表,", notes="查询支付订单基本列表", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="reqBody", value="reqBody", required=true, paramType="body", dataType="PayOrderInfoDtoReq")
	})
	@RequestMapping(value = "/pay/create/order/list", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<PayOrderListDtoResult> queryPayOrderList(@RequestBody PayOrderDtoReq payOrderDtoReq);
}














