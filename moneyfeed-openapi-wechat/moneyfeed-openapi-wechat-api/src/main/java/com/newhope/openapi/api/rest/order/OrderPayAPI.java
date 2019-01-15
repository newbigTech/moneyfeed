package com.newhope.openapi.api.rest.order;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.order.pay.OrderPayByEBSReq;
import com.newhope.openapi.api.vo.request.order.pay.PayPasswordVerifyReq;
import com.newhope.openapi.api.vo.response.order.pay.PayAccountInfoResult;
import com.newhope.openapi.api.vo.response.order.pay.PayByEBSValidateResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Api(value="OrderPayAPI", description="订单支付open-api", protocols="http")
public interface OrderPayAPI {

	@ApiOperation(value="获取订单用户账户信息", notes="获取订单用户账户信息", protocols="http")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderId", value = "订单主键id", required = true, paramType = "query", dataType = "Long")
	})
	@RequestMapping(value = "/order/pay/getaccountinfo", method = RequestMethod.GET, produces={"application/json"})
	BaseResponse<PayAccountInfoResult> getAccountInfo(@RequestParam(name = "orderId",required = true) Long orderId);
	
	@ApiOperation(value="库存验证", notes="库存验证", protocols="http")
	@RequestMapping(value = "/order/pay/inventory/verify", method = RequestMethod.GET, produces = {"application/json"})
	BaseResponse<DtoResult> inventoryVerify(@RequestParam(name = "orderId",required = true) Long orderId);
	
	@ApiOperation(value="验证支付密码", notes="验证支付密码", protocols="http")
	@RequestMapping(value = "/order/pay/password/verify", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<DtoResult> passwordVerify(@Valid @RequestBody PayPasswordVerifyReq req);
	
	@ApiOperation(value="订单EBS方式支付", notes="订单EBS方式支付", protocols="http")
	@RequestMapping(value = "/order/pay/byebs", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<DtoResult> orderPayByEBS(@Valid @RequestBody OrderPayByEBSReq req);
	
	@ApiOperation(value="余额支付前验证相关信息", notes="余额支付前验证相关信息", protocols="http")
	@RequestMapping(value = "/order/pay/byebs/validate", method = RequestMethod.GET, produces={"application/json"})
	BaseResponse<PayByEBSValidateResult> orderPayByEBSValidate(@RequestParam(name = "orderId",required = true) Long orderId);
}