package com.newhope.moneyfeed.order.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.order.api.rest.OrderScheduleAPI;
import com.newhope.order.biz.service.OrderScheduleService;

@RestController
public class OrderScheduleController extends AbstractController implements OrderScheduleAPI{

	@Autowired
	OrderScheduleService orderScheduleService;
	
	@Override
	public BaseResponse<DtoResult> closeUnPaidOrder() {
		
		//关闭超时未支付订单
		orderScheduleService.closeOvertimeNotpaid();
		DtoResult dtoResult = DtoResult.success();
		return buildJson(dtoResult);
	}

	@Override
	public BaseResponse<DtoResult> remindPay() {
		
		orderScheduleService.remindDoPay();
		DtoResult dtoResult = DtoResult.success();
		return buildJson(dtoResult);
	}

	@Override
	public BaseResponse<DtoResult> remindTransport() {
		orderScheduleService.remindTransport();
		DtoResult dtoResult = DtoResult.success();
		return buildJson(dtoResult);
	}

	@Override
	public BaseResponse<DtoResult> remindPreparingMaterial() {
		orderScheduleService.remindPreparingMaterial();
		DtoResult dtoResult = DtoResult.success();
		return buildJson(dtoResult);
	}


}
