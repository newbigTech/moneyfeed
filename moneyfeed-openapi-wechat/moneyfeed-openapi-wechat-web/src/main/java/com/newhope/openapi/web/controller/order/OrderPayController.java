package com.newhope.openapi.web.controller.order;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.rest.order.OrderPayAPI;
import com.newhope.openapi.api.vo.request.order.pay.OrderPayByEBSReq;
import com.newhope.openapi.api.vo.request.order.pay.PayPasswordVerifyReq;
import com.newhope.openapi.api.vo.response.order.pay.PayAccountInfoResult;
import com.newhope.openapi.api.vo.response.order.pay.PayByEBSValidateResult;
import com.newhope.openapi.biz.service.order.OrderPayService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 订单支付
 * @author hp
 *
 */
@RestController
public class OrderPayController extends AbstractController implements OrderPayAPI {

	@Autowired
	OrderPayService orderPayService;
	
	@Override
	public BaseResponse<PayAccountInfoResult> getAccountInfo(@RequestParam(name = "orderId",required = true) Long orderId) {
		PayAccountInfoResult result = orderPayService.getAccountInfo(orderId);
		return buildJson(result);
	}

	@Override
	public BaseResponse<DtoResult> orderPayByEBS(@Valid @RequestBody OrderPayByEBSReq req) {
		DtoResult result = orderPayService.orderPayByEBS(req);
		BaseResponse<DtoResult> baseResponse = buildJson(result);
		if (ResultCode.USER_PAY_PASS_ERROR.getCode().equals(result.getCode())){
			baseResponse.setData(result);
		}
		return baseResponse;
	}

	@Override
	public BaseResponse<DtoResult> inventoryVerify(@RequestParam(name = "orderId",required = true) Long orderId) {
		DtoResult result = orderPayService.inventoryVerify(orderId);
		return buildJson(result);
	}
	
	@Override
	public BaseResponse<DtoResult> passwordVerify(@Valid @RequestBody PayPasswordVerifyReq req) {
		DtoResult result = orderPayService.passwordVerify(req);
		BaseResponse<DtoResult> baseResponse = buildJson(result);
		if (ResultCode.USER_PAY_PASS_ERROR.getCode().equals(result.getCode())){
			baseResponse.setData(result);
		}
		return baseResponse;
	}

	@Override
	public BaseResponse<PayByEBSValidateResult> orderPayByEBSValidate(@RequestParam(name = "orderId",required = true) Long orderId) {
		PayByEBSValidateResult result = orderPayService.orderPayByEBSValidate(orderId);
		BaseResponse<PayByEBSValidateResult> baseResponse = buildJson(result);
		if (ResultCode.USER_PAY_PASS_UN_EXIST.getCode().equals(result.getCode())){
			baseResponse.setData(result);
		}
		return baseResponse;
	}
	
}
