package com.newhope.order.biz.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.newhope.commons.lang.DateUtils;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.dto.uclog.UserOperationLogDtoReq;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.enums.UserOperEventType;
import com.newhope.moneyfeed.api.enums.UserOperSourceType;
import com.newhope.moneyfeed.api.enums.UserOperationEnums;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderPayInBalanceTypeDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.QueryEbsOrderDetailInfoDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.order.QueryEbsOrderDetailInfoDtoResult;
import com.newhope.moneyfeed.order.api.bean.OrderInfoModel;
import com.newhope.moneyfeed.order.api.dto.request.integration.RepositoryLowDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.pay.OrderPayByEBSDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.pay.PayResultDtoReq;
import com.newhope.moneyfeed.order.api.enums.OrderStatusEnum;
import com.newhope.moneyfeed.order.api.enums.PayTypeEnums;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderInfoDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PaySpecialDtoReq;
import com.newhope.moneyfeed.pay.api.dto.response.PayOrderCreateDtoResult;
import com.newhope.moneyfeed.pay.api.enums.PayStatusEnum;
import com.newhope.order.biz.rpc.feign.MoneyfeedToEbsOrderFeignClient;
import com.newhope.order.biz.rpc.feign.UserOperationFeignClient;
import com.newhope.order.biz.rpc.feign.pay.PayOrderInfoFeignClient;
import com.newhope.order.biz.service.OrderChangeLogService;
import com.newhope.order.biz.service.OrderIntegrationService;
import com.newhope.order.biz.service.OrderPayService;
import com.newhope.order.biz.service.OrderService;

@Service("OrderPayService")
public class OrderPayServiceImpl implements OrderPayService {
	private final Logger logger = LoggerFactory.getLogger(OrderPayServiceImpl.class);
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderChangeLogService orderChangeLogService;
	
	@Autowired
	OrderIntegrationService orderIntegrationService;
	
	@Autowired
	PayOrderInfoFeignClient payOrderInfoFeignClient;
	
	@Autowired
	UserOperationFeignClient userOperationFeignClient;
	
	@Autowired
	MoneyfeedToEbsOrderFeignClient moneyfeedToEbsOrderFeignClient;
	
