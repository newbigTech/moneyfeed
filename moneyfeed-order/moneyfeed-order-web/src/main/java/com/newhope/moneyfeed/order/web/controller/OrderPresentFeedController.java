package com.newhope.moneyfeed.order.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.order.api.dto.request.present.OrderPresentFeedQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.present.OrderPresentFeedListDtoResult;
import com.newhope.moneyfeed.order.api.rest.OrderPresentFeedAPI;
import com.newhope.order.biz.service.OrderPresentFeedService;

@RestController
public class OrderPresentFeedController extends AbstractController implements OrderPresentFeedAPI {

	@Autowired
	OrderPresentFeedService OrderPresentFeedService;

	@Override
	public BaseResponse<OrderPresentFeedListDtoResult> queryOrderPresentFeed(@RequestBody OrderPresentFeedQueryDtoReq dtoReq) {
		OrderPresentFeedListDtoResult orderPresentFeedListDtoResult = OrderPresentFeedService.queryOrderPresentFeed(dtoReq);
		return buildJson(orderPresentFeedListDtoResult);
	}

}
