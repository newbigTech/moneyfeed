package com.newhope.openapi.api.rest.order;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.order.OrderCloseReq;
import com.newhope.openapi.api.vo.request.order.OrderProxyCreateReq;
import com.newhope.openapi.api.vo.request.order.OrderFeedTransportModifyReq;
import com.newhope.openapi.api.vo.request.order.OrderInfoQueryReq;
import com.newhope.openapi.api.vo.response.order.OrderProxyCreateResult;
import com.newhope.openapi.api.vo.response.order.OrderDetailResult;
import com.newhope.openapi.api.vo.response.order.OrderInfoListResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api(value="OrderOpenAPI", description="订单服务open-api", protocols="http")
public interface OrderOpenAPI {

	@ApiOperation(value = "查询订单列表", notes = "查询订单列表", protocols = "http")
	@ApiImplicitParams({
							   @ApiImplicitParam(name = "ucShopId", value = "商户店铺ID", dataType = "Long", paramType = "query"),
							   @ApiImplicitParam(name = "orderNo", value = "订单号", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "shortNo", value = "订单展示号", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "ebsorderNo", value = "EBS订单号", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "moneyNo", value = "资金订单号", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "ebsRefundOrderNo", value = "EBS退款订单号", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "bankRefundOrderNo", value = "银行退款流水号", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "bankOrderNo", value = "银行流水号", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "customerId", value = "客户ID", dataType = "Long", paramType = "query"),
							   @ApiImplicitParam(name = "customerName", value = "客户名", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "status", value = "订单状态", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "orderBeginDate", value = "下单开始时间yyyy/MM/dd", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "orderEndDate", value = "下单结束时间yyyy/MM/dd", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "completeBeginDate", value = "完成起始时间yyyy/MM/dd", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "completeEndDate", value = "完成结束时间yyyy/MM/dd", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "planTransportBeginDate", value = "预计拉料起始日期yyyy/MM/dd", dataType = "String", paramType = "query"),
							   @ApiImplicitParam(name = "planTransportEndDate", value = "预计拉料结束日期yyyy/MM/dd", dataType = "String", paramType = "query"),
					   })
	@RequestMapping(value = "/order/info/list", method = RequestMethod.GET, produces={"application/json"})
	BaseResponse<OrderInfoListResult> queryOrderInfoList(OrderInfoQueryReq req);

	@ApiOperation(value = "订单列表导出", notes = "订单列表导出", protocols = "http")
	@RequestMapping(value = "/order/info/list/export", method = RequestMethod.GET)
	void exportOrderInfoList(OrderInfoQueryReq req, HttpServletResponse response);

	@ApiOperation(value="订单详情编辑", notes="订单详情编辑", protocols="http")
	@RequestMapping(value = "/order/transport", method = RequestMethod.PUT, consumes = {"application/json"}, produces={"application/json"})
	BaseResponse<Result> modifyOrderFeedTransport(@RequestBody OrderFeedTransportModifyReq requestBody);

	@ApiOperation(value="订单详情查询", notes="订单详情查询", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单主键id", required = false, paramType = "query", dataType = "Long"),
			@ApiImplicitParam(name = "orderNo", value = "订单号", required = false, paramType = "query", dataType = "Long")
	})
	@RequestMapping(value = "/order/detail", method = RequestMethod.GET)
	BaseResponse<OrderDetailResult> queryOrderDetail(@RequestParam(name = "orderId",required = false) Long orderId,@RequestParam(name = "orderNo",required = false) String orderNo);


	@ApiOperation(value="订单关闭", notes="订单关闭", protocols="http")
	@RequestMapping(value = "/order/close", method = RequestMethod.PUT, consumes = {"application/json"}, produces={"application/json"})
	BaseResponse<Result> orderClose(@RequestBody @Valid OrderCloseReq orderCloseReq);

	@ApiOperation(value="代用户创建订单", notes="", protocols="http")
	@RequestMapping(value = { "/order/nh/proxy" }, method = RequestMethod.POST,consumes={"application/json"},produces={"application/json"})
	BaseResponse<OrderProxyCreateResult> proxyUserCreateOrder(@RequestBody @Valid OrderProxyCreateReq reqBody);

	@ApiOperation(value = "订单商品明细列表导出", notes = "订单商品明细列表导出", protocols = "http")
	@RequestMapping(value = "/order/info/deatil/list/export", method = RequestMethod.GET)
	void exportOrderGoodsDetailList(OrderInfoQueryReq req, HttpServletResponse response);
}