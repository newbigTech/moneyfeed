package com.newhope.moneyfeed.order.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.order.api.dto.request.feedtransport.OrderFeedTransportModifyDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.feedtransport.OrderFeedTransportQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.OrderTransportListDtoResult;
import com.newhope.moneyfeed.order.api.rest.OrderFeedTransportAPI;
import com.newhope.order.biz.service.OrderFeedTransportService;

@RestController
public class OrderFeedTransportController extends AbstractController implements OrderFeedTransportAPI{
	
	@Autowired
	OrderFeedTransportService orderFeedTransportService;

	@Override
	public BaseResponse<DtoResult> updateFeedTransport(@RequestBody OrderFeedTransportModifyDtoReq dtoReq) {
		
		DtoResult dtoResult = orderFeedTransportService.modifyFeedTransport(dtoReq);
		return buildJson(dtoResult);
	}

	@Override
	public BaseResponse<OrderTransportListDtoResult> queryFeedTransport(@RequestBody OrderFeedTransportQueryDtoReq dtoReq) {
		OrderTransportListDtoResult orderTransportListDtoResult = orderFeedTransportService.queryFeedTransport(dtoReq);
		return buildJson(orderTransportListDtoResult);
	}
}
