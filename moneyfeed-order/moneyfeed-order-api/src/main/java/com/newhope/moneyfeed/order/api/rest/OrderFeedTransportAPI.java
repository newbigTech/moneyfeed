package com.newhope.moneyfeed.order.api.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.order.api.dto.request.feedtransport.OrderFeedTransportModifyDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.feedtransport.OrderFeedTransportQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.OrderTransportListDtoResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "OrderFeedTransportAPI", description = "订单拉料信息", protocols = "http")
@RequestMapping(value="/oc/order/feedtransport")
public interface OrderFeedTransportAPI {

	@ApiOperation(value="修改拉料信息", notes="修改拉料信息", protocols="http")
	@RequestMapping(value = "", method = RequestMethod.PUT, produces = {"application/json"})
	BaseResponse<DtoResult> updateFeedTransport(@RequestBody OrderFeedTransportModifyDtoReq dtoReq);
	
	@ApiOperation(value="查询拉料信息", notes="查询拉料信息", protocols="http")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = {"application/json"})
	BaseResponse<OrderTransportListDtoResult> queryFeedTransport(@RequestBody OrderFeedTransportQueryDtoReq dtoReq);
	
}


