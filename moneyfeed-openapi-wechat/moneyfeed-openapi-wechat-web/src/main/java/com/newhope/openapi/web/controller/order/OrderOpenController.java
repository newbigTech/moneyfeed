package com.newhope.openapi.web.controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.newhope.openapi.api.vo.request.order.OrderModifyOfflinePaymentReq;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.order.api.dto.request.carts.CartsQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.carts.CartsSkuDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.carts.CartsUpdateDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.openapi.api.rest.order.OrderOpenAPI;
import com.newhope.openapi.api.vo.request.order.OrderCloseReq;
import com.newhope.openapi.api.vo.request.order.OrderCreateReq;
import com.newhope.openapi.api.vo.request.order.OrderFeedTransportModifyReq;
import com.newhope.openapi.api.vo.request.order.OrderInfoQueryReq;
import com.newhope.openapi.api.vo.request.order.OrderUpdateReq;
import com.newhope.openapi.api.vo.request.order.carts.CartsSkuReq;
import com.newhope.openapi.api.vo.request.order.carts.CartsUpdateReq;
import com.newhope.openapi.api.vo.response.order.OrderDetailResult;
import com.newhope.openapi.api.vo.response.order.OrderStatusResult;
import com.newhope.openapi.api.vo.response.order.carts.OrderCartsSkuListResult;
import com.newhope.openapi.api.vo.response.order.info.OrderCreateResult;
import com.newhope.openapi.api.vo.response.order.info.OrderInfoListResult;
import com.newhope.openapi.biz.service.order.OrderInfoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderOpenController extends AbstractController implements OrderOpenAPI {

	@Autowired
	OrderInfoService orderInfoService;
	@Autowired
	RSessionCache rSessionCache;
	
	@Override
	public BaseResponse<OrderCreateResult> createOrder(@RequestBody @Valid OrderCreateReq reqBody) {
		/**登录验证*/
		ClientUserCacheDtoResult userInfo = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if( userInfo == null ){
			return buildJson( ResultCode.USER_LOGIN_REQUIRED.getCode() , ResultCode.USER_LOGIN_REQUIRED.getDesc(), null );
		}
		/**创建订单*/
		OrderCreateResult result = orderInfoService.createOrder( reqBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<OrderInfoListResult> queryOrderInfoList(OrderInfoQueryReq req) {
		OrderInfoListResult result = orderInfoService.queryOrderInfoList(req);
		return buildJson(result);
	}

	@Override
	public BaseResponse<OrderDetailResult> queryOrderDetail(Long orderId,String orderNo) {
		OrderDetailResult result = orderInfoService.queryOrderDetail(orderId,orderNo);
		return buildJson(result);
	}
	@Override
	public BaseResponse<Result> orderClose(@RequestBody OrderCloseReq orderCloseReq) {
		Result result = orderInfoService.orderClose(orderCloseReq);
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> modifyOrderFeedTransport(@Valid @RequestBody OrderFeedTransportModifyReq requestBody) {
		Result result = orderInfoService.modifyOrderFeedTransport(requestBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<OrderStatusResult> orderStatus(Long orderId) {
		OrderStatusResult result = orderInfoService.orderStatus(orderId);
		return buildJson(result);
	}

	@Override
	public BaseResponse<OrderCartsSkuListResult> carts() {
		/**登录验证*/
		ClientUserCacheDtoResult userInfo = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if( userInfo == null ){
			return buildJson( ResultCode.USER_LOGIN_REQUIRED.getCode() , ResultCode.USER_LOGIN_REQUIRED.getDesc(), null );
		}
		CartsQueryDtoReq dtoReq = new CartsQueryDtoReq();
		dtoReq.setBuyerId(userInfo.getUser().getId());
		dtoReq.setCustomerNo(userInfo.getCustomer().getCustomer().getEbsCustomerNum());
		dtoReq.setUcShopId(userInfo.getVisitShop().getShop().getId());
		OrderCartsSkuListResult result = orderInfoService.carts(dtoReq);
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> updateCarts(@RequestBody CartsUpdateReq requestBody) {
		/**登录验证*/
		ClientUserCacheDtoResult userInfo = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if( userInfo == null ){
			return buildJson( ResultCode.USER_LOGIN_REQUIRED.getCode() , ResultCode.USER_LOGIN_REQUIRED.getDesc(), null );
		}
		CartsUpdateDtoReq dtoReq = new CartsUpdateDtoReq();
		dtoReq.setBuyerId(userInfo.getUser().getId());
		dtoReq.setCustomerNo(userInfo.getCustomer().getCustomer().getEbsCustomerNum());
		dtoReq.setUcShopId(userInfo.getVisitShop().getShop().getId());
		if(CollectionUtils.isNotEmpty(requestBody.getItems())){
			List<CartsSkuDtoReq> skuDtos = new ArrayList<>();
			for(CartsSkuReq sku : requestBody.getItems()){
				CartsSkuDtoReq skuDto = new CartsSkuDtoReq();
				skuDto.setQuantity(sku.getQuantity());
				skuDto.setSkuId(sku.getSkuId());
				skuDtos.add(skuDto);
			}
			dtoReq.setItems(skuDtos);
		}
		
		Result result = orderInfoService.updateCarts(dtoReq);
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> modifyOrderByOfflinePayment(@RequestBody OrderModifyOfflinePaymentReq requestBody) {
		Result result = orderInfoService.modifyOrderByOfflinePayment(requestBody.getOrderNo());
		return buildJson(result);
	}

	@Override
	public BaseResponse<OrderCreateResult> updateNHOrder(@RequestBody OrderUpdateReq reqBody) {
		OrderCreateResult result = orderInfoService.updateNHOrder(reqBody);
		return buildJson(result);
	}
}