	@Override
	@Transactional
	public DtoResult orderPayByEBS(OrderPayByEBSDtoReq dtoReq) {
		OrderInfoModel queryModel = new OrderInfoModel();
		queryModel.setId(dtoReq.getOrderId());
		List<OrderInfoModel> orderInfoList = orderService.query(queryModel);
		if (CollectionUtils.isEmpty(orderInfoList)) {
			throw new BizException(ResultCode.OC_ORDER_ID_NOT_EXISTS);
		}
		OrderInfoModel orderInfo = orderInfoList.get(0);
		// 待支付和进场两种状态才可以支付
		if (!OrderStatusEnum.WAITING_FOR_PAYMENT.name().equals(orderInfo.getStatus())
				&& !OrderStatusEnum.ALREADY_ENTRY_FACTORY.name().equals(orderInfo.getStatus())) {
			throw new BizException(ResultCode.OC_ORDER_NOT_WAITTING_PAY);
		}
		//更新订单信息 
		OrderInfoModel oldModel = new OrderInfoModel();
		oldModel.setId(orderInfo.getId());
		oldModel.setStatus(orderInfo.getStatus());
		OrderInfoModel newModel = new OrderInfoModel();
		newModel.setStatus(OrderStatusEnum.PAYING.name());
		boolean flag = orderService.update(oldModel, newModel);
		if (flag) {
			Date date = new Date();
			//生成支付信息
			PayOrderInfoDtoReq payOrderInfoDtoReq = new PayOrderInfoDtoReq();
			payOrderInfoDtoReq.setOrderNo(orderInfo.getOrderNo());
			payOrderInfoDtoReq.setUserId(orderInfo.getBuyerId());
			payOrderInfoDtoReq.setShopId(orderInfo.getUcShopId());
			payOrderInfoDtoReq.setTradeTime(date);
			payOrderInfoDtoReq.setAmount(orderInfo.getTotalPayAmount());
			payOrderInfoDtoReq.setRemark("EBS方式支付订单");
			payOrderInfoDtoReq.setStatus(PayStatusEnum.PAY_PROGRESS.name());
			payOrderInfoDtoReq.setPayType(PayTypeEnums.BAL_PAY.name());
			payOrderInfoDtoReq.setCusNo(orderInfo.getCustomerNum());
			payOrderInfoDtoReq.setMerchno(orderInfo.getUcShopId().toString());
			payOrderInfoDtoReq.setPlatformid(orderInfo.getUcShopId().toString());
			payOrderInfoDtoReq.setOrgId(orderInfo.getOrgId());
			BaseResponse<PayOrderCreateDtoResult> createPayOrderResponse = payOrderInfoFeignClient.createPayOrder(payOrderInfoDtoReq);
			if(!ResultCode.SUCCESS.getCode().equals(createPayOrderResponse.getCode())){
				throw new BizException(createPayOrderResponse.getMsg());
			}
			//调用中台支付
			MoneyfeedToEbsOrderPayInBalanceTypeDtoReq orderPayInBalanceTypeDtoReq = new MoneyfeedToEbsOrderPayInBalanceTypeDtoReq();
			orderPayInBalanceTypeDtoReq.setEbsOrderNo(orderInfo.getEbsOrderNo());
			orderPayInBalanceTypeDtoReq.setCusNo(orderInfo.getCustomerNum());
			orderPayInBalanceTypeDtoReq.setTotalPayAmount(orderInfo.getTotalPayAmount());
			//MIN(EBS在客户可用余额信息接口 “保证金”金额,订单金额)
			BigDecimal discountAmount = orderInfo.getTotalPayAmount().compareTo(dtoReq.getDeposit()) > 0 ? dtoReq.getDeposit() : orderInfo.getTotalPayAmount();
			orderPayInBalanceTypeDtoReq.setDiscountAmount(discountAmount);
			orderPayInBalanceTypeDtoReq.setTotalBalanceAmount(orderInfo.getTotalPayAmount());
			orderPayInBalanceTypeDtoReq.setCurrency("CNY");
			orderPayInBalanceTypeDtoReq.setMoneyfeedPayNo(createPayOrderResponse.getData().getPayOrderNo());
			orderPayInBalanceTypeDtoReq.setPayDateString(DateUtils.date2Str(date, DateUtils.date_sdf));
			orderPayInBalanceTypeDtoReq.setOrgId(orderInfo.getOrgId());
			DtoResult result = orderIntegrationService.sendOrderPayInfo(orderPayInBalanceTypeDtoReq);
			if(!ResultCode.SUCCESS.getCode().equals(result.getCode())){
				throw new BizException(result.getMsg());
			}
			
			//记录日志
			UserOperationLogDtoReq userOperationLogDtoReq = new UserOperationLogDtoReq();
			userOperationLogDtoReq.setUserOperEventType(UserOperEventType.ORDER);
			userOperationLogDtoReq.setEventId(orderInfo.getId());
			userOperationLogDtoReq.setEbsOrderId(orderInfo.getEbsOrderNo());
			userOperationLogDtoReq.setOrderId(orderInfo.getId());
			userOperationLogDtoReq.setEventDate(new Date());
			userOperationLogDtoReq.setBeforeEventAmount(orderInfo.getTotalPayAmount());
			userOperationLogDtoReq.setAfterEventAmount(orderInfo.getTotalPayAmount());
			userOperationLogDtoReq.setBeforeEventStatus(orderInfo.getStatus());
			userOperationLogDtoReq.setAfterEventStatus(newModel.getStatus());
			userOperationLogDtoReq.setUserOperationEnums(UserOperationEnums.PA_SHOP_CREATE_PAY_TO_EBS);
			userOperationLogDtoReq.setBeforeEventModel(JSON.toJSONString(orderInfo));
			orderInfoList = orderService.query(queryModel);
			userOperationLogDtoReq.setAfterEventModel(JSON.toJSONString(orderInfoList.get(0)));
			userOperationLogDtoReq.setComment("订单发起EBS支付");
			userOperationLogDtoReq.setSource(UserOperSourceType.ORDER_CENTER);
			BaseResponse<DtoResult> operaResp = userOperationFeignClient.recordingUserOperation(userOperationLogDtoReq);
	        if (!operaResp.isSuccess()) {
	            logger.error(String.format(ResultCode.OC_ORDER_OPERALOG_ERROR.getDesc() + "[error:{%s}" + "data:{%s}]", operaResp.getCode(), userOperationLogDtoReq));
	        }
	        
			return result;
		}else {
			return DtoResult.fail(ResultCode.OC_ORDER_NOT_WAITTING_PAY);
		}
		//return DtoResult.success();
	}

