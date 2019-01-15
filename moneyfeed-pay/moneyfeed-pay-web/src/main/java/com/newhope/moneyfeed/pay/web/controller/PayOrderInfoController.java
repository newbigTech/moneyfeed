package com.newhope.moneyfeed.pay.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderCallbackDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderInfoDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PaySpecialDtoReq;
import com.newhope.moneyfeed.pay.api.dto.response.PayCallbackDtoResult;
import com.newhope.moneyfeed.pay.api.dto.response.PayOrderCreateDtoResult;
import com.newhope.moneyfeed.pay.api.dto.response.PayOrderListDtoResult;
import com.newhope.moneyfeed.pay.api.rest.pay.PayOrderInfoAPI;
import com.newhope.pay.biz.service.PayOrderService;

@RestController
public class PayOrderInfoController extends AbstractController implements PayOrderInfoAPI {

	private static Logger logger = LoggerFactory.getLogger(PayOrderInfoController.class);
	
	@Autowired
	PayOrderService payOrderService;
	@Override
	public BaseResponse<PayOrderCreateDtoResult> createPayOrder(@RequestBody PayOrderInfoDtoReq payOrderInfoDtoReq) {
		PayOrderCreateDtoResult payOrderInfoDtoResult = payOrderService.createPayOrder(payOrderInfoDtoReq);
		return buildJson(payOrderInfoDtoResult);
	}
	
	@Override
	public BaseResponse<PayOrderCreateDtoResult> createPayUser(@RequestBody PayOrderInfoDtoReq payOrderInfoDtoReq) {
		PayOrderCreateDtoResult payOrderInfoDtoResult = payOrderService.createPayUserAccOrder(payOrderInfoDtoReq);
		return buildJson(payOrderInfoDtoResult);
	}
	
	@Override
	public BaseResponse<PayCallbackDtoResult> payCallback(@RequestBody PayOrderCallbackDtoReq reqBody) {
		PayCallbackDtoResult dtoResult = payOrderService.payCallback(reqBody);
		return buildJson(dtoResult);
	}

	@Override
	public BaseResponse<DtoResult> paySpecial(@RequestBody PaySpecialDtoReq paySpecialDtoReq) {
		DtoResult dtoResult = payOrderService.paySpecial(paySpecialDtoReq);
		return buildJson(dtoResult);
	}

	@Override
	public BaseResponse<PayOrderListDtoResult> queryPayOrderList(@RequestBody PayOrderDtoReq payOrderDtoReq) {
		PayOrderListDtoResult dtoResult = payOrderService.queryPayOrderList(payOrderDtoReq);
		return buildJson(dtoResult);
	}


}

