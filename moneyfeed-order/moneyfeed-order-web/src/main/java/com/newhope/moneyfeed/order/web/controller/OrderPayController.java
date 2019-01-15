package com.newhope.moneyfeed.order.web.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.order.api.dto.request.order.pay.OrderPayByEBSDtoReq;
import com.newhope.moneyfeed.order.api.rest.OrderPayAPI;
import com.newhope.moneyfeed.pay.api.dto.req.PaySpecialDtoReq;
import com.newhope.order.biz.service.OrderPayService;

@RestController
public class OrderPayController extends AbstractController implements OrderPayAPI{
	private static Logger logger = LoggerFactory.getLogger(OrderPayController.class);
	
	@Autowired
	OrderPayService orderPayService;

	@Override
	public BaseResponse<DtoResult> orderPayByEBS(@Valid @RequestBody OrderPayByEBSDtoReq dtoReq) {
		DtoResult result = orderPayService.orderPayByEBS(dtoReq);
		return buildJson(result);
	}

	@Override
	public BaseResponse<DtoResult> paySpecial(@RequestBody PaySpecialDtoReq paySpecialDtoReq) {
		logger.info("[OrderPayController.paySpecial]特殊处理传入的参数" + JSON.toJSONString(paySpecialDtoReq));
		DtoResult dtoResult = orderPayService.paySpecial(paySpecialDtoReq);
		return buildJson(dtoResult);
	}
	
	
}
