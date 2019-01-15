package com.newhope.moneyfeed.order.api.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.order.api.dto.request.integration.CancelOrderResultDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.IntegrationTimeInfoDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.ReceiveOrderCreateInfoDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.ReceiveOrderPayInfoDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.RepositoryLowDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.TransportInfoToEbsResultDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderValidateRepositoryDtoResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(value = "OrderIntegrationAPI", description = "OrderIntegration", protocols = "http")
public interface OrderIntegrationAPI {

	@ApiOperation(value="更新订单创建信息", notes="更新订单创建信息", protocols="http")
	@RequestMapping(value = "/oc/order/integration/receive/ordercreateinfo", method = RequestMethod.POST, produces = {"application/json"})
	BaseResponse<DtoResult> receiveOrderCreateInfo(@Valid @RequestBody ReceiveOrderCreateInfoDtoReq dtoReq);
	
	@ApiOperation(value="更新订单支付信息", notes="更新订单支付信息", protocols="http")
	@RequestMapping(value = "/oc/order/integration/receive/orderpayinfo", method = RequestMethod.POST, produces = {"application/json"})
	BaseResponse<DtoResult> receiveOrderPayInfo(@RequestBody ReceiveOrderPayInfoDtoReq dtoReq);
	
	@ApiOperation(value="调用商城更新取消订单结果", notes="调用商城更新取消订单结果", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="reqBody", value="reqBody", required=true, paramType="body", dataType="CancelOrderResultDtoReq")
	})
	@RequestMapping(value = "/oc/order/integration/cancel/result", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<DtoResult> reciveCancelOrderResult(@Valid @RequestBody CancelOrderResultDtoReq cancelOrderResultDtoReq);
	
	@ApiOperation(value="接收中台库存不足请求", notes="接收中台库存不足请求", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="reqBody", value="reqBody", required=true, paramType="body", dataType="CancelOrderResultDtoReq")
	})
	@RequestMapping(value = "/oc/order/integration/repository/low", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<DtoResult> reciveRepositoryLow(@RequestBody RepositoryLowDtoReq repositoryLowDtoReq);

	@ApiOperation(value="拉取中间层的EBS更新后的数据更新订单中心的数据", notes="拉取中间层的EBS更新后的数据更新订单中心的数据", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="reqBody", value="reqBody", required=true, paramType="body", dataType="CancelOrderResultDtoReq")
	})
	@RequestMapping(value = "/oc/order/integration/pull", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	void pullOrderInfo();
	
	@ApiOperation(value="integration调用商城更新 拉料信息结果", notes="integration调用商城更新 拉料信息结果", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="reqBody", value="reqBody", required=true, paramType="body", dataType="TransportInfoToEbsResultDtoReq")
	})
	@RequestMapping(value = "/oc/order/integration/transport", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<DtoResult> reciveTransportInfoToEbsResult(@RequestBody TransportInfoToEbsResultDtoReq transportInfoToEbsResultDtoReq);

	@ApiOperation(value="接收中间层的EBS更新后的物料更新订单中心的数据", notes="接收中间层的EBS更新后的物料更新订单中心的数据", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="reqBody", value="reqBody", required=true, paramType="body", dataType="IntegrationTimeInfoDtoReq")
	})
	@RequestMapping(value = "/oc/order/integration/recive/info", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	void reciveEbsOrderInfo(@RequestBody IntegrationTimeInfoDtoReq integrationTimeInfoDtoReq);
	
	@ApiOperation(value="验证订单锁库的库存是否足够", notes="验证订单锁库的库存是否足够", protocols="http")
	@RequestMapping(value = "/oc/order/integration/validation/repository", method = RequestMethod.GET)
	BaseResponse<OrderValidateRepositoryDtoResult> validateRepository(@RequestParam("orderId") Long orderId);
}


