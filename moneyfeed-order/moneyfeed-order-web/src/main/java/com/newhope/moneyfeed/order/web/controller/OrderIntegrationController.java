package com.newhope.moneyfeed.order.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.order.api.dto.request.integration.CancelOrderResultDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.IntegrationTimeInfoDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.ReceiveOrderCreateInfoDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.ReceiveOrderPayInfoDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.RepositoryLowDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.TransportInfoToEbsResultDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderValidateRepositoryDtoResult;
import com.newhope.moneyfeed.order.api.rest.OrderIntegrationAPI;
import com.newhope.order.biz.service.OrderIntegrationService;

@RestController
public class OrderIntegrationController extends AbstractController implements OrderIntegrationAPI {

	@Autowired
	OrderIntegrationService orderIntegrationService;
	
	@Override
	public BaseResponse<DtoResult> receiveOrderCreateInfo(@Valid @RequestBody ReceiveOrderCreateInfoDtoReq dtoReq) {
		
		DtoResult result = orderIntegrationService.receiveOrderCreateInfo(dtoReq);
		return buildJson(result);
	}

	@Override
	public BaseResponse<DtoResult> receiveOrderPayInfo(@RequestBody ReceiveOrderPayInfoDtoReq dtoReq) {
		DtoResult result = DtoResult.success();//orderIntegrationService.receiveOrderPayInfo(dtoReq);
		return buildJson(result);
	}
	
	@Override
	public BaseResponse<DtoResult> reciveCancelOrderResult(@Valid @RequestBody CancelOrderResultDtoReq cancelOrderResultDtoReq) {
		DtoResult dtoResult = orderIntegrationService.reciveCancelOrderResult(cancelOrderResultDtoReq);
		return buildJson(dtoResult);
	}

	@Override
	public BaseResponse<DtoResult> reciveRepositoryLow(@RequestBody RepositoryLowDtoReq repositoryLowDtoReq) {
		DtoResult dtoResult = orderIntegrationService.reciveRepositoryLow(repositoryLowDtoReq);
		return buildJson(dtoResult);
	}

	@Override
	public void pullOrderInfo() {
		orderIntegrationService.pullOrderEventInfo();
	}

	@Override
	public BaseResponse<DtoResult> reciveTransportInfoToEbsResult(@RequestBody TransportInfoToEbsResultDtoReq transportInfoToEbsResultDtoReq) {
		DtoResult dtoResult = orderIntegrationService.reciveTransportInfoToEbsResult(transportInfoToEbsResultDtoReq);
		return buildJson(dtoResult);
	}

	@Override
	public void reciveEbsOrderInfo(@RequestBody IntegrationTimeInfoDtoReq integrationTimeInfoDtoReq) {
		orderIntegrationService.reciveIntegrationOrderInfo(integrationTimeInfoDtoReq);
	}

	@Override
	public BaseResponse<OrderValidateRepositoryDtoResult> validateRepository(Long orderId) {
		OrderValidateRepositoryDtoResult orderValidateRepositoryDtoResult = orderIntegrationService.validateRepository(orderId);
		return buildJson(orderValidateRepositoryDtoResult);
	}
	
}
