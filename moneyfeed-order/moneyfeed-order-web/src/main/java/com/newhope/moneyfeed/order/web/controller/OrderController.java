package com.newhope.moneyfeed.order.web.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.order.api.bean.OrderInfoModel;
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
import com.newhope.moneyfeed.order.api.dto.response.OrderDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.OrderListDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.OrderStatusDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.carts.OrderCartsSkuListDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderCreateDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderGoodsDetailDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderGoodsDetailListDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.PayOrderNoDtoResult;
import com.newhope.moneyfeed.order.api.rest.OrderAPI;
import com.newhope.order.biz.service.OrderCartService;
import com.newhope.order.biz.service.OrderChangeLogService;
import com.newhope.order.biz.service.OrderDetailService;
import com.newhope.order.biz.service.OrderService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController extends AbstractController implements OrderAPI{

	
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	OrderChangeLogService orderChangeLogService;
	@Autowired
	OrderCartService orderCartService;

	@Override
	public BaseResponse<OrderCreateDtoResult> createNHOrder(@RequestBody OrderNHCreateDtoReq reqBody) {
		OrderCreateDtoResult result = orderService.createNHOrder(reqBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<OrderListDtoResult> queryOrderInfoList(@RequestBody OrderInfoQueryDtoReq req) {
		OrderListDtoResult result = new OrderListDtoResult();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		PageList<OrderDtoResult> list = orderService.queryOrderInfoList(req);
		if (CollectionUtils.isNotEmpty(list)) {
			result.setOrderList(list);
		}
		if (list.getPaginator() != null) {
			result.setPage((long) list.getPaginator().getPage());
			result.setTotalCount((long) list.getPaginator().getTotalCount());
			result.setTotalPage((long) list.getPaginator().getTotalPages());
		}
		return buildJson(result);
	}

	@Override
	public BaseResponse<OrderDetailDtoResult> orderDetail(@RequestBody OrderDetailDtoReq requestBody) {
		OrderDetailDtoResult result = new OrderDetailDtoResult();

		if(requestBody.getOrderId()==null&&StringUtils.isBlank(requestBody.getOrderNo())){
			result.setCode(ResultCode.PARAM_ERROR.getCode());
			result.setMsg(ResultCode.PARAM_ERROR.getDesc());
			return buildJson(result);
		}
		result = orderDetailService.queryOrderDetail(requestBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<DtoResult> orderLog(@RequestBody OrderLogDtoReq requestBody) {
		DtoResult result = orderChangeLogService.addOrderChangeLog(requestBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<DtoResult> updateOrderInfo(@RequestBody OrderInfoModifyDtoReq requestBody) {
		DtoResult result = new DtoResult();

		if(requestBody.getOrderId()==null){
			result.setCode(ResultCode.PARAM_ERROR.getCode());
			result.setMsg(ResultCode.PARAM_ERROR.getDesc());
			return buildJson(result);
		}
		result = orderService.updateOrderInfo(requestBody);
		return buildJson(result);
	}
	
	@Override
	public BaseResponse<DtoResult> closeOrder(@RequestBody OrderCloseDtoReq requestBody) {
		DtoResult result = orderService.closeOrder(requestBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<OrderStatusDtoResult> orderStatus(Long orderId) {

		OrderStatusDtoResult result = new OrderStatusDtoResult();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		if(orderId==null){
			result.setCode(ResultCode.PARAM_ERROR.getCode());
			result.setMsg(ResultCode.PARAM_ERROR.getDesc());
			return buildJson(result);
		}
		OrderInfoModel orderInfoModel = new OrderInfoModel();
		orderInfoModel.setId(orderId);
		List<OrderInfoModel> orderInfoModels = orderService.query(orderInfoModel);
		if(CollectionUtils.isNotEmpty(orderInfoModels)){
			BeanUtils.copyProperties(orderInfoModels.get(0),result);
		}
		return buildJson(result);
	}

	@Override
	public BaseResponse<OrderGoodsDetailListDtoResult> queryOrderGoodsDetailList(@RequestBody OrderInfoQueryDtoReq req) {
		OrderGoodsDetailListDtoResult result = new OrderGoodsDetailListDtoResult();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		if (StringUtils.isEmpty(req.getBankOrderNo()) && StringUtils.isEmpty(req.getMoneyNo()) && StringUtils.isEmpty(req.getBankRefundOrderNo())){
			PageList<OrderGoodsDetailDtoResult> list = orderService.queryOrderGoodsDetailList(req);
			if (CollectionUtils.isNotEmpty(list)) {
				result.setGoodsDetailList(list);
			}
			if (list.getPaginator() != null) {
				result.setPage((long) list.getPaginator().getPage());
				result.setTotalCount((long) list.getPaginator().getTotalCount());
				result.setTotalPage((long) list.getPaginator().getTotalPages());
			}
		}
		return buildJson(result);
	}

	@Override
	public BaseResponse<OrderCartsSkuListDtoResult> carts(@RequestBody CartsQueryDtoReq requstBody) {
		OrderCartsSkuListDtoResult result = orderCartService.getCarts(requstBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<DtoResult> updateCarts(@RequestBody CartsUpdateDtoReq requestBody) {
		DtoResult result = orderCartService.updateCarts(requestBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<OrderCreateDtoResult> updateNHOrder(@RequestBody OrderNHUpdateDtoReq reqBody) {
		OrderCreateDtoResult result = orderService.createNHOrderAfterCancel(reqBody);
		return buildJson(result);
	}

	@Override
	public BaseResponse<PayOrderNoDtoResult> genPayNo() {
		PayOrderNoDtoResult payOrderNoDtoResult = orderService.genPayNo();
		return buildJson(payOrderNoDtoResult);
	}

	@Override
	public BaseResponse<DtoResult> modifyOrderByOfflinePayment(String orderNo) {
		DtoResult dtoResult = orderService.modifyOrderByOfflinePayment(orderNo);
		return buildJson(dtoResult);
	}
	
}
