package com.newhope.openapi.api.rest.order;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.order.OrderCloseReq;
import com.newhope.openapi.api.vo.request.order.OrderCreateReq;
import com.newhope.openapi.api.vo.request.order.OrderFeedTransportModifyReq;
import com.newhope.openapi.api.vo.request.order.OrderInfoQueryReq;
import com.newhope.openapi.api.vo.request.order.OrderModifyOfflinePaymentReq;
import com.newhope.openapi.api.vo.request.order.OrderUpdateReq;
import com.newhope.openapi.api.vo.request.order.carts.CartsUpdateReq;
import com.newhope.openapi.api.vo.response.order.OrderDetailResult;
import com.newhope.openapi.api.vo.response.order.OrderStatusResult;
import com.newhope.openapi.api.vo.response.order.carts.OrderCartsSkuListResult;
import com.newhope.openapi.api.vo.response.order.info.OrderCreateResult;
import com.newhope.openapi.api.vo.response.order.info.OrderInfoListResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Api(value="OrderOpenAPI", description="订单服务open-api", protocols="http")
public interface OrderOpenAPI {

	@ApiOperation(value="创建订单", notes="", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="reqBody", value="reqBody", required=true, paramType="body", dataType="OrderCreateReq")
	})
	@RequestMapping(value = { "/order/nh" }, method = RequestMethod.POST,consumes={"application/json"},produces={"application/json"})
	BaseResponse<OrderCreateResult> createOrder(@RequestBody @Valid OrderCreateReq reqBody);
	
	@ApiOperation(value="修改订单", notes="", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="reqBody", value="reqBody", required=true, paramType="body", dataType="OrderUpdateReq")
	})
	@RequestMapping(value = { "/order/nh" }, method = RequestMethod.PUT,consumes={"application/json"},produces={"application/json"})
	BaseResponse<OrderCreateResult> updateNHOrder(@RequestBody @Valid OrderUpdateReq reqBody);
	

	@ApiOperation(value = "查询订单列表", notes = "查询订单列表", protocols = "http")
	@ApiImplicitParams({
							   @ApiImplicitParam(name = "status", value = "订单状态", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "orderBeginDate", value = "下单开始时间yyyy/MM/dd", dataType = "Long", paramType = "query"),
							   @ApiImplicitParam(name = "orderEndDate", value = "下单结束时间yyyy/MM/dd", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "pullBeginDate", value = "拉料开始时间yyyy/MM/dd", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "pullEndDate", value = "拉料结束时间yyyy/MM/dd", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "completeBeginDate", value = "完成起始时间yyyy/MM/dd", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "completeEndDate", value = "完成结束时间yyyy/MM/dd", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "closeBeginDate", value = "关闭起始时间yyyy/MM/dd", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "closeEndDate", value = "关闭结束时间yyyy/MM/dd", dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/order/info/list", method = RequestMethod.GET, produces = {"application/json"})
	BaseResponse<OrderInfoListResult> queryOrderInfoList(OrderInfoQueryReq req);

	@ApiOperation(value="订单详情查询", notes="订单详情查询", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单主键id", required = false, paramType = "query", dataType = "Long"),
			@ApiImplicitParam(name = "orderNo", value = "订单号", required = false, paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/order/detail", method = RequestMethod.GET)
	BaseResponse<OrderDetailResult> queryOrderDetail(@RequestParam(name = "orderId",required = false) Long orderId,@RequestParam(name = "orderNo",required = false) String orderNo);

	@ApiOperation(value="订单关闭", notes="订单关闭", protocols="http")
	@RequestMapping(value = "/order/close", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	BaseResponse<Result> orderClose(@RequestBody  OrderCloseReq orderCloseReq);

	@ApiOperation(value="修改订单拉料信息", notes="修改订单拉料信息", protocols="http")
	@RequestMapping(value = "/order/feedtransport", method = RequestMethod.PUT, consumes = {"application/json"}, produces={"application/json"})
	BaseResponse<Result> modifyOrderFeedTransport(@Valid @RequestBody OrderFeedTransportModifyReq requestBody);

	@ApiOperation(value="订单状态", notes="订单状态", protocols="http")
	@RequestMapping(value = "/order/status", method = RequestMethod.GET, produces = {"application/json"})
	public BaseResponse<OrderStatusResult> orderStatus(@RequestParam("orderId") Long orderId);
	
	@ApiOperation(value="获取购物车商品列表", notes="获取购物车商品列表", protocols="http")
	@RequestMapping(value = "/order/carts", method = RequestMethod.GET, produces = {"application/json"})
	public BaseResponse<OrderCartsSkuListResult> carts();
	
	@ApiOperation(value="更新购物车", notes="更新购物车", protocols="http")
	@RequestMapping(value = "/order/carts", method = RequestMethod.PUT,consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<Result> updateCarts(@RequestBody CartsUpdateReq requestBody);

	@ApiOperation(value="进厂刷卡支付", notes="进厂刷卡支付", protocols="http")
	@RequestMapping(value = "/order/offline", method = RequestMethod.PUT,consumes = {"application/json"}, produces={"application/json"})
	BaseResponse<Result> modifyOrderByOfflinePayment(@RequestBody OrderModifyOfflinePaymentReq requestBody);
	
}