package com.newhope.openapi.biz.service.order;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.common.helper.DateUtils;
import com.newhope.moneyfeed.goods.api.dto.request.ProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuAttributesDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;
import com.newhope.moneyfeed.order.api.constant.CommonConstant;
import com.newhope.moneyfeed.order.api.dto.request.base.OrderSysParamQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.carts.CartsQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.carts.CartsUpdateDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.feedtransport.OrderFeedTransportModifyDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderCloseDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderDetailDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoModifyDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.nh.OrderNHCreateDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.nh.OrderNHUpdateDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.nh.rule.FeedTransportDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.product.ProductDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.shop.UcShopDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.user.CustomerUserDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.OrderDetailDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.OrderDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.OrderGoodsDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.OrderListDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.OrderPresentDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.OrderStatusDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.base.OrderSysParamDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.base.OrderSysParamListDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.carts.OrderCartsSkuDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.carts.OrderCartsSkuListDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderCreateDtoResult;
import com.newhope.moneyfeed.order.api.enums.OrderChannelEnum;
import com.newhope.moneyfeed.order.api.enums.OrderOperationSourceEnums;
import com.newhope.moneyfeed.order.api.enums.OrderPayTypeEnum;
import com.newhope.moneyfeed.order.api.enums.OrderStatusEnum;
import com.newhope.moneyfeed.order.api.enums.OrderStatusTypeEnum;
import com.newhope.moneyfeed.order.api.enums.OrderSysParamsCodeEnum;
import com.newhope.moneyfeed.order.api.enums.OrderSysParamsTypeEnum;
import com.newhope.moneyfeed.order.api.enums.PayLimitTypeEnums;
import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerPropertyModel;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.moneyfeed.user.api.enums.client.CustomerPropertyTypeEnums;
import com.newhope.openapi.api.vo.request.order.OrderBasePostReq;
import com.newhope.openapi.api.vo.request.order.OrderCloseReq;
import com.newhope.openapi.api.vo.request.order.OrderCreateReq;
import com.newhope.openapi.api.vo.request.order.OrderFeedTransportModifyReq;
import com.newhope.openapi.api.vo.request.order.OrderInfoQueryReq;
import com.newhope.openapi.api.vo.request.order.OrderUpdateReq;
import com.newhope.openapi.api.vo.request.order.product.ProductItemReq;
import com.newhope.openapi.api.vo.response.order.OrderDetailInfoResult;
import com.newhope.openapi.api.vo.response.order.OrderDetailResult;
import com.newhope.openapi.api.vo.response.order.OrderGoodsResult;
import com.newhope.openapi.api.vo.response.order.OrderPresentResult;
import com.newhope.openapi.api.vo.response.order.OrderStatusResult;
import com.newhope.openapi.api.vo.response.order.OrderTransportResult;
import com.newhope.openapi.api.vo.response.order.base.OrderSysParamResult;
import com.newhope.openapi.api.vo.response.order.carts.OrderCartsSkuListResult;
import com.newhope.openapi.api.vo.response.order.carts.OrderCartsSkuResult;
import com.newhope.openapi.api.vo.response.order.info.OrderCreateResult;
import com.newhope.openapi.api.vo.response.order.info.OrderInfoListResult;
import com.newhope.openapi.api.vo.response.order.info.OrderInfoResult;
import com.newhope.openapi.biz.rpc.feign.order.OrderFeignClient;
import com.newhope.openapi.biz.rpc.feign.order.OrderSysParamFeignClient;
import com.newhope.openapi.biz.rpc.feign.order.OrderTransportFeignClient;
import com.newhope.openapi.biz.rpc.feign.product.ProductFeignClient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.sonar.api.internal.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderInfoService {

	@Autowired
	OrderFeignClient orderFeignClient;
	@Autowired
	ProductFeignClient productFeignClient;
	@Autowired
	RSessionCache rSessionCache;
	@Autowired
	OrderTransportFeignClient orderTransportFeignClient;
	@Autowired
	OrderSysParamFeignClient orderSysParamFeignClient;

	/**
	 * 创建订单
	 * 
	 * @creator : pudongliang
	 * @dateTime : 2018年11月24日下午2:10:33
	 */
	public OrderCreateResult createOrder(OrderCreateReq reqBody) {
		OrderCreateResult result = new OrderCreateResult();
		/** 登录验证 */
		ClientUserCacheDtoResult userInfo = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if (userInfo == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			return result;
		}
		BaseResponse<OrderCreateDtoResult> response = orderFeignClient.createNHOrder(buildOrderInfo(buildOrderUserInfo(userInfo), reqBody));
		BeanUtils.copyProperties(response, result);
		if (ResultCode.SUCCESS.getCode().equals(response.getCode()) && null != response.getData()) {
			result.setOrderNo(response.getData().getOrderNo());
		}
		return result;
	}

	/**
	 * 查询订单列表
	 *
	 * @author: dongql
	 * @date: 2018/11/19 15:47
	 * @param req
	 * @return: com.newhope.openapi.api.vo.response.order.info.
	 *          OrderInfoListResult
	 */
	public OrderInfoListResult queryOrderInfoList(OrderInfoQueryReq req) {
		OrderInfoListResult result = new OrderInfoListResult();
		OrderInfoQueryDtoReq dtoReq = new OrderInfoQueryDtoReq();
		wrapRequestParam(req, dtoReq);

		ClientUserCacheDtoResult userInfo = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if (userInfo == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return result;
		}
		dtoReq.setCustomerId(userInfo.getCustomer().getCustomer().getId());
		dtoReq.setUcShopId(userInfo.getVisitShop().getShop().getId());

		BaseResponse<OrderListDtoResult> response = orderFeignClient.queryOrderInfoList(dtoReq);
		result.setCode(response.getCode());
		result.setMsg(response.getMsg());
		if (ResultCode.SUCCESS.getCode().equals(response.getCode()) && null != response.getData()) {
			ArrayList<OrderInfoResult> orderInfoResults = new ArrayList<>();
			result.setPage(response.getData().getPage());
			result.setTotalCount(response.getData().getTotalCount());
			result.setTotalPage(response.getData().getTotalPage());
			List<OrderDtoResult> orderList = response.getData().getOrderList();
			if (CollectionUtils.isNotEmpty(orderList)) {
				for (OrderDtoResult dtoResult : orderList) {
					OrderInfoResult orderInfoResult = new OrderInfoResult();
					BeanUtils.copyProperties(dtoResult, orderInfoResult);
					setPayAmount(dtoResult, orderInfoResult);
					// 根据订单支付限制时间类型计算支付倒计时时间
					if (StringUtils.isNotEmpty(dtoResult.getLimitTimeType())) {
						if (dtoResult.getPayLimitTime() != null
								&& PayLimitTypeEnums.LIMIT_TIME_PRICE.getValue().equals(dtoResult.getLimitTimeType())) {
							orderInfoResult.setPayLimitTime(
									dtoResult.getPayLimitTime().getTime() - System.currentTimeMillis());
						}
					}
					// 添加赠包重量
					if (null != dtoResult.getTotalPresent()) {
						orderInfoResult.setTotalFeedWeight(
								orderInfoResult.getTotalFeedWeight().add(dtoResult.getTotalPresent()));
					}
					orderInfoResults.add(orderInfoResult);
				}
				result.setOrderList(orderInfoResults);
			}
		}
		return result;
	}

	/**
	 * 对于核价中的订单，将 totalOrginalAmount 设置到 totalPayAmount
	 * @param dtoResult
	 * @param orderInfoResult
	 */
	private void setPayAmount(OrderDtoResult dtoResult, OrderInfoResult orderInfoResult) {
		if(orderInfoResult.getTotalPayAmount() == null) {
			orderInfoResult.setTotalPayAmount(dtoResult.getTotalOrginalAmount());
		}
	}

	/**
	 * 包装请求参数
	 *
	 * @author: dongql
	 * @date: 2018/11/24 11:46
	 * @param req
	 * @return: void
	 */
	private void wrapRequestParam(OrderInfoQueryReq req, OrderInfoQueryDtoReq dtoReq) {
		if (StringUtils.isNotEmpty(req.getOrderBeginDate())) {
			req.setOrderBeginDate(req.getOrderBeginDate() + DateUtils.BEGIN_TIME_SUFFIX);
		}
		if (StringUtils.isNotEmpty(req.getOrderEndDate())) {
			req.setOrderEndDate(req.getOrderEndDate() + DateUtils.END_TIME_SUFFIX);
		}
		if (StringUtils.isNotEmpty(req.getCompleteBeginDate())) {
			req.setCompleteBeginDate(req.getCompleteBeginDate() + DateUtils.BEGIN_TIME_SUFFIX);
		}
		if (StringUtils.isNotEmpty(req.getCompleteEndDate())) {
			req.setCompleteEndDate(req.getCompleteEndDate() + DateUtils.END_TIME_SUFFIX);
		}
		if (StringUtils.isNotEmpty(req.getPullBeginDate())) {
			req.setPullBeginDate(req.getPullBeginDate() + DateUtils.BEGIN_TIME_SUFFIX);
		}
		if (StringUtils.isNotEmpty(req.getPullEndDate())) {
			req.setPullEndDate(req.getPullEndDate() + DateUtils.END_TIME_SUFFIX);
		}
		if (StringUtils.isNotEmpty(req.getCloseBeginDate())) {
			req.setCloseBeginDate(req.getCloseBeginDate() + DateUtils.BEGIN_TIME_SUFFIX);
		}
		if (StringUtils.isNotEmpty(req.getCloseEndDate())) {
			req.setCloseEndDate(req.getCloseEndDate() + DateUtils.END_TIME_SUFFIX);
		}
		// 包装订单状态tab
		if (OrderStatusTypeEnum.TO_BE_PAID.getValue().equals(req.getStatus())) {
			ArrayList<String> list = new ArrayList<>();
			list.add(OrderStatusEnum.CREATING.getValue());
			list.add(OrderStatusEnum.WAITING_FOR_PAYMENT.getValue());
			list.add(OrderStatusEnum.PAYING.getValue());
			dtoReq.setStatusList(list);
            dtoReq.setPayFlag(Boolean.FALSE);
            
			dtoReq.setOrderBy("gmt_created desc");
		}
		if (OrderStatusTypeEnum.TO_PULL_MATERIAL.getValue().equals(req.getStatus())) {
			ArrayList<String> list = new ArrayList<>();
			list.add(OrderStatusEnum.PAID.getValue());
			list.add(OrderStatusEnum.MATERIAL_PREPARING.getValue());
			list.add(OrderStatusEnum.WAITING_PULL_MATERIAL.getValue());
			list.add(OrderStatusEnum.WAITING_OFF_LINE_PAY.getValue());
			list.add(OrderStatusEnum.ALREADY_ENTRY_FACTORY.getValue());
			dtoReq.setStatusList(list);
			dtoReq.setOrderBy("plan_transport_time");
		}
		if (OrderStatusTypeEnum.COMPLETED.getValue().equals(req.getStatus())) {
			ArrayList<String> list = new ArrayList<>();
			list.add(OrderStatusEnum.COMPLETED.getValue());
			dtoReq.setStatusList(list);
			dtoReq.setOrderBy("end_time desc");
		}
		if (OrderStatusTypeEnum.CLOSED.getValue().equals(req.getStatus())) {
			ArrayList<String> list = new ArrayList<>();
			list.add(OrderStatusEnum.EBS_SYNC_FAILED.getValue());
			list.add(OrderStatusEnum.REFUNDED.getValue());
			list.add(OrderStatusEnum.CUSTOMER_REPEAL.getValue());
			list.add(OrderStatusEnum.SHOP_REPEAL.getValue());
			list.add(OrderStatusEnum.CLOSED.getValue());
			list.add(OrderStatusEnum.CUSTOMER_REPEALING.getValue());
			dtoReq.setStatusList(list);
			dtoReq.setOrderBy("close_time desc");
		}
		BeanUtils.copyProperties(req, dtoReq);
	}

	/**
	 * @Author ：yyq
	 * @Date ：Created in 2018/11/19 0019 19:18
	 * @Param ：
	 */
	public OrderDetailResult queryOrderDetail(Long orderId, String orderNo) {
		OrderDetailResult result = new OrderDetailResult();
		OrderDetailDtoReq dtoReq = new OrderDetailDtoReq();
		dtoReq.setOrderId(orderId);
		dtoReq.setOrderNo(orderNo);
		BaseResponse<OrderDetailDtoResult> response = orderFeignClient.orderDetail(dtoReq);
		BeanUtils.copyProperties(response, result);
		if (!ResultCode.SUCCESS.getCode().equals(response.getCode()) || null == response.getData()) {
			return result;
		}
		if (response.getData().getOrderInfoDtoResult() != null) {
			OrderDetailInfoResult infoResult = new OrderDetailInfoResult();
			BeanUtils.copyProperties(response.getData().getOrderInfoDtoResult(), infoResult);
			// 根据订单支付限制时间类型计算支付倒计时时间
			if (StringUtils.isNotEmpty(response.getData().getOrderInfoDtoResult().getLimitTimeType())) {
				if (response.getData().getOrderInfoDtoResult().getPayLimitTime() != null
						&& PayLimitTypeEnums.LIMIT_TIME_PRICE.getValue()
								.equals(response.getData().getOrderInfoDtoResult().getLimitTimeType())) {
					infoResult.setPayLimitTime(response.getData().getOrderInfoDtoResult().getPayLimitTime().getTime()
							- System.currentTimeMillis());
				}
			}
			result.setOrderInfoDtoResult(infoResult);
		}
		if (response.getData().getOrderTransportDtoResult() != null) {
			OrderTransportResult transportResult = new OrderTransportResult();
			BeanUtils.copyProperties(response.getData().getOrderTransportDtoResult(), transportResult);
			result.setOrderTransportDtoResult(transportResult);
		}
		if (CollectionUtils.isNotEmpty(response.getData().getOrderPresentDtoResultList())) {
			List<OrderPresentResult> orderPresentDtoResultList = new ArrayList<>();
			for (OrderPresentDtoResult presentDtoResult : response.getData().getOrderPresentDtoResultList()) {
				OrderPresentResult orderPresentResult = new OrderPresentResult();
				BeanUtils.copyProperties(presentDtoResult, orderPresentResult);
				orderPresentDtoResultList.add(orderPresentResult);
			}
			result.setOrderPresentDtoResultList(orderPresentDtoResultList);
		}
		if (CollectionUtils.isNotEmpty(response.getData().getOrderGoodsDtoResultList())) {
			List<OrderGoodsResult> orderGoodsResults = new ArrayList<>();
			for (OrderGoodsDtoResult goodsDtoResult : response.getData().getOrderGoodsDtoResultList()) {
				OrderGoodsResult orderGoodsResult = new OrderGoodsResult();
				BeanUtils.copyProperties(goodsDtoResult, orderGoodsResult);
				orderGoodsResults.add(orderGoodsResult);
			}
			result.setOrderGoodsDtoResultList(orderGoodsResults);
		}
		return result;
	}

	/**
	 * @Author: yyq
	 * @Date :Created in 2018/11/23 10:33
	 * @Param :
	 */
	public Result orderClose(OrderCloseReq orderCloseReq) {
				
		Result result = new Result();
		OrderCloseDtoReq dtoReq = new OrderCloseDtoReq();
		dtoReq.setOrderId(orderCloseReq.getOrderId());
		BaseResponse<DtoResult> response = orderFeignClient.closeOrder(dtoReq);
		BeanUtils.copyProperties(response, result);
		return result;
	}

	/**
	 * @Author ：yyq
	 * @Date ：Created in 2018/11/19 0019 17:46
	 * @Param ：
	 */
	public Result modifyOrderFeedTransport(OrderFeedTransportModifyReq requestBody) {
		Result result = new Result();
		OrderFeedTransportModifyDtoReq dtoReq = new OrderFeedTransportModifyDtoReq();
		BeanUtils.copyProperties(requestBody, dtoReq);
		dtoReq.setSource(OrderOperationSourceEnums.MONEYFEED);
		if (requestBody.getId() == null || requestBody.getOrderId() == null) {
			result.setCode(ResultCode.PARAM_ERROR.getCode());
			result.setMsg(ResultCode.PARAM_ERROR.getDesc());
			return result;
		}
		BaseResponse<DtoResult> response = orderTransportFeignClient.updateFeedTransport(dtoReq);
		if (requestBody.getPlanTransportTime() != null) {
			OrderInfoModifyDtoReq infoModifyDtoReq = new OrderInfoModifyDtoReq();
			infoModifyDtoReq.setPlanTransportTime(requestBody.getPlanTransportTime());
			infoModifyDtoReq.setOrderId(requestBody.getOrderId());
			// 限制更新拉料时间状态
			OrderDetailDtoReq orderDetailDtoReq = new OrderDetailDtoReq();
			orderDetailDtoReq.setOrderId(requestBody.getOrderId());
			BaseResponse<OrderDetailDtoResult> orderDetailResponse = orderFeignClient.orderDetail(orderDetailDtoReq);
			if (!orderDetailResponse.isSuccess() || orderDetailResponse.getData().getOrderInfoDtoResult() == null) {
				BeanUtils.copyProperties(orderDetailResponse, result);
				return result;
			}

			String status = orderDetailResponse.getData().getOrderInfoDtoResult().getStatus();

			if (!OrderStatusEnum.CREATING.getValue().equals(status)
					&& !OrderStatusEnum.WAITING_FOR_PAYMENT.getValue().equals(status)
					&& !OrderStatusEnum.PAYING.getValue().equals(status)
					&& !OrderStatusEnum.PAID.getValue().equals(status)
					&& !OrderStatusEnum.MATERIAL_PREPARING.getValue().equals(status)
					&& !OrderStatusEnum.WAITING_PULL_MATERIAL.getValue().equals(status)) {
				BeanUtils.copyProperties(response, result);
				return result;
			}
			BaseResponse<DtoResult> orderResponse = orderFeignClient.updateOrderInfo(infoModifyDtoReq);
			if (!orderResponse.isSuccess()) {
				BeanUtils.copyProperties(orderResponse, result);
				return result;
			}
		}
		BeanUtils.copyProperties(response, result);
		return result;
	}

	public OrderStatusResult orderStatus(Long orderId) {
		OrderStatusResult result = new OrderStatusResult();
		BaseResponse<OrderStatusDtoResult> response = orderFeignClient.orderStatus(orderId);
		if (!response.isSuccess()) {
			BeanUtils.copyProperties(response, result);
			return result;
		}
		BeanUtils.copyProperties(response.getData(), result);
		return result;
	}

	/**
	 * 查询购物车
	 * 
	 * @creator : pudongliang
	 */
	public OrderCartsSkuListResult carts(CartsQueryDtoReq dtoReq) {
		OrderCartsSkuListResult result = new OrderCartsSkuListResult();
		result.setCode(ResultCode.SUCCESS.getCode());
		OrderSysParamQueryDtoReq sysParamDtoReq = new OrderSysParamQueryDtoReq();
		sysParamDtoReq.setType(OrderSysParamsTypeEnum.TRANSPORT_SUGGEST.name());
		sysParamDtoReq.setCode(OrderSysParamsCodeEnum.TRANSPORT_WEIGHT.name());
		BaseResponse<OrderSysParamListDtoResult> paramResponse = orderSysParamFeignClient.getSysParam(sysParamDtoReq);
		OrderSysParamListDtoResult paramDtoResult = paramResponse.getData();
		if (paramResponse.isSuccess() && paramDtoResult != null
				&& CollectionUtils.isNotEmpty(paramDtoResult.getParams())) {
			List<OrderSysParamResult> params = new ArrayList<OrderSysParamResult>();
			for (OrderSysParamDtoResult paramDto : paramDtoResult.getParams()) {
				OrderSysParamResult param = new OrderSysParamResult();
				BeanUtils.copyProperties(paramDto, param);
				params.add(param);
			}
			result.setParams(params);
		}

		BaseResponse<OrderCartsSkuListDtoResult> cartsResponse = orderFeignClient.carts(dtoReq);
		OrderCartsSkuListDtoResult cartDtoResult = cartsResponse.getData();
		if (!cartsResponse.isSuccess() || cartDtoResult == null || CollectionUtils.isEmpty(cartDtoResult.getCarts())) {
			result.setCode(cartsResponse.getCode());
			return result;
		}
		List<OrderCartsSkuDtoResult> cartsDtoResult = cartDtoResult.getCarts();
		List<Long> skuids = new ArrayList<>(cartsDtoResult.size());
		Map<Long, Integer> skucountMap = Maps.newHashMap();
		for (OrderCartsSkuDtoResult cartDto : cartsDtoResult) {
			skuids.add(cartDto.getPcProcuctId());
			skucountMap.put(cartDto.getPcProcuctId(), cartDto.getCount());
		}

		ProductQueryDtoReq productQueryDtoReq = new ProductQueryDtoReq();
		productQueryDtoReq.setProductSkuIds(skuids);
		productQueryDtoReq.setShopId(dtoReq.getUcShopId());
		productQueryDtoReq.setCustomerNum(dtoReq.getCustomerNo());
		productQueryDtoReq.setVerify(Boolean.TRUE);
		BaseResponse<ProductSkuListDtoResult> skuResponse = productFeignClient.getProduct(productQueryDtoReq);
		ProductSkuListDtoResult skuListDtoResult = skuResponse.getData();
		if (!skuResponse.isSuccess() || skuListDtoResult == null
				|| CollectionUtils.isEmpty(skuListDtoResult.getProductSkuList())) {
			result.setCode(ResultCode.OC_CARTS_GOODS_OVERDUE.getCode());
			return result;
		}
		List<ProductSkuDtoResult> skuListDto = skuListDtoResult.getProductSkuList();
		List<OrderCartsSkuResult> cartsResult = new ArrayList<OrderCartsSkuResult>();
		for (ProductSkuDtoResult skuDto : skuListDto) {
			OrderCartsSkuResult cartResult = new OrderCartsSkuResult();
			cartResult.setCount(skucountMap.get(skuDto.getProductSkuId()));
			cartResult.setProductSkuId(skuDto.getProductSkuId());
			cartResult.setPrice(skuDto.getPrice());
			cartResult.setPrimaryUom(skuDto.getPrimaryUom());
			cartResult.setSecondaryUom(skuDto.getSecondaryUom());
			cartResult.setProductName(skuDto.getProductName());
			List<ProductSkuAttributesDtoResult> skuAttrParamsDto = skuDto.getProductSkuAttributesExModels();
			if (CollectionUtils.isNotEmpty(skuAttrParamsDto)) {
				for (ProductSkuAttributesDtoResult skuAttrParamDto : skuAttrParamsDto) {
					if (CommonConstant.WEIGHT_CN.equals(skuAttrParamDto.getName())) {
						cartResult.setWeight(skuAttrParamDto.getParmValue());
					}
				}
			}
			cartsResult.add(cartResult);
		}
		result.setCarts(cartsResult);
		return result;
	}

	public Result updateCarts(CartsUpdateDtoReq dtoReq) {
		Result result = new Result();
		BaseResponse<DtoResult> cartsResponse = orderFeignClient.updateCarts(dtoReq);
		BeanUtils.copyProperties(cartsResponse, result);
		return result;
	}

	public Result modifyOrderByOfflinePayment(String orderNo) {
		Result result = new Result();
		BaseResponse<DtoResult> response = orderFeignClient.modifyOrderByOfflinePayment(orderNo);
		BeanUtils.copyProperties(response, result);
		return result;
	}
	/**
	 * 修改-新建订单
	 * @param reqBody
	 * @return
	 */
	public OrderCreateResult updateNHOrder(OrderUpdateReq reqBody) {
		OrderCreateResult result = new OrderCreateResult();
		/** 登录验证 */
		ClientUserCacheDtoResult userInfo = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
		if (userInfo == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			return result;
		}
		OrderDetailDtoReq detailDtoReq = new OrderDetailDtoReq();
		detailDtoReq.setOrderNo(reqBody.getOrderNo());
		BaseResponse<OrderDetailDtoResult> detailResponse = orderFeignClient.orderDetail(detailDtoReq);
		OrderDetailDtoResult orderDetail = detailResponse.getData();
		BeanUtils.copyProperties(detailResponse, result);
		if (!ResultCode.SUCCESS.getCode().equals(detailResponse.getCode()) || orderDetail == null) {
			return result;
		}
		// 验证商品是否可以修改
		if (!orderEnableModify(orderDetail)) {
			result.setCode(ResultCode.OC_ORDER_STATUS_NONEDITABLE.getCode());
			result.setMsg(ResultCode.OC_ORDER_STATUS_NONEDITABLE.getDesc());
			return result;
		}
		List<OrderGoodsDtoResult> skus = orderDetail.getOrderGoodsDtoResultList();
		if (skus.size() != reqBody.getPitems().size()) {
			OrderNHUpdateDtoReq updateDtoReq = new OrderNHUpdateDtoReq();
			updateDtoReq.setOrderNo(reqBody.getOrderNo());
			updateDtoReq.setCreateBody(buildOrderInfo(buildOrderUserInfo(userInfo), reqBody));
			BaseResponse<OrderCreateDtoResult> createResponse = orderFeignClient.updateNHOrder(updateDtoReq);
			BeanUtils.copyProperties(createResponse, result);
			if (ResultCode.SUCCESS.getCode().equals(createResponse.getCode()) && null != createResponse.getData()) {
				result.setOrderNo(createResponse.getData().getOrderNo());
			}
			return result;
		}
		Map<Long, Integer> skuQuantityMap = Maps.newHashMap();
		for (OrderGoodsDtoResult sku : skus) {
			skuQuantityMap.put(sku.getSkuId(), sku.getQuantity());
		}
		
		for (ProductItemReq item : reqBody.getPitems()) {
			if (skuQuantityMap.get(item.getSkuid()) == null
					|| !skuQuantityMap.get(item.getSkuid()).equals(item.getCount())) {
				OrderNHUpdateDtoReq updateDtoReq = new OrderNHUpdateDtoReq();
				updateDtoReq.setOrderNo(reqBody.getOrderNo());
				updateDtoReq.setCreateBody(buildOrderInfo(buildOrderUserInfo(userInfo), reqBody));
				BaseResponse<OrderCreateDtoResult> createResponse = orderFeignClient.updateNHOrder(updateDtoReq);
				BeanUtils.copyProperties(createResponse, result);
				if (ResultCode.SUCCESS.getCode().equals(createResponse.getCode()) && null != createResponse.getData()) {
					result.setOrderNo(createResponse.getData().getOrderNo());
				}
				return result;
			}
		}

		OrderFeedTransportModifyReq transportUpdateReq = new OrderFeedTransportModifyReq();
		BeanUtils.copyProperties(reqBody, transportUpdateReq);
		transportUpdateReq.setOrderId(orderDetail.getOrderInfoDtoResult().getId());
		transportUpdateReq.setId(orderDetail.getOrderTransportDtoResult().getId());
		transportUpdateReq
				.setPlanTransportTime(DateUtils.str2Date(reqBody.getPlanTransportTime(), DateUtils.date_sdf));
		BeanUtils.copyProperties(modifyOrderFeedTransport(transportUpdateReq), result);
		if(ResultCode.SUCCESS.getCode().equals(result.getCode())){
			result.setOrderNo(orderDetail.getOrderInfoDtoResult().getOrderNo());
		}
		return result;
	}

	/**
	 * 订单状态是否能被修改
	 * 待支付 及 已进场的未支付的非线下订单
	 * @param orderDetail
	 * @return
	 */
	private boolean orderEnableModify(OrderDetailDtoResult orderDetail) {
		OrderDtoResult orderInfo = orderDetail.getOrderInfoDtoResult();
		String status = orderInfo.getStatus();
		return OrderStatusEnum.WAITING_FOR_PAYMENT.getValue().equals(status) ||
				(OrderStatusEnum.ALREADY_ENTRY_FACTORY.getValue().equals(status) &&
						!orderInfo.getPayFlag() &&
						!OrderPayTypeEnum.OFF_LINE.getValue().equals(orderInfo.getPayType()));
	}

	protected  OrderNHCreateDtoReq buildOrderUserInfo(ClientUserCacheDtoResult userInfo) {
		OrderNHCreateDtoReq createDtoReq = new OrderNHCreateDtoReq();
		/** 用户客户信息 */
		CustomerUserDtoReq user = new CustomerUserDtoReq();
		user.setBuyerId(userInfo.getUser().getId());
		user.setBuyerName(userInfo.getUser().getName());
		user.setBuyerMobile(userInfo.getUser().getMobile());
		user.setCustomerId(userInfo.getCustomer().getCustomer().getId());
		user.setCustomerMobile(userInfo.getCustomer().getCustomer().getContactTel());
		user.setCustomerName(userInfo.getCustomer().getCustomer().getName());
		user.setCustomerNum(userInfo.getCustomer().getCustomer().getEbsCustomerNum());
		createDtoReq.setUser(user);

		/** 商户信息 */
		UcShopDtoReq shop = new UcShopDtoReq();
		shop.setUcShopId(userInfo.getVisitShop().getShop().getId());
		shop.setUcShopMobile(userInfo.getVisitShop().getShop().getContactTel());
		shop.setUcShopName(userInfo.getVisitShop().getShop().getName());
		shop.setUcShopAddress(userInfo.getVisitShop().getShop().getAddress());
		shop.setOrgId(userInfo.getVisitShop().getShop().getEbsOrgId());
		UcShopCustomerPropertyModel property = userInfo.getVisitShop().getShopCustomerRules()
				.get(CustomerPropertyTypeEnums.ORDER_CALCULATE_RULE.name());
		shop.setCusOrderType(property == null ? null : property.getValue());
		createDtoReq.setShop(shop);
		createDtoReq.setOrderChannel(OrderChannelEnum.CLIENT.getValue());
		createDtoReq.setProxUserId(userInfo.getUser().getId());
		createDtoReq.setProxName(userInfo.getUser().getName());
		return createDtoReq;
	}

	protected OrderNHCreateDtoReq buildOrderInfo(OrderNHCreateDtoReq createDtoReq,OrderBasePostReq baseReq) {
		FeedTransportDtoReq pullfeedRule = new FeedTransportDtoReq();
		BeanUtils.copyProperties(baseReq, pullfeedRule);
		pullfeedRule.setPlanTransportTime(DateUtils.str2Date(baseReq.getPlanTransportTime(), DateUtils.date_sdf));
		createDtoReq.setPullFeedRule(pullfeedRule);
		/** 用户订单商品信息 */
		int size = baseReq.getPitems().size();
		List<ProductDtoReq> products = new ArrayList<>(size);
		for (ProductItemReq item : baseReq.getPitems()) {
			ProductDtoReq itemDto = new ProductDtoReq();
			BeanUtils.copyProperties(item, itemDto);
			products.add(itemDto);
		}
		createDtoReq.setProducts(products);
		return createDtoReq;
	}
}