	@Override
	public DtoResult paySpecial(PaySpecialDtoReq paySpecialDtoReq) {
		DtoResult dtoResult = new DtoResult();
		
		if(!paySpecialDtoReq.getType().equals("7")){
			if (StringUtils.isEmpty(paySpecialDtoReq.getPayNo())) {
				dtoResult.setCode(ResultCode.PAY_ORDER_NOT_EXIST.getCode());
				dtoResult.setMsg(ResultCode.PAY_ORDER_NOT_EXIST.getDesc());
				return dtoResult;
			}
		}
		if (StringUtils.isEmpty(paySpecialDtoReq.getOrderNo())) {
			dtoResult.setCode(ResultCode.OC_ORDER_ID_NOT_EXISTS.getCode());
			dtoResult.setMsg(ResultCode.OC_ORDER_ID_NOT_EXISTS.getDesc());
			return dtoResult;
		}
		
		//查询商城订单
		OrderInfoModel orderInfo = null;
		OrderInfoModel queryModel = new OrderInfoModel();
		queryModel.setOrderNo(paySpecialDtoReq.getOrderNo());
		List<OrderInfoModel> orderInfoList = orderService.query(queryModel);
		if (CollectionUtils.isEmpty(orderInfoList)) {
			logger.error("[OrderPayServiceImpl.paySpecial]商城订单不存在" + JSON.toJSONString(paySpecialDtoReq));
			dtoResult.setCode(ResultCode.OC_ORDER_NOT_EXIST.getCode());
			dtoResult.setMsg(ResultCode.OC_ORDER_NOT_EXIST.getDesc());
			return dtoResult;
		}
		orderInfo = orderInfoList.get(0);
		
		//ebs成功了，但是没改商城的同步状态
		if(paySpecialDtoReq.getType().equals("3")
				|| paySpecialDtoReq.getType().equals("4")){
			PayResultDtoReq payResultDtoReq = new PayResultDtoReq();
			payResultDtoReq.setEbsorderNo(orderInfo.getEbsOrderNo());
			if(paySpecialDtoReq.getType().equals("3")){
				payResultDtoReq.setPayFlag(true);
				payResultDtoReq.setMsg("手工置为成功");
			}else if(paySpecialDtoReq.getType().equals("4")){
				payResultDtoReq.setPayFlag(false);
				payResultDtoReq.setMsg("手工置为失败");
			}
			payResultDtoReq.setPayType(PayTypeEnums.BAL_PAY.name());
			payResultDtoReq.setOrgId(orderInfo.getOrgId());
			payResultDtoReq.setMoneyfeedPayNo(orderInfo.getId().toString());
			orderIntegrationService.receiveOrderPayInfo(payResultDtoReq);
		}else if(paySpecialDtoReq.getType().equals("5")
				|| paySpecialDtoReq.getType().equals("6")){
			RepositoryLowDtoReq repositoryLowDtoReq = new RepositoryLowDtoReq();
			repositoryLowDtoReq.setMoneyfeedOrderId(orderInfo.getId());
			if(paySpecialDtoReq.getType().equals("5")){
				repositoryLowDtoReq.setRepositoryLowFlag(false);
			}else if(paySpecialDtoReq.getType().equals("6")){
				repositoryLowDtoReq.setRepositoryLowFlag(true);
			}
			orderIntegrationService.reciveRepositoryLow(repositoryLowDtoReq);
		}else if(paySpecialDtoReq.getType().equals("7")){
			//组装订单号传递给中台
			List<String> moneyfeedOrderIds = new ArrayList<String>();
			moneyfeedOrderIds.add(orderInfo.getId().toString());
			
			//从中台查询数据同步本地订单
			QueryEbsOrderDetailInfoDtoReq search = new QueryEbsOrderDetailInfoDtoReq();
			search.setMoneyfeedOrderIds(moneyfeedOrderIds);
			BaseResponse<QueryEbsOrderDetailInfoDtoResult> feignResult = moneyfeedToEbsOrderFeignClient.moneyfeedQueryEbsOrder(search);
			dtoResult.setData(feignResult);
		}
		dtoResult.setCode(ResultCode.SUCCESS.getCode());
		return dtoResult;
	}
}
