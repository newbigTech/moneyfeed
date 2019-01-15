package com.newhope.moneyfeed.order.api.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.order.api.dto.request.order.pay.OrderPayByEBSDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PaySpecialDtoReq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(value = "OrderAPI", description = "order", protocols = "http")
@RequestMapping(value="/oc")
public interface OrderPayAPI {

	@ApiOperation(value="订单EBS方式支付", notes="订单EBS方式支付", protocols="http")
	@RequestMapping(value = "/order/pay/byebs", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<DtoResult> orderPayByEBS(@Valid @RequestBody OrderPayByEBSDtoReq dtoReq);
	
	@ApiOperation(value="特殊处理", notes="特殊处理", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="reqBody", value="reqBody", required=true, paramType="body", dataType="PaySpecialDtoReq")
	})
	@RequestMapping(value = "/pay/special", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<DtoResult> paySpecial(@RequestBody PaySpecialDtoReq paySpecialDtoReq);
}


