package com.newhope.moneyfeed.order.api.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.order.api.dto.request.present.OrderPresentFeedQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.present.OrderPresentFeedListDtoResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "OrderPresentFeedAPI", description = "订单赠包信息", protocols = "http")
@RequestMapping(value = "/oc/order/present")
public interface OrderPresentFeedAPI {
	
	@ApiOperation(value = "查询赠包信息", notes = "查询赠包信息", protocols = "http")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = { "application/json" })
	BaseResponse<OrderPresentFeedListDtoResult> queryOrderPresentFeed(@RequestBody OrderPresentFeedQueryDtoReq dtoReq);

}
