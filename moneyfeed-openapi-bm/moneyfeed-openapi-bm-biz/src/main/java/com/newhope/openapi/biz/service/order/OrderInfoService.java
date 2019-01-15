package com.newhope.openapi.biz.service.order;

import com.google.common.collect.Maps;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.common.constant.StatisticsConstant;
import com.newhope.moneyfeed.common.helper.DateUtils;
import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.common.util.ExportUtil;
import com.newhope.moneyfeed.order.api.dto.request.feedtransport.OrderFeedTransportModifyDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderDetailDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoModifyDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.nh.OrderNHCreateDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.nh.rule.FeedTransportDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.product.ProductDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.shop.UcShopDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.user.CustomerUserDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.*;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderCreateDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderGoodsDetailDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderGoodsDetailListDtoResult;
import com.newhope.moneyfeed.order.api.enums.OrderChannelEnum;
import com.newhope.moneyfeed.order.api.enums.OrderOperationSourceEnums;
import com.newhope.moneyfeed.order.api.enums.OrderStatusEnum;
import com.newhope.moneyfeed.order.api.enums.PayLimitTypeEnums;
import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerPropertyModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.constant.CommonConstant;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopCustomerPropertyQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopCustomerPropertyListResult;
import com.newhope.moneyfeed.user.api.enums.client.CustomerPropertyTypeEnums;
import com.newhope.openapi.api.vo.request.order.*;
import com.newhope.openapi.api.vo.response.order.*;
import com.newhope.openapi.biz.rpc.feign.order.OrderFeignClient;
import com.newhope.openapi.biz.rpc.feign.order.OrderTransportFeignClient;
import com.newhope.openapi.biz.rpc.feign.shop.ShopFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.ClientUserFeginClient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class OrderInfoService {
	private Logger logger = Logger.getLogger(OrderInfoService.class);
	@Autowired
	OrderFeignClient orderFeignClient;
	@Autowired
	OrderTransportFeignClient orderTransportFeignClient;

	@Autowired
	RSessionCache rSessionCache;

	@Autowired
	ClientUserFeginClient clientUserFeginClient;

	@Autowired
	ShopFeignClient shopFeignClient;
	/**
	 *  查询订单列表
	 *
	 * @author: dongql
	 * @date: 2018/11/19 15:47
	 * @param req
	 * @return: com.newhope.openapi.api.vo.response.order.info.OrderInfoListResult
	 */
	public OrderInfoListResult queryOrderInfoList(OrderInfoQueryReq req){
		OrderInfoListResult result = new OrderInfoListResult();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		OrderInfoQueryDtoReq dtoReq = wrapQueryDtoReq(req);
		dtoReq.setOrderBy("gmt_created desc");
		if (req.getUcShopId() == null) {
			Object obj = rSessionCache.getUserInfo();
			if(obj == null ){
                result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
                result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
                return result;
            }
			dtoReq.setUcShopId(((UcBmUserDtoResult) obj).getShopId());
		}
		BaseResponse<OrderListDtoResult> response = orderFeignClient.queryOrderInfoList(dtoReq);
		if (ResultCode.SUCCESS.getCode().equals(response.getCode()) && null != response.getData()) {
			result.setPage(response.getData().getPage());
			result.setTotalCount(response.getData().getTotalCount());
			result.setTotalPage(response.getData().getTotalPage());
			ArrayList<OrderInfoResult> orderInfoResults = new ArrayList<>();
			List<OrderDtoResult> orderList = response.getData().getOrderList();
			if (CollectionUtils.isNotEmpty(orderList)) {
				for (OrderDtoResult dtoResult : orderList) {
					OrderInfoResult orderInfoResult = new OrderInfoResult();
					BeanUtils.copyProperties(dtoResult, orderInfoResult);
                    orderInfoResults.add(orderInfoResult);
				}
				result.setOrderList(orderInfoResults);
			}
		}
		return result;
	}

	/**    
	 *  包装查询参数
	 *
	 * @author: dongql  
	 * @date: 2018/12/6 10:04  
	 * @param req  
	 * @return: com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoQueryDtoReq  
	 */  
	private OrderInfoQueryDtoReq wrapQueryDtoReq(OrderInfoQueryReq req) {
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
		if (StringUtils.isNotEmpty(req.getPlanTransportBeginDate())) {
			req.setPlanTransportBeginDate(req.getPlanTransportBeginDate() + DateUtils.BEGIN_TIME_SUFFIX);
		}
		if (StringUtils.isNotEmpty(req.getPlanTransportEndDate())) {
			req.setPlanTransportEndDate(req.getPlanTransportEndDate() + DateUtils.END_TIME_SUFFIX);
		}
		OrderInfoQueryDtoReq dtoReq = new OrderInfoQueryDtoReq();
		BeanUtils.copyProperties(req, dtoReq);
		if (StringUtils.isNotEmpty(req.getStatus())) {
			dtoReq.setStatusList(Arrays.asList(req.getStatus().split(StatisticsConstant.CSV_COLUMN_SEPARATOR)));
		}
		return dtoReq;
	}

	/**    
	 * 导出订单列表
	 *
	 * @author: dongql  
	 * @date: 2018/11/23 17:26
	 * @param req
	 * @param response  
	 * @return: void  
	 */  
	public void exportOrderInfoList(OrderInfoQueryReq req, HttpServletResponse response){
		req.setPage(null);
		req.setPageSize(null);
		OrderInfoListResult result = queryOrderInfoList(req);
		if (ResultCode.SUCCESS.getCode().equals(result.getCode()) && result.getOrderList() != null) {{
			List<OrderInfoResult> orderList = result.getOrderList();
			List<Map<String, Object>> content = new ArrayList<>();
			Map<String, Object> map;
			for (OrderInfoResult data : orderList) {
				map = new HashMap<>();
				map.put("orderNo",data.getOrderNo() == null ? CommonConstant.BLANK_STRING :  "\t" + data.getOrderNo() );
				map.put("ebsorderNo",data.getEbsorderNo() == null ? CommonConstant.BLANK_STRING : "\t" + data.getEbsorderNo());
				map.put("customerName",data.getCustomerName() == null ? CommonConstant.BLANK_STRING : "\"" + data.getCustomerName() + "\"");
				map.put("customerId",data.getCustomerId() == null ? CommonConstant.BLANK_STRING : "\"" + data.getCustomerId() + "\"");
				map.put("totalFeedWeight",data.getTotalFeedWeight() == null ? CommonConstant.BLANK_STRING : "\"" + data.getTotalFeedWeight().divide(new BigDecimal(1000),3,BigDecimal.ROUND_HALF_UP) + "\"");
				map.put("totalOrginalAmount",data.getTotalOrginalAmount() == null ? CommonConstant.BLANK_STRING : "\"" + data.getTotalOrginalAmount() + "\"");
				map.put("totalPayAmount",data.getTotalPayAmount() == null ? CommonConstant.BLANK_STRING : "\"" + data.getTotalPayAmount() + "\"");
				map.put("moneyNo",data.getMoneyNo() == null ? CommonConstant.BLANK_STRING : "\t" + data.getMoneyNo());
				map.put("bankOrderNo",data.getBankOrderNo() == null ? CommonConstant.BLANK_STRING : "\t" + data.getBankOrderNo());
				map.put("gmtCreated",data.getGmtCreated() == null ? CommonConstant.BLANK_STRING : "\"" + DateUtil.getDateStr(data.getGmtCreated(), DateUtil.YYYY_MM_DD_HH_MM_SS) + "\"");
				map.put("endTime",data.getEndTime() == null ? CommonConstant.BLANK_STRING : "\"" + DateUtil.getDateStr(data.getEndTime(), DateUtil.YYYY_MM_DD_HH_MM_SS) + "\"");
				map.put("transportTime",data.getTransportTime() == null ? CommonConstant.BLANK_STRING : "\"" + DateUtil.getDateStr(data.getTransportTime(), DateUtil.YYYY_MM_DD_HH_MM_SS) + "\"");
				map.put("checkinTime",data.getCheckinTime() == null ? CommonConstant.BLANK_STRING : "\"" + DateUtil.getDateStr(data.getCheckinTime(), DateUtil.YYYY_MM_DD_HH_MM_SS) + "\"");
				map.put("dealTime",data.getDealTime() == null ? CommonConstant.BLANK_STRING : "\"" + DateUtil.getDateStr(data.getDealTime(), DateUtil.YYYY_MM_DD_HH_MM_SS) + "\"");
				map.put("status",data.getStatus() == null ? CommonConstant.BLANK_STRING : "\"" + OrderStatusEnum.getEnumsMap().get(data.getStatus()) + "\"");
				map.put("ebsRefundOrderNo",data.getEbsRefundOrderNo() == null ? CommonConstant.BLANK_STRING : "\t" + data.getEbsRefundOrderNo());
				map.put("bankRefundOrderNo",data.getBankRefundOrderNo() == null ? CommonConstant.BLANK_STRING : "\t" + data.getBankRefundOrderNo());
				map.put("planTransportTime",data.getPlanTransportTime() == null ? CommonConstant.BLANK_STRING : "\"" + DateUtil.getDateStr(data.getPlanTransportTime(), DateUtil.YYYY_MM_DD_HH_MM_SS) + "\"");
				map.put("carNo",data.getCarNo() == null ? CommonConstant.BLANK_STRING : "\"" + data.getCarNo() + "\"");
				map.put("driverName",data.getDriverName() == null ? CommonConstant.BLANK_STRING : "\"" + data.getDriverName() + "\"");
				map.put("driverMobile",data.getDriverMobile() == null ? CommonConstant.BLANK_STRING : "\t" + data.getDriverMobile());
				map.put("orderChannel",data.getOrderChannel() == null ? CommonConstant.BLANK_STRING : "\"" + OrderChannelEnum.getEnumsMap().get(data.getOrderChannel()) + "\"");
				map.put("proxName",data.getProxName() == null ? CommonConstant.BLANK_STRING : "\"" + data.getProxName() + "\"");
				content.add(map);
			}
			// map映射key
			String mapKey = "orderNo,ebsorderNo,customerName,customerId,totalFeedWeight,totalOrginalAmount,totalPayAmount," +
					"moneyNo,bankOrderNo,gmtCreated,endTime,transportTime,checkinTime,dealTime,status,ebsRefundOrderNo,bankRefundOrderNo,planTransportTime,carNo,driverName,driverMobile,orderChannel,proxName";

			try {
				final OutputStream os = response.getOutputStream();
				ExportUtil.responseSetProperties(StatisticsConstant.BM_EXPORT_ORDER, response);
				ExportUtil.doExport(content, StatisticsConstant.COLUMNS_BM_EXPORT_ORDER,
                        StatisticsConstant.CSV_COLUMN_SEPARATOR, StatisticsConstant.CSV_RN, mapKey, os);
			} catch (Exception e) {
				logger.error("[OrderInfoService.exportOrderInfoList]:下载文件异常", e);
			}
		}}

	}

	/**
	 * @Author     ：yyq
	 * @Date       ：Created in  2018/11/19 0019 17:46
	 * @Param      ：
	 */
	@Transactional
	public Result modifyOrderFeedTransport(OrderFeedTransportModifyReq requestBody) {
		Result result = new Result();
		OrderFeedTransportModifyDtoReq dtoReq = new OrderFeedTransportModifyDtoReq();
		BeanUtils.copyProperties(requestBody,dtoReq);
		dtoReq.setSource(OrderOperationSourceEnums.MONEYFEED);
		if(requestBody.getId()==null || requestBody.getOrderId()==null){
			result.setCode(ResultCode.PARAM_ERROR.getCode());
			result.setMsg(ResultCode.PARAM_ERROR.getDesc());
			return result;
		}
		BaseResponse<DtoResult> response = orderTransportFeignClient.updateFeedTransport(dtoReq);
		if(requestBody.getPlanTransportTime()!=null){
			OrderInfoModifyDtoReq infoModifyDtoReq = new OrderInfoModifyDtoReq();
			infoModifyDtoReq.setPlanTransportTime(requestBody.getPlanTransportTime());
			infoModifyDtoReq.setOrderId(requestBody.getOrderId());
			BaseResponse<DtoResult> orderResponse = orderFeignClient.updateOrderInfo(infoModifyDtoReq);
			if(!orderResponse.isSuccess()){
				BeanUtils.copyProperties(orderResponse, result);
				return result;
			}
		}
		BeanUtils.copyProperties(response, result);
		return result;
	}
	/**
	 * @Author     ：yyq
	 * @Date       ：Created in  2018/11/19 0019 18:26
	 * @Param      ：
	 */
	public OrderDetailResult queryOrderDetail(Long orderId,String orderNo) {
		OrderDetailResult result = new OrderDetailResult();
		OrderDetailDtoReq dtoReq = new OrderDetailDtoReq();
		dtoReq.setOrderId(orderId);
		dtoReq.setOrderNo(orderNo);
		BaseResponse<OrderDetailDtoResult> response = orderFeignClient.orderDetail(dtoReq);
		BeanUtils.copyProperties(response, result);
		if (!ResultCode.SUCCESS.getCode().equals(response.getCode()) || null == response.getData()) {
			return result;
		}
		if(response.getData().getOrderInfoDtoResult()!=null){
			OrderDetailInfoResult infoResult = new OrderDetailInfoResult();
			BeanUtils.copyProperties(response.getData().getOrderInfoDtoResult(), infoResult);
			//根据订单支付限制时间类型计算支付倒计时时间
			if (StringUtils.isNotEmpty(response.getData().getOrderInfoDtoResult().getLimitTimeType())) {
				if (response.getData().getOrderInfoDtoResult().getPayLimitTime() != null
						&& PayLimitTypeEnums.LIMIT_TIME_PRICE.getValue().equals(response.getData().getOrderInfoDtoResult().getLimitTimeType())) {
					infoResult.setPayLimitTime(response.getData().getOrderInfoDtoResult().getPayLimitTime().getTime() - System.currentTimeMillis());
				}
			}
			result.setOrderInfoDtoResult(infoResult);
		}

		if(response.getData().getOrderTransportDtoResult()!=null){
			OrderTransportResult transportResult = new OrderTransportResult();
			BeanUtils.copyProperties(response.getData().getOrderTransportDtoResult(), transportResult);
			result.setOrderTransportDtoResult(transportResult);
		}
		if(CollectionUtils.isNotEmpty(response.getData().getOrderPresentDtoResultList())){
			List<OrderPresentResult> orderPresentDtoResultList = new ArrayList<>();
			for(OrderPresentDtoResult presentDtoResult:response.getData().getOrderPresentDtoResultList()){
				OrderPresentResult orderPresentResult = new OrderPresentResult();
				BeanUtils.copyProperties(presentDtoResult,orderPresentResult);
				orderPresentDtoResultList.add(orderPresentResult);
			}
			result.setOrderPresentDtoResultList(orderPresentDtoResultList);
		}
		if(CollectionUtils.isNotEmpty(response.getData().getOrderGoodsDtoResultList())){
			List<OrderGoodsResult> orderGoodsResults = new ArrayList<>();
			for(OrderGoodsDtoResult goodsDtoResult:response.getData().getOrderGoodsDtoResultList()){
				OrderGoodsResult orderGoodsResult = new OrderGoodsResult();
				BeanUtils.copyProperties(goodsDtoResult,orderGoodsResult);
				orderGoodsResults.add(orderGoodsResult);
			}
			result.setOrderGoodsDtoResultList(orderGoodsResults);
		}

		return result;
	}

	/**
	 * @Author: yyq
	 * @Date  :Created in  2018/11/20 14:35
	 * @Param :
	 */
	public Result orderClose(OrderCloseReq orderCloseReq) {
		Result result = new Result();
		OrderInfoModifyDtoReq dtoReq = new OrderInfoModifyDtoReq();
		BeanUtils.copyProperties(orderCloseReq,dtoReq);
		dtoReq.setStatus(OrderStatusEnum.SHOP_REPEAL.getValue());
		BaseResponse<DtoResult> response = orderFeignClient.updateOrderInfo(dtoReq);
		BeanUtils.copyProperties(response, result);
		return result;
	}

	/**    
	 *  代用户下单
	 *
	 * @author: dongql  
	 * @date: 2018/11/30 20:20  
	 * @param reqBody  
	 * @return: com.newhope.openapi.api.vo.response.order.OrderProxyCreateResult  
	 */  
    public OrderProxyCreateResult proxyUserCreateOrder(OrderProxyCreateReq reqBody) {
		OrderProxyCreateResult result = new OrderProxyCreateResult();
		/**登录验证*/
		UcBmUserDtoResult userInfo = (UcBmUserDtoResult) rSessionCache.getUserInfo();
		if( userInfo == null ){
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return result;
		}
        OrderNHCreateDtoReq dtoReq = new OrderNHCreateDtoReq();
        dtoReq.setProxUserId(userInfo.getId());
        dtoReq.setProxName(userInfo.getName());
		dtoReq.setOrderChannel(OrderChannelEnum.BM.getValue());
		dtoReq.setProxy(Boolean.TRUE);
		/**用户拉料信息*/
        FeedTransportDtoReq pullfeedRule = new FeedTransportDtoReq();
        BeanUtils.copyProperties(reqBody.getFeedTransportInfo(), pullfeedRule);
		dtoReq.setPullFeedRule(pullfeedRule);
		BaseResponse<ClientUserCacheDtoResult> userCacheResponse = clientUserFeginClient.assemblyUserCache(reqBody.getUserId());
		if (ResultCode.SUCCESS.getCode().equals(userCacheResponse.getCode()) && null != userCacheResponse.getData()) {
			ClientUserCacheDtoResult buyerInfo = userCacheResponse.getData();
			CustomerUserDtoReq user = new CustomerUserDtoReq();
			user.setBuyerId(buyerInfo.getUser().getId());
			user.setBuyerName(buyerInfo.getUser().getName());
			user.setBuyerMobile(buyerInfo.getUser().getMobile());
			user.setCustomerId(buyerInfo.getCustomer().getCustomer().getId());
			user.setCustomerMobile(buyerInfo.getCustomer().getCustomer().getContactTel());
			user.setCustomerName(buyerInfo.getCustomer().getCustomer().getName());
			user.setCustomerNum(buyerInfo.getCustomer().getCustomer().getEbsCustomerNum());
			dtoReq.setUser(user);

			List<UcShopModel> shopList = buyerInfo.getCustomer().getShopList();
			if(CollectionUtils.isNotEmpty(shopList)) {
				for (UcShopModel shopModel : shopList) {
					if (shopModel.getId().equals(reqBody.getShopId())) {
						/**商户信息*/
						UcShopDtoReq shop = new UcShopDtoReq();
						shop.setUcShopId(shopModel.getId());
						shop.setUcShopMobile(shopModel.getContactTel());
						shop.setUcShopName(shopModel.getName());
						shop.setUcShopAddress(shopModel.getAddress());
						shop.setOrgId(shopModel.getEbsOrgId());
						ShopCustomerPropertyQueryDtoReq propertyParam = new ShopCustomerPropertyQueryDtoReq();
						propertyParam.setCustomerId(buyerInfo.getCustomer().getCustomer().getId());
						propertyParam.setShopId(shopModel.getId());
						BaseResponse<ShopCustomerPropertyListResult> propertyResult =  shopFeignClient.queryShopCustomerProperty(propertyParam);
						Map<String,UcShopCustomerPropertyModel> propertysMap = Maps.newHashMap();
						if(propertyResult.isSuccess()){
							if( CollectionUtils.isNotEmpty( propertyResult.getData().getPropertys()) ){
								for( UcShopCustomerPropertyModel property : propertyResult.getData().getPropertys() ){
									propertysMap.put(property.getName(), property);
								}
							}
						}
						UcShopCustomerPropertyModel property = propertysMap.get(CustomerPropertyTypeEnums.ORDER_CALCULATE_RULE.name());
						shop.setCusOrderType(property == null ? null : property.getValue());
						dtoReq.setShop(shop);
					}
				}
			}
		} else {
			result.setCode(userCacheResponse.getCode());
			result.setMsg(userCacheResponse.getMsg());
			return result ;
		}
		if (dtoReq.getShop() == null){
			result.setCode(ResultCode.USER_CUSTOMER_OR_SHOP_NOT_EXIST.getCode());
			result.setMsg(ResultCode.USER_CUSTOMER_OR_SHOP_NOT_EXIST.getDesc());
			return result ;
		}
        /**用户订单商品信息*/
        int size = reqBody.getPitems().size();
        List<ProductDtoReq> products = new ArrayList<>(size);
        for (ProductItemReq item : reqBody.getPitems()) {
            ProductDtoReq itemDto = new ProductDtoReq();
            BeanUtils.copyProperties(item, itemDto);
            products.add(itemDto);
        }
        dtoReq.setProducts(products);

        BaseResponse<OrderCreateDtoResult> response = orderFeignClient.createNHOrder(dtoReq);
        BeanUtils.copyProperties(response, result);
        if (ResultCode.SUCCESS.getCode().equals(response.getCode()) && null != response.getData()) {
            result.setOrderNo(response.getData().getOrderNo());
        }
        return result;
    }

    public void exportOrderGoodsDetailList(OrderInfoQueryReq req, HttpServletResponse response){
        req.setPage(null);
        req.setPageSize(null);
		OrderInfoQueryDtoReq dtoReq = wrapQueryDtoReq(req);
        if (req.getUcShopId() == null) {
            Object obj = rSessionCache.getUserInfo();
            if(obj == null ){
                return;
            }
            dtoReq.setUcShopId(((UcBmUserDtoResult) obj).getShopId());
        }
        BaseResponse<OrderGoodsDetailListDtoResult> result = orderFeignClient.queryOrderGoodsDetailList(dtoReq);
        if (ResultCode.SUCCESS.getCode().equals(result.getCode()) && result.getData() != null) {{
            List<OrderGoodsDetailDtoResult> orderList = result.getData().getGoodsDetailList();
            List<Map<String, Object>> content = new ArrayList<>();
            Map<String, Object> map;
            if (orderList != null) {
                for (OrderGoodsDetailDtoResult data : orderList) {
                    map = new HashMap<>();
                    map.put("orderNo",data.getOrderNo() == null ? CommonConstant.BLANK_STRING :  "\t" + data.getOrderNo() );
                    map.put("ebsorderNo",data.getEbsorderNo() == null ? CommonConstant.BLANK_STRING : "\t" + data.getEbsorderNo());
                    map.put("customerName",data.getCustomerName() == null ? CommonConstant.BLANK_STRING : "\"" + data.getCustomerName() + "\"");
                    map.put("customerId",data.getCustomerId() == null ? CommonConstant.BLANK_STRING : "\"" + data.getCustomerId() + "\"");
                    map.put("productCode",data.getProductCode() == null ? CommonConstant.BLANK_STRING : "\"" + data.getProductCode() + "\"");
                    map.put("productName",data.getProductName() == null ? CommonConstant.BLANK_STRING : "\"" + data.getProductName() + "\"");
                    map.put("productCount",data.getProductCount() == null ? CommonConstant.BLANK_STRING : "\"" + data.getProductCount() + "\"");
                    map.put("totalFeedWeight",data.getTotalFeedWeight() == null ? CommonConstant.BLANK_STRING : "\"" + data.getTotalFeedWeight().divide(new BigDecimal(1000), 3, RoundingMode.FLOOR) + "\"");
                    map.put("gmtCreated",data.getGmtCreated() == null ? CommonConstant.BLANK_STRING : "\"" + DateUtil.getDateStr(data.getGmtCreated(), DateUtil.YYYY_MM_DD_HH_MM_SS) + "\"");
                    map.put("endTime",data.getEndTime() == null ? CommonConstant.BLANK_STRING : "\"" + DateUtil.getDateStr(data.getEndTime(), DateUtil.YYYY_MM_DD_HH_MM_SS) + "\"");
                    map.put("status",data.getStatus() == null ? CommonConstant.BLANK_STRING : "\"" + OrderStatusEnum.getEnumsMap().get(data.getStatus()) + "\"");
                    map.put("ebsRefundOrderNo",data.getEbsRefundOrderNo() == null ? CommonConstant.BLANK_STRING : "\t" + data.getEbsRefundOrderNo());
                    map.put("planTransportTime",data.getPlanTransportTime() == null ? CommonConstant.BLANK_STRING : "\"" + DateUtil.getDateStr(data.getPlanTransportTime(), DateUtil.YYYY_MM_DD_HH_MM_SS) + "\"");
                    map.put("carNo",data.getCarNo() == null ? CommonConstant.BLANK_STRING : "\"" + data.getCarNo() + "\"");
                    map.put("orderChannel",data.getOrderChannel() == null ? CommonConstant.BLANK_STRING : "\"" + OrderChannelEnum.getEnumsMap().get(data.getOrderChannel()) + "\"");
                    map.put("proxName",data.getProxName() == null ? CommonConstant.BLANK_STRING : "\"" + data.getProxName() + "\"");
                    content.add(map);
                }
                // map映射key
                String mapKey = "orderNo,ebsorderNo,customerName,customerId,productCode,productName,productCount,totalFeedWeight," +
                        "gmtCreated,endTime,status,ebsRefundOrderNo,planTransportTime,carNo,orderChannel,proxName";

                try {
                    final OutputStream os = response.getOutputStream();
                    ExportUtil.responseSetProperties(StatisticsConstant.BM_EXPORT_ORDER_GOODS_DETAIL, response);
                    ExportUtil.doExport(content, StatisticsConstant.COLUMNS_BM_EXPORT_ORDER_GOODS_DETAIL,
                            StatisticsConstant.CSV_COLUMN_SEPARATOR, StatisticsConstant.CSV_RN, mapKey, os);
                } catch (Exception e) {
                    logger.error("[CustomerService.exportIntentionCustomer]:下载文件异常", e);
                }
            }
        }}
    }
}
