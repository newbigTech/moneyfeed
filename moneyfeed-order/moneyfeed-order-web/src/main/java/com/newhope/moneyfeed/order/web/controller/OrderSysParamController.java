package com.newhope.moneyfeed.order.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.order.api.dto.request.base.OrderSysParamQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.base.OrderSysParamListDtoResult;
import com.newhope.moneyfeed.order.api.rest.OrderSysParamAPI;
import com.newhope.order.biz.service.OrderSysParamService;

@RestController
public class OrderSysParamController extends AbstractController implements OrderSysParamAPI{
    @Autowired
    OrderSysParamService orderSysParamService;

	@Override
	public BaseResponse<OrderSysParamListDtoResult> getSysParam(@RequestBody OrderSysParamQueryDtoReq requestBody) {
		OrderSysParamListDtoResult result = orderSysParamService.getSysParam(requestBody);
		return buildJson(result);
	}
}
