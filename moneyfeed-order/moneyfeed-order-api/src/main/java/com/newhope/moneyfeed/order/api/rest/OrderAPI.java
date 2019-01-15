package com.newhope.moneyfeed.order.api.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.order.api.dto.request.carts.CartsQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.carts.CartsUpdateDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderCloseDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderDetailDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoModifyDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderLogDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.nh.OrderNHCreateDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.nh.OrderNHUpdateDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.OrderDetailDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.OrderListDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.OrderStatusDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.carts.OrderCartsSkuListDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderCreateDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderGoodsDetailListDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.PayOrderNoDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(value = "OrderAPI", description = "order", protocols = "http")
@RequestMapping(value="/oc")
public interface OrderAPI {

	@ApiOperation(value="创建订单-内部业务", notes="创建订单-内部业务", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="reqBody", value="reqBody", required=true, paramType="body", dataType="OrderCreateNHDtoReq")
	})
	@RequestMapping(value = "/order/nh", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<OrderCreateDtoResult> createNHOrder(@RequestBody  OrderNHCreateDtoReq reqBody);
	
	@ApiOperation(value="修改订单-内部业务", notes="修改订单-内部业务", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="reqBody", value="reqBody", required=true, paramType="body", dataType="OrderNHUpdateDtoReq")
	})
	@RequestMapping(value = "/order/nh", method = RequestMethod.PUT, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<OrderCreateDtoResult> updateNHOrder(@RequestBody  OrderNHUpdateDtoReq reqBody);
	
	@ApiOperation(value = "查询订单列表", notes = "查询订单列表", protocols = "http")
	@RequestMapping(value = "/order/info/list", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<OrderListDtoResult> queryOrderInfoList(@RequestBody OrderInfoQueryDtoReq req);

	@ApiOperation(value="订单详情", notes="订单详情", protocols="http")
	@RequestMapping(value = "/order/detail", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	BaseResponse<OrderDetailDtoResult> orderDetail(@RequestBody OrderDetailDtoReq requestBody);

	@ApiOperation(value="添加订单表更日志", notes="添加订单表更日志", protocols="http")
	@RequestMapping(value = "/order/log", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	BaseResponse<DtoResult> orderLog(@RequestBody OrderLogDtoReq requestBody);

	@ApiOperation(value="更新订单信息", notes="更新订单信息", protocols="http")
	@RequestMapping(value = "/order/modify", method = RequestMethod.PUT, consumes = {"application/json"}, produces={"application/json"})
	BaseResponse<DtoResult> updateOrderInfo(@RequestBody OrderInfoModifyDtoReq requestBody);

	@ApiOperation(value="关闭订单", notes="关闭订单", protocols="http")
	@RequestMapping(value = "/order/close", method = RequestMethod.PUT, consumes = {"application/json"}, produces={"application/json"})
	BaseResponse<DtoResult> closeOrder(@RequestBody OrderCloseDtoReq requestBody);
	
	@ApiOperation(value="订单状态", notes="订单状态", protocols="http")
	@RequestMapping(value = "/order/status", method = RequestMethod.GET)
	BaseResponse<OrderStatusDtoResult> orderStatus(@RequestParam("orderId") Long orderId);

	@ApiOperation(value = "查询订单商品明细列表", notes = "查询订单商品明细列表", protocols = "http")
	@RequestMapping(value = "/order/goods/detail/list", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<OrderGoodsDetailListDtoResult> queryOrderGoodsDetailList(@RequestBody OrderInfoQueryDtoReq req);
	
	@ApiOperation(value="获取购物车商品列表", notes="获取购物车商品列表", protocols="http")
	@RequestMapping(value = "/order/carts", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	public BaseResponse<OrderCartsSkuListDtoResult> carts(@RequestBody CartsQueryDtoReq requstBody);
	
	@ApiOperation(value="更新购物车", notes="更新购物车", protocols="http")
	@RequestMapping(value = "/order/carts", method = RequestMethod.PUT, produces = {"application/json"},consumes={"application/json"})
	public BaseResponse<DtoResult> updateCarts(@RequestBody CartsUpdateDtoReq requestBody);


	@ApiOperation(value = "生成支付订单号", notes = "生成支付订单号", protocols = "http")
	@RequestMapping(value = "/order/payno", method = RequestMethod.POST, produces = {"application/json"},consumes={"application/json"})
	BaseResponse<PayOrderNoDtoResult> genPayNo();

	@ApiOperation(value="进厂刷卡支付", notes="进厂刷卡支付", protocols="http")
	@RequestMapping(value = "/order/offline", method = RequestMethod.PUT,consumes = {"application/json"}, produces={"application/json"})
	BaseResponse<DtoResult> modifyOrderByOfflinePayment(@RequestParam("orderNo") String orderNo);

}


