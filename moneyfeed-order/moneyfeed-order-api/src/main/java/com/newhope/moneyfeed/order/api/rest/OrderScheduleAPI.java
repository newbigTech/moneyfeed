package com.newhope.moneyfeed.order.api.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "OrderScheduleAPI", description = "订单定时任务", protocols = "http")
@RequestMapping(value="/oc/order/schedule")
public interface OrderScheduleAPI {

	@ApiOperation(value="定时关闭未支付超时订单", notes="定时关闭未支付超时订单", protocols="http")
	@RequestMapping(value = "/closeunpaid", method = RequestMethod.GET, produces = {"application/json"})
	BaseResponse<DtoResult> closeUnPaidOrder();


	@ApiOperation(value="发送提醒支付信息", notes="发送提醒支付信息", protocols="http")
	@RequestMapping(value = "/remindpay", method = RequestMethod.GET, produces = {"application/json"})
	BaseResponse<DtoResult> remindPay();

	@ApiOperation(value="发送提醒拉料信息", notes="发送提醒拉料信息", protocols="http")
	@RequestMapping(value = "/remindtransport", method = RequestMethod.GET, produces = {"application/json"})
	BaseResponse<DtoResult> remindTransport();
	
	@ApiOperation(value="每天指定时间所库存失败备货提醒", notes="每天指定时间所库存失败备货提醒", protocols="http")
	@RequestMapping(value = "/remindpreparingmaterial", method = RequestMethod.GET, produces = {"application/json"})
	BaseResponse<DtoResult> remindPreparingMaterial();
}


