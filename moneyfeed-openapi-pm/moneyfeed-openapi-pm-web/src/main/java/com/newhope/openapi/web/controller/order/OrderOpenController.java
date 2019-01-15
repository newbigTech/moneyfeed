package com.newhope.openapi.web.controller.order;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.rest.order.OrderOpenAPI;
import com.newhope.openapi.api.vo.request.order.OrderCloseReq;
import com.newhope.openapi.api.vo.request.order.OrderProxyCreateReq;
import com.newhope.openapi.api.vo.request.order.OrderFeedTransportModifyReq;
import com.newhope.openapi.api.vo.request.order.OrderInfoQueryReq;
import com.newhope.openapi.api.vo.response.order.OrderProxyCreateResult;
import com.newhope.openapi.api.vo.response.order.OrderDetailResult;
import com.newhope.openapi.api.vo.response.order.OrderInfoListResult;
import com.newhope.openapi.biz.service.order.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class OrderOpenController extends AbstractController implements OrderOpenAPI {

	@Autowired
	OrderInfoService orderInfoService;

	@Override
	public BaseResponse<OrderInfoListResult> queryOrderInfoList(OrderInfoQueryReq req) {
		OrderInfoListResult result = orderInfoService.queryOrderInfoList(req);
		return buildJson(result);
	}

	@Override
	public void exportOrderInfoList(OrderInfoQueryReq req, HttpServletResponse response) {
		orderInfoService.exportOrderInfoList(req, response);
	}

	@Override
	public BaseResponse<Result> modifyOrderFeedTransport(@RequestBody OrderFeedTransportModifyReq requestBody) {
		Result result = orderInfoService.modifyOrderFeedTransport(requestBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<OrderDetailResult> queryOrderDetail(@RequestParam(name = "orderId",required = false)Long orderId,@RequestParam(name = "orderNo",required = false)String orderNo) {

		OrderDetailResult result = orderInfoService.queryOrderDetail(orderId,orderNo);
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> orderClose(OrderCloseReq orderCloseReq) {
		Result result = orderInfoService.orderClose(orderCloseReq);
		return buildJson(result);
	}

	@Override
	public BaseResponse<OrderProxyCreateResult> proxyUserCreateOrder(@RequestBody @Valid OrderProxyCreateReq reqBody) {
		return buildJson(orderInfoService.proxyUserCreateOrder(reqBody));
	}

	@Override
	public void exportOrderGoodsDetailList(OrderInfoQueryReq req, HttpServletResponse response) {
		orderInfoService.exportOrderGoodsDetailList(req, response);
	}

}
