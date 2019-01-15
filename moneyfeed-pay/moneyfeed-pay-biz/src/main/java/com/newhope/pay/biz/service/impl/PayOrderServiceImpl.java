package com.newhope.pay.biz.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.api.dto.request.sms.SmsSendDtoReq;
import com.newhope.moneyfeed.api.dto.request.wechat.WechatMsgDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.dto.uclog.UserOperationLogDtoReq;
import com.newhope.moneyfeed.api.enums.AppSourceEnums;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.enums.SmsTemplateEnums;
import com.newhope.moneyfeed.api.enums.UserOperEventType;
import com.newhope.moneyfeed.api.enums.UserOperSourceType;
import com.newhope.moneyfeed.api.enums.UserOperationEnums;
import com.newhope.moneyfeed.api.enums.wechat.WechatMsgTypeEnums;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.helper.DateUtils;
import com.newhope.moneyfeed.order.api.bean.OrderFeedTransportModel;
import com.newhope.moneyfeed.order.api.bean.OrderPresentFeedModel;
import com.newhope.moneyfeed.order.api.dto.request.feedtransport.OrderFeedTransportQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoModifyDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.present.OrderPresentFeedQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.OrderDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.OrderListDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.OrderTransportListDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderValidateRepositoryDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.PayOrderNoDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.present.OrderPresentFeedListDtoResult;
import com.newhope.moneyfeed.order.api.enums.OrderPayTypeEnum;
import com.newhope.moneyfeed.order.api.enums.OrderStatusEnum;
import com.newhope.moneyfeed.order.api.enums.PayTypeEnums;
import com.newhope.moneyfeed.pay.api.bean.PayOrderInfoModel;
import com.newhope.moneyfeed.pay.api.bean.PayOrderLogModel;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderCallbackDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderInfoDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderInfoQueryDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.PaySpecialDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.integration.NotifyPaymentDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.integration.NotifyRechargeDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.integration.ReciveNotifyEbsPayDtoReq;
import com.newhope.moneyfeed.pay.api.dto.response.PayCallbackDtoResult;
import com.newhope.moneyfeed.pay.api.dto.response.PayOrderCreateDtoResult;
import com.newhope.moneyfeed.pay.api.dto.response.PayOrderDtoResult;
import com.newhope.moneyfeed.pay.api.dto.response.PayOrderListDtoResult;
import com.newhope.moneyfeed.pay.api.enums.CheckPayStatusEnum;
import com.newhope.moneyfeed.pay.api.enums.EbsPayStatusEnum;
import com.newhope.moneyfeed.pay.api.enums.PayStatusEnum;
import com.newhope.moneyfeed.pay.dal.BaseDao;
import com.newhope.moneyfeed.pay.dal.dao.PayOrderInfoDao;
import com.newhope.moneyfeed.pay.dal.dao.PayOrderLogDao;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerAccountStatementPostDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerAccountStatementPostListDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserThirdAccountDtoResult;
import com.newhope.pay.biz.rpc.feign.ClientUserFeignClient;
import com.newhope.pay.biz.rpc.feign.CustomerAccountFeignClient;
import com.newhope.pay.biz.rpc.feign.OrderFeignClient;
import com.newhope.pay.biz.rpc.feign.OrderIntegrationFeignClient;
import com.newhope.pay.biz.rpc.feign.OrderPresentFeedFeignClient;
import com.newhope.pay.biz.rpc.feign.OrderTransportFeignClient;
import com.newhope.pay.biz.rpc.feign.SmsFeignClient;
import com.newhope.pay.biz.rpc.feign.UserOperationFeignClient;
import com.newhope.pay.biz.rpc.feign.WechatMsgFeignClient;
import com.newhope.pay.biz.service.PayIntegrationService;
import com.newhope.pay.biz.service.PayOrderService;

@Service("PayOrderService")
public class PayOrderServiceImpl extends BaseServiceImpl<PayOrderInfoModel> implements PayOrderService {

	private final Logger logger = LoggerFactory.getLogger(PayOrderServiceImpl.class);

	@Value("${newhope.wechat.hompageurl}")
	private String hompageurl;
	
	@Autowired
	PayOrderInfoDao payOrderInfoDao;

	@Autowired
	PayOrderLogDao payOrderLogDao;

	@Autowired
	SmsFeignClient smsFeignClient;

	@Autowired
	WechatMsgFeignClient wechatMsgFeignClient;

	@Autowired
	ClientUserFeignClient clientUserFeignClient;

	@Autowired
	OrderFeignClient orderFeignClient;

	@Autowired
	CustomerAccountFeignClient customerAccountFeignClient;

	@Autowired
	UserOperationFeignClient userOperationFeignClient;

	@Autowired
	PayIntegrationService payIntegrationService;
	
	@Autowired
	OrderIntegrationFeignClient orderIntegrationFeignClient;
	
	@Autowired
	OrderTransportFeignClient orderTransportFeignClient;
	
	@Autowired
	OrderPresentFeedFeignClient orderPresentFeedFeignClient;

	@Override
	protected BaseDao<PayOrderInfoModel> getDao() {
		return payOrderInfoDao;
	}

	@Override
	@Transactional
	public PayOrderCreateDtoResult createPayOrder(PayOrderInfoDtoReq payOrderInfoDtoReq) {
		/*
		 * 判断商城订单状态为待支付才能创建支付订单
		 * 生成支付订单
		 * 记录支付订单日志
		 * 记录UCLOG日志
		 * */
		PayOrderCreateDtoResult dtoResult = new PayOrderCreateDtoResult();
		if (StringUtils.isEmpty(payOrderInfoDtoReq.getOrderNo())) {
			logger.error("[PayOrderServiceImpl.createPayOrder]商城订单号没传入" + JSON.toJSONString(payOrderInfoDtoReq));
			dtoResult.setCode(ResultCode.OC_ORDER_NOT_EXIST.getCode());
			dtoResult.setMsg(ResultCode.OC_ORDER_NOT_EXIST.getDesc());
			return dtoResult;
		}
		
		//查询商城订单
		OrderInfoQueryDtoReq req = new OrderInfoQueryDtoReq();
		req.setOrderNo(payOrderInfoDtoReq.getOrderNo());
		BaseResponse<OrderListDtoResult> feginOrderListDtoResult = orderFeignClient.queryOrderInfoList(req);
		if (!feginOrderListDtoResult.isSuccess() || CollectionUtils.isEmpty(feginOrderListDtoResult.getData().getOrderList())) {
			logger.error("[PayOrderServiceImpl.createPayOrder]商城订单不存在" + JSON.toJSONString(payOrderInfoDtoReq));
			dtoResult.setCode(ResultCode.OC_ORDER_NOT_EXIST.getCode());
			dtoResult.setMsg(ResultCode.OC_ORDER_NOT_EXIST.getDesc());
			return dtoResult;
		}
		OrderDtoResult orderDtoResult = feginOrderListDtoResult.getData().getOrderList().get(0);
		//商城订单状态不为待支付,不能支付
		if (!OrderStatusEnum.WAITING_FOR_PAYMENT.name().equals(orderDtoResult.getStatus())
				&& !OrderStatusEnum.ALREADY_ENTRY_FACTORY.name().equals(orderDtoResult.getStatus())) {
			logger.error("[PayOrderServiceImpl.createPayOrder]商城订单状态不为待支付" + JSON.toJSONString(orderDtoResult));
			dtoResult.setCode(ResultCode.OC_ORDER_NOT_WAITTING_PAY.getCode());
			dtoResult.setMsg(ResultCode.OC_ORDER_NOT_WAITTING_PAY.getDesc());
			return dtoResult;
		}
		
		//如果是银行卡支付,并且是当天，查询库存是否够
		if(PayTypeEnums.CARD_PAY.name().equals(payOrderInfoDtoReq.getPayType())
				&& DateUtils.formatDate(new Date()).equals(DateUtils.formatDate(orderDtoResult.getPlanTransportTime()))){
			BaseResponse<OrderValidateRepositoryDtoResult> feginOrderValidateRepositoryDtoResult = orderIntegrationFeignClient.validateRepository(orderDtoResult.getId());
	    	if(feginOrderValidateRepositoryDtoResult.isSuccess() && feginOrderValidateRepositoryDtoResult.getData() != null){
	    		if(feginOrderValidateRepositoryDtoResult.getData().getRepositoryLowFlag()){
	    			logger.error("[PayOrderServiceImpl.createPayOrder]物料不足" + JSON.toJSONString(feginOrderValidateRepositoryDtoResult.getData().getMaterialName()));
	    			dtoResult.setCode(ResultCode.OC_ORDER_REPOSITORY_LOW.getCode());
		    		dtoResult.setMsg("库存不足："+JSON.toJSONString(feginOrderValidateRepositoryDtoResult.getData().getMaterialName())+",建议你选择其它拉料时间");
		    		return dtoResult;
		    	}
	    	}else{
    			logger.error("[PayOrderServiceImpl.createPayOrder]银行卡支付时查询库存错误" + JSON.toJSONString(feginOrderValidateRepositoryDtoResult));
	    		dtoResult.setCode(ResultCode.FAIL.getCode());
	    		dtoResult.setMsg(ResultCode.FAIL.getDesc());
	    		return dtoResult;
	    	}
		}
    	
		//生成支付订单号
		BaseResponse<PayOrderNoDtoResult> payOrderNoDtoResult = orderFeignClient.genPayNo();
		if (!payOrderNoDtoResult.isSuccess()) {
			logger.error("[PayOrderServiceImpl.createPayOrder]创建支付订单时生成支付订单号错误" + JSON.toJSONString(payOrderNoDtoResult));
			dtoResult.setCode(ResultCode.PAY_GEN_PAYNO_ERROR.getCode());
			dtoResult.setMsg(ResultCode.PAY_GEN_PAYNO_ERROR.getDesc());
			return dtoResult;
		}
		//添加支付订单
		dtoResult = addPayOrder(payOrderInfoDtoReq, dtoResult, orderDtoResult, payOrderNoDtoResult);
		return dtoResult;
	}
	
	@Override
	public PayOrderCreateDtoResult createPayUserAccOrder(PayOrderInfoDtoReq payOrderInfoDtoReq) {
		PayOrderCreateDtoResult dtoResult = new PayOrderCreateDtoResult();
		//生成支付订单号
		BaseResponse<PayOrderNoDtoResult> payOrderNoDtoResult = orderFeignClient.genPayNo();
		if (!payOrderNoDtoResult.isSuccess()) {
			logger.error("[PayOrderServiceImpl.createPayOrder]创建支付订单时生成支付订单号错误" + JSON.toJSONString(payOrderNoDtoResult));
			dtoResult.setCode(ResultCode.PAY_GEN_PAYNO_ERROR.getCode());
			dtoResult.setMsg(ResultCode.PAY_GEN_PAYNO_ERROR.getDesc());
			return dtoResult;
		}
		//添加支付订单
		dtoResult = addPayOrder(payOrderInfoDtoReq, dtoResult, null, payOrderNoDtoResult);
		
		return dtoResult;
	}

	/**  
	* @Title: addPayOrder  
	* @Description: 添加支付订单
	*/
	@Transactional
	private PayOrderCreateDtoResult addPayOrder(PayOrderInfoDtoReq payOrderInfoDtoReq, PayOrderCreateDtoResult dtoResult, OrderDtoResult orderDtoResult,
			BaseResponse<PayOrderNoDtoResult> payOrderNoDtoResult) {
		List<PayOrderInfoModel> list = new ArrayList<>();
		PayOrderInfoModel payOrderInfoModel = new PayOrderInfoModel();
		payOrderInfoModel.setAmount(payOrderInfoDtoReq.getAmount());
		//如果是账户充值 ，不用关联商城订单号和ID
		if(!PayTypeEnums.ACC_PAY.name().equals(payOrderInfoDtoReq.getPayType())){
			payOrderInfoModel.setOrderId(orderDtoResult.getId());
			payOrderInfoModel.setOrderNo(orderDtoResult.getOrderNo());
		}
		
		payOrderInfoModel.setPayType(payOrderInfoDtoReq.getPayType());
		payOrderInfoModel.setRemark(payOrderInfoDtoReq.getRemark());
		payOrderInfoModel.setShopId(Long.valueOf(payOrderInfoDtoReq.getShopId()));
		//如果是余额支付，支付订单不用对账
		if (PayTypeEnums.BAL_PAY.name().equals(payOrderInfoDtoReq.getPayType())) {
			payOrderInfoModel.setStatus(PayStatusEnum.PAY_PROGRESS.name());
			payOrderInfoModel.setCheckStatus(CheckPayStatusEnum.NOT_CHECK.name());
			payOrderInfoModel.setEbsStatus(EbsPayStatusEnum.EBS_PAY_PROGRESS.name());
			payOrderInfoModel.setBankOrderNo(orderDtoResult.getEbsorderNo());
		}else {
			payOrderInfoModel.setStatus(PayStatusEnum.PAY_PROGRESS.name());
			payOrderInfoModel.setCheckStatus(CheckPayStatusEnum.CHECK_PAY_WAITTING.name());
			payOrderInfoModel.setEbsStatus(EbsPayStatusEnum.EBS_PAY_WAITTING.name());
		}
		payOrderInfoModel.setCusno(payOrderInfoDtoReq.getCusNo());
		payOrderInfoModel.setMerchno(payOrderInfoDtoReq.getMerchno());
		payOrderInfoModel.setPayOrderNo(payOrderNoDtoResult.getData().getPayOrderNo());
		payOrderInfoModel.setPlatformid(payOrderInfoDtoReq.getPlatformid());
		payOrderInfoModel.setUserId(payOrderInfoDtoReq.getUserId());
		payOrderInfoModel.setTradeTime(new Date());
		payOrderInfoModel.setOrgId(payOrderInfoDtoReq.getOrgId());
		list.add(payOrderInfoModel);
		
		long i = payOrderInfoDao.insert(list);
		
		if (i > 0) {
			//返回值给跳转到支付页面用
			if(!PayTypeEnums.ACC_PAY.name().equals(payOrderInfoDtoReq.getPayType())){
				dtoResult.setMoneyfeedOrderId(orderDtoResult.getId());
			}
			dtoResult.setPayOrderId(list.get(0).getId());
			dtoResult.setPayOrderNo(payOrderInfoModel.getPayOrderNo());
			dtoResult.setPlatformId(payOrderInfoDtoReq.getPlatformid());
			dtoResult.setMerchantAcc(payOrderInfoDtoReq.getMerchno());
			dtoResult.setCusNo(payOrderInfoDtoReq.getCusNo());

			//---------------------------记录统一日志--------------------------------------------------
			addUcLog(payOrderInfoModel, "创建支付订单");
			addPayLog(payOrderInfoModel, "创建支付订单");
			
			dtoResult.setCode(ResultCode.SUCCESS.getCode());
			dtoResult.setMsg(ResultCode.SUCCESS.getDesc());
		} else {
			logger.error("[PayOrderServiceImpl.createPayOrder]插入支付订单不成功" + i);
			throw new BizException(ResultCode.PAY_CREATE_ERROR);
		}
		return dtoResult;
	}

	@Override
	@Transactional
	public PayCallbackDtoResult payCallback(PayOrderCallbackDtoReq payOrderCallbackDtoReq) {
		/**
		 * 取出商城订单,支付订单
		 * 根据返回的订单状态修改商城订单和支付订单，记录日志，向EBS发起请求
		 * 0 未支付，1 成功，2失败
		 * 0-支付订单状态不变
		 * 1-支付订单状态改为已支付，商城订单状态为已支付,向EBS发起支付或充值请求
		 * 2-支付订单状态改为未支付，记录支付失败原因
		 */
		PayCallbackDtoResult dtoResult = new PayCallbackDtoResult();
		//支付订单号必传
		if (payOrderCallbackDtoReq.getOrderNo() == null) {
			logger.error("[PayOrderServiceImpl.payCallback]支付回调时支付订单号为空" + JSON.toJSONString(payOrderCallbackDtoReq));
			dtoResult.setCode(ResultCode.PAY_ORDER_NOT_EXIST.getCode());
			dtoResult.setMsg(ResultCode.PAY_ORDER_NOT_EXIST.getDesc());
			return dtoResult;
		}
		
		//查询支付订单
		PayOrderInfoModel payOrderInfoModel = new PayOrderInfoModel();
		payOrderInfoModel.setPayOrderNo(payOrderCallbackDtoReq.getOrderNo());
		List<PayOrderInfoModel> payOrderInfoModels = payOrderInfoDao.select(payOrderInfoModel);
		if (CollectionUtils.isEmpty(payOrderInfoModels)) {
			logger.error("[PayOrderServiceImpl.payCallback]支付回调时支付订单不存在" + JSON.toJSONString(payOrderCallbackDtoReq));
			dtoResult.setCode(ResultCode.PAY_ORDER_NOT_EXIST.getCode());
			dtoResult.setMsg(ResultCode.PAY_ORDER_NOT_EXIST.getDesc());
			return dtoResult;
		}
		payOrderInfoModel = payOrderInfoModels.get(0);
		
		//如果是账户充值 回调，不操作商城订单
		if (PayTypeEnums.ACC_PAY.name().equals(payOrderInfoModel.getPayType())) {
			modifyPayInfoForCallback(payOrderCallbackDtoReq, dtoResult, payOrderInfoModel, null);
			return dtoResult;
		}
		
		//查询商城订单
		OrderInfoQueryDtoReq req = new OrderInfoQueryDtoReq();
		req.setOrderNo(payOrderInfoModel.getOrderNo());
		BaseResponse<OrderListDtoResult> feginOrderListDtoResult = orderFeignClient.queryOrderInfoList(req);
		if (!feginOrderListDtoResult.isSuccess() || CollectionUtils.isEmpty(feginOrderListDtoResult.getData().getOrderList())) {
			logger.error("[PayOrderServiceImpl.payCallback]支付回调时商城订单不存在" + JSON.toJSONString(payOrderCallbackDtoReq));
			dtoResult.setCode(ResultCode.OC_ORDER_NOT_EXIST.getCode());
			dtoResult.setMsg(ResultCode.OC_ORDER_NOT_EXIST.getDesc());
			return dtoResult;
		}
		OrderDtoResult orderDtoResult = feginOrderListDtoResult.getData().getOrderList().get(0);
		
		//如果支付订单不是进行中，不需要更新
		if (!PayStatusEnum.PAY_PROGRESS.name().equals(payOrderInfoModel.getStatus())) {
			logger.error("[PayOrderServiceImpl.payCallback]支付回调时支付订单不是进行中" + JSON.toJSONString(payOrderInfoModel));
			dtoResult.setCode(ResultCode.PAY_ORDER_NOT_PROGRESS.getCode());
			dtoResult.setMsg(ResultCode.PAY_ORDER_NOT_PROGRESS.getDesc());
			return dtoResult;
		}

		//如果商城订单不是待支付不需要更新
		if (!OrderStatusEnum.WAITING_FOR_PAYMENT.name().equals(orderDtoResult.getStatus()) 
				&& !OrderStatusEnum.ALREADY_ENTRY_FACTORY.name().equals(orderDtoResult.getStatus())) {
			logger.error("[PayOrderServiceImpl.payCallback]支付回调时商城订单不是待支付" + JSON.toJSONString(orderDtoResult));
			dtoResult.setCode(ResultCode.OC_ORDER_NOT_WAITTING_PAY.getCode());
			dtoResult.setMsg(ResultCode.OC_ORDER_NOT_WAITTING_PAY.getDesc());
			return dtoResult;
		}
		
		//根据回调修改信息
		modifyPayInfoForCallback(payOrderCallbackDtoReq, dtoResult, payOrderInfoModel, orderDtoResult);
		
		dtoResult.setOrderId(orderDtoResult.getId());
		dtoResult.setOrderNo(orderDtoResult.getOrderNo());
		return dtoResult;
	}

	/**  
	* @Title: modifyPayInfoForCallback  
	* @Description: 根据回调修改信息
	*/
	@Transactional
	private void modifyPayInfoForCallback(PayOrderCallbackDtoReq payOrderCallbackDtoReq, PayCallbackDtoResult dtoResult, PayOrderInfoModel payOrderInfoModel,
			OrderDtoResult orderDtoResult) {
		//接收到请求后更新支付订单
		PayOrderInfoModel oldPayOrderInfoModel = new PayOrderInfoModel();
		oldPayOrderInfoModel.setId(payOrderInfoModel.getId());
		oldPayOrderInfoModel.setStatus(PayStatusEnum.PAY_PROGRESS.name());
		oldPayOrderInfoModel.setPayOrderNo(payOrderCallbackDtoReq.getOrderNo());
		
		PayOrderInfoModel newPayOrderInfoModel = new PayOrderInfoModel();
		
		//商城订单
		OrderInfoModifyDtoReq requestBody = new OrderInfoModifyDtoReq();
		if(!PayTypeEnums.ACC_PAY.name().equals(payOrderInfoModel.getPayType())){
			requestBody.setOrderId(orderDtoResult.getId());
			requestBody.setOldStatus(orderDtoResult.getStatus());
		}
		
		//判断支付平台返回的状态 0 未支付，1 成功，2失败
		if ("0".equals(payOrderCallbackDtoReq.getOrderStatus())) {
			logger.error("[PayOrderServiceImpl.payCallback]支付回调时支付订单状态为未支付" + JSON.toJSONString(payOrderCallbackDtoReq));
			//newModel.setStatus(PayStatusEnum.PAY_WAITTING.name());
			newPayOrderInfoModel.setReason(payOrderCallbackDtoReq.getOrderStatus());
		} else if ("1".equals(payOrderCallbackDtoReq.getOrderStatus())) {
			newPayOrderInfoModel.setStatus(PayStatusEnum.PAY_SUCESS.name());
			newPayOrderInfoModel.setBankOrderNo(payOrderCallbackDtoReq.getAccNo());
			newPayOrderInfoModel.setBankTradeTime(DateUtils.str2Date(payOrderCallbackDtoReq.getAccDate(), DateUtils.yyyymmddhhmmss));
			newPayOrderInfoModel.setBankAmount(new BigDecimal(payOrderCallbackDtoReq.getTradeAmt()));
			newPayOrderInfoModel.setEbsStatus(EbsPayStatusEnum.EBS_PAY_PROGRESS.name());
			newPayOrderInfoModel.setShopBankAcco(payOrderCallbackDtoReq.getBankCardNo());
			newPayOrderInfoModel.setChannelcode(payOrderCallbackDtoReq.getChannelCode());
			newPayOrderInfoModel.setSubmerchid(payOrderCallbackDtoReq.getSubmerchid());
			newPayOrderInfoModel.setWaterno(payOrderCallbackDtoReq.getWaterNo());
			newPayOrderInfoModel.setBankname(payOrderCallbackDtoReq.getBankName());
			
			if(PayTypeEnums.CARD_PAY.name().equals(payOrderInfoModel.getPayType())){
				requestBody.setStatus(OrderStatusEnum.PAID.name());
				requestBody.setPayTime(DateUtils.str2Date(payOrderCallbackDtoReq.getAccDate(), DateUtils.yyyymmddhhmmss));
				requestBody.setPayFlag(true);
				requestBody.setPayType(OrderPayTypeEnum.BANK_CARD.name());
			}
		} else if ("2".equals(payOrderCallbackDtoReq.getOrderStatus())) {
			newPayOrderInfoModel.setStatus(PayStatusEnum.PAY_FAIL.name());
			newPayOrderInfoModel.setReason(payOrderCallbackDtoReq.getOrderStatus());
		} else {
			logger.error("[PayOrderServiceImpl.payCallback]返回状态码不正确" + payOrderCallbackDtoReq.getOrderStatus());
			throw new BizException(ResultCode.PAY_STATUS_CODE_ERROR);
		}
		
		//修改支付订单
		boolean b = update(oldPayOrderInfoModel, newPayOrderInfoModel);
		if (!b) {
			logger.error("[PayOrderServiceImpl.payCallback]修改支付订单不成功" + JSON.toJSONString(oldPayOrderInfoModel));
			logger.error("[PayOrderServiceImpl.payCallback]修改支付订单不成功" + JSON.toJSONString(newPayOrderInfoModel));
			throw new BizException(ResultCode.PAY_ORDER_UPDATE_ERROR);
		}
		//如果是支付,修改商城订单
		if(PayTypeEnums.CARD_PAY.name().equals(payOrderInfoModel.getPayType())){
			BaseResponse<DtoResult> orderFeginDtoResult = orderFeignClient.updateOrderInfo(requestBody);
			if (!orderFeginDtoResult.isSuccess()) {
				logger.error("[PayOrderServiceImpl.payCallback]修改商城订单不成功" + JSON.toJSONString(requestBody));
				addPayLog(payOrderInfoModel, orderFeginDtoResult.getMsg());
				throw new BizException(ResultCode.OC_ORDER_NOT_UPDATE_ERROR);
			}
		}

		//支付成功的话调中台接口传输充值，支付数据
		if ("1".equals(payOrderCallbackDtoReq.getOrderStatus())) {
			notifyEbs(payOrderCallbackDtoReq, payOrderInfoModel, orderDtoResult, newPayOrderInfoModel);
		}

		//发送支付消息
		sendPayMsg(payOrderCallbackDtoReq, payOrderInfoModel, orderDtoResult);
		
		//记录用户账户流水
		CustomerAccountStatementPostListDtoReq customerAccountStatementPostListDtoReq = new CustomerAccountStatementPostListDtoReq();
		List<CustomerAccountStatementPostDtoReq> dataList = new ArrayList<>();
		CustomerAccountStatementPostDtoReq cstomerAccountStatementPostDtoReq = new CustomerAccountStatementPostDtoReq();
		cstomerAccountStatementPostDtoReq.setAmount(newPayOrderInfoModel.getBankAmount());
		cstomerAccountStatementPostDtoReq.setBizType(payOrderInfoModel.getPayType());
		cstomerAccountStatementPostDtoReq.setClientUserId(payOrderInfoModel.getUserId());
		cstomerAccountStatementPostDtoReq.setOrderNo(payOrderInfoModel.getOrderNo());
		cstomerAccountStatementPostDtoReq.setSerialNumber(newPayOrderInfoModel.getBankOrderNo());
		cstomerAccountStatementPostDtoReq.setPayOrderNo(payOrderInfoModel.getPayOrderNo());
		cstomerAccountStatementPostDtoReq.setShopId(payOrderInfoModel.getShopId());
		dataList.add(cstomerAccountStatementPostDtoReq);
		customerAccountStatementPostListDtoReq.setDataList(dataList);
		BaseResponse<DtoResult> cusDtoResult = customerAccountFeignClient.saveCustomerAccountStatement(customerAccountStatementPostListDtoReq);
		if (!cusDtoResult.isSuccess()) {
			logger.error("[PayOrderServiceImpl.payCallback]插入支付订单日志失败");
			throw new BizException(ResultCode.PAY_LOG_ERROR);
		}
		
		addUcLog(payOrderInfoModel, "支付平台回调");
		addPayLog(payOrderInfoModel, payOrderCallbackDtoReq.getOrderStatus());

		dtoResult.setPayType(payOrderInfoModel.getPayType());
		dtoResult.setCode(ResultCode.SUCCESS.getCode());
		dtoResult.setMsg(ResultCode.SUCCESS.getDesc());
	}

	/**  
	* @Title: sendPayMsg  
	* @Description: 支付回调后发送消息
	*/
	private void sendPayMsg(PayOrderCallbackDtoReq payOrderCallbackDtoReq, PayOrderInfoModel payOrderInfoModel, OrderDtoResult orderDtoResult) {
		if (PayTypeEnums.CARD_PAY.name().equals(payOrderInfoModel.getPayType())) {
			
//			订单支付成功，且订单有司机手机号码	支付成功	司机	
//			【料你富】${userName}有一条${shopName}的饲料订单等待您去装运， 装运时间：${transportTime}，
//			订单重量：${weight}吨，货主电话：${tel}

			//查询订单拉料信息
			OrderFeedTransportQueryDtoReq orderFeedTransport = new OrderFeedTransportQueryDtoReq();
			orderFeedTransport.setOrderId(orderDtoResult.getId());
			BaseResponse<OrderTransportListDtoResult> feginOrderTransportListDtoResult = orderTransportFeignClient.queryFeedTransport(orderFeedTransport);
			if(feginOrderTransportListDtoResult.isSuccess() && CollectionUtils.isNotEmpty(feginOrderTransportListDtoResult.getData().getOrderFeedTransports())){
				OrderFeedTransportModel orderFeedTransportModel = feginOrderTransportListDtoResult.getData().getOrderFeedTransports().get(0);
				if(orderFeedTransportModel.getDriverMobile() != null){
					//查询赠包信息
					OrderPresentFeedQueryDtoReq orderPresentFeed = new OrderPresentFeedQueryDtoReq();
					orderPresentFeed.setOrderId(orderDtoResult.getId());
					BaseResponse<OrderPresentFeedListDtoResult> feginOrderPresentFeedListDtoResult = orderPresentFeedFeignClient.queryOrderPresentFeed(orderPresentFeed);
					
					BigDecimal presentWeight = BigDecimal.ZERO;
					if(feginOrderPresentFeedListDtoResult.isSuccess() && CollectionUtils.isNotEmpty(feginOrderPresentFeedListDtoResult.getData().getOrderPresentFeeds())){
						List<OrderPresentFeedModel> orderPresentFeeds = feginOrderPresentFeedListDtoResult.getData().getOrderPresentFeeds();
						for (OrderPresentFeedModel orderPresentFeedModel2 : orderPresentFeeds) {
							presentWeight = presentWeight.add(new BigDecimal(orderPresentFeedModel2.getCount()));
						}
					}
					
					SmsSendDtoReq smsSendDtoReq = new SmsSendDtoReq();
					smsSendDtoReq = new SmsSendDtoReq();
					smsSendDtoReq.setAuthCode(false);
					smsSendDtoReq.setMobile(orderFeedTransportModel.getDriverMobile());
					smsSendDtoReq.setTemplateId(SmsTemplateEnums.SMS_151767096.name());
					Map<String, String> paramMap = new HashMap<>();
					paramMap.put("userName", orderDtoResult.getBuyerName());
					paramMap.put("shopName", orderDtoResult.getUcShopName());
					paramMap.put("transportTime", DateUtils.date_sdf_wz.format(orderDtoResult.getPlanTransportTime()));
					paramMap.put("weight", String.valueOf((orderDtoResult.getTotalFeedWeight().add(presentWeight)).divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP)));
					paramMap.put("tel", orderDtoResult.getUcShopMobile());
					smsSendDtoReq.setParamMap(paramMap);
					smsFeignClient.sendSms(smsSendDtoReq);
				}
			}
			
			//		订单使用银行卡支付，支付成功	用户	
			//		【料你富】您的订单${orderNo}已支付成功，支付方式：银行卡，支付金额：${amount}元。装运时间：${transportTime}，请及时安排	
			//		项目名称：支付成功                                                                      项目进展：通知                                                                    
			//		您的订单1788已支付成功，支付方式：银行卡，支付金额：23000元。拉料日期：11月17日，请提前安排

			SmsSendDtoReq dtoReq = new SmsSendDtoReq();
			dtoReq.setAuthCode(false);
			dtoReq.setMobile(orderDtoResult.getBuyerMobile());
			dtoReq.setTemplateId(SmsTemplateEnums.SMS_151767105.name());
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("orderNo", orderDtoResult.getOrderNo());
			paramMap.put("amount", payOrderCallbackDtoReq.getTradeAmt());
			paramMap.put("transportTime", DateUtils.date_sdf_wz.format(orderDtoResult.getPlanTransportTime()));
			dtoReq.setParamMap(paramMap);
			smsFeignClient.sendSms(dtoReq);
			
			//发送微信给用户
			BaseResponse<ClientUserThirdAccountDtoResult> clientUserThirdAccountDtoResult = clientUserFeignClient
					.queryUserThirdAccount(orderDtoResult.getBuyerId(), null, AppSourceEnums.WECHAT.name());
			if(clientUserThirdAccountDtoResult.isSuccess() && clientUserThirdAccountDtoResult.getData().getAccount() != null){
				WechatMsgDtoReq wechatMsgDtoReq = new WechatMsgDtoReq();
				wechatMsgDtoReq.setOpenid(clientUserThirdAccountDtoResult.getData().getAccount().getThirdAccount());
				List<String> params = new ArrayList<>();
				params.add(orderDtoResult.getOrderNo());
				params.add(PayTypeEnums.CARD_PAY.getDesc());
				params.add(payOrderCallbackDtoReq.getTradeAmt());
				params.add(DateUtils.date_sdf_wz.format(orderDtoResult.getPlanTransportTime()));
				wechatMsgDtoReq.setParams(params);
				String url = hompageurl + "/static/html/order/order_temp.html?orderId=" + orderDtoResult.getId() + "&pre_path=_static_html_order_orderList";
				wechatMsgDtoReq.setUrl(url);
				wechatMsgDtoReq.setWechatMsgTypeEnums(WechatMsgTypeEnums.PAY_SUCCESS);
				wechatMsgFeignClient.sendWechatMsg(wechatMsgDtoReq);
			}
			
		}else if (PayTypeEnums.SO_PAY.name().equals(payOrderInfoModel.getPayType())) {
			//			可用余额变动-充值	用户	【料你富】您的${shopName}可用额度账户成功充值${money}元，如有疑问致电${shopPhone}	
			//			项目名称：可用额度充值成功                                                                      项目进展：通知                                                                     
			//			您的桂林新希望可用额度账户成功充值50000元，如有疑问致电0773-681918
			SmsSendDtoReq dtoReq = new SmsSendDtoReq();
			dtoReq.setAuthCode(false);
			dtoReq.setMobile(orderDtoResult.getBuyerMobile());
			dtoReq.setTemplateId(SmsTemplateEnums.SMS_152205437.name());
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("shopName", payOrderCallbackDtoReq.getShopName());
			paramMap.put("money", payOrderCallbackDtoReq.getTradeAmt());
			paramMap.put("shopPhone", orderDtoResult.getUcShopMobile());
			dtoReq.setParamMap(paramMap);
			smsFeignClient.sendSms(dtoReq);

			//发送微信给用户
			BaseResponse<ClientUserThirdAccountDtoResult> clientUserThirdAccountDtoResult = clientUserFeignClient
					.queryUserThirdAccount(orderDtoResult.getBuyerId(), null, AppSourceEnums.WECHAT.name());
			if(clientUserThirdAccountDtoResult.isSuccess() && clientUserThirdAccountDtoResult.getData().getAccount() != null){
				WechatMsgDtoReq wechatMsgDtoReq = new WechatMsgDtoReq();
				wechatMsgDtoReq.setOpenid(clientUserThirdAccountDtoResult.getData().getAccount().getThirdAccount());
				List<String> params = new ArrayList<>();
				params.add(payOrderCallbackDtoReq.getShopName());
				params.add(payOrderCallbackDtoReq.getTradeAmt());
				params.add(orderDtoResult.getUcShopMobile());
				wechatMsgDtoReq.setParams(params);
				//			wechatMsgDtoReq.setUrl("");
				wechatMsgDtoReq.setWechatMsgTypeEnums(WechatMsgTypeEnums.WALLET_RECHARGE);
				wechatMsgFeignClient.sendWechatMsg(wechatMsgDtoReq);
			}
		}else if (PayTypeEnums.ACC_PAY.name().equals(payOrderInfoModel.getPayType())) {
	//			可用余额变动-充值	用户	【料你富】您的${shopName}可用额度账户成功充值${money}元，如有疑问致电${shopPhone}	
			//			项目名称：可用额度充值成功                                                                      项目进展：通知                                                                     
			//			您的桂林新希望可用额度账户成功充值50000元，如有疑问致电0773-681918
			SmsSendDtoReq dtoReq = new SmsSendDtoReq();
			dtoReq.setAuthCode(false);
			dtoReq.setMobile(payOrderCallbackDtoReq.getUserMobile());
			dtoReq.setTemplateId(SmsTemplateEnums.SMS_152205437.name());
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("shopName", payOrderCallbackDtoReq.getShopName());
			paramMap.put("money", payOrderCallbackDtoReq.getTradeAmt());
			paramMap.put("shopPhone", payOrderCallbackDtoReq.getShopMobile());
			dtoReq.setParamMap(paramMap);
			smsFeignClient.sendSms(dtoReq);
	
			//发送微信给用户
			if(StringUtils.isNotEmpty(payOrderCallbackDtoReq.getThirdAcct())){
				WechatMsgDtoReq wechatMsgDtoReq = new WechatMsgDtoReq();
				wechatMsgDtoReq.setOpenid(payOrderCallbackDtoReq.getThirdAcct());
				List<String> params = new ArrayList<>();
				params.add(payOrderCallbackDtoReq.getShopName());
				params.add(payOrderCallbackDtoReq.getTradeAmt());
				params.add(payOrderCallbackDtoReq.getShopMobile());
				wechatMsgDtoReq.setParams(params);
				//			wechatMsgDtoReq.setUrl("");
				wechatMsgDtoReq.setWechatMsgTypeEnums(WechatMsgTypeEnums.WALLET_RECHARGE);
				wechatMsgFeignClient.sendWechatMsg(wechatMsgDtoReq);
			}
		}
	}

	/**  
	* @Title: notifyEbs  
	* @Description: 同步支付成功信息到EBS
	*/
	private void notifyEbs(PayOrderCallbackDtoReq payOrderCallbackDtoReq, PayOrderInfoModel payOrderInfoModel, OrderDtoResult orderDtoResult,
			PayOrderInfoModel newPayOrderInfoModel) {
		if (PayTypeEnums.CARD_PAY.name().equals(payOrderInfoModel.getPayType())) {
			NotifyPaymentDtoReq notifyPaymentDtoReq = new NotifyPaymentDtoReq();
			notifyPaymentDtoReq.setAccNo(payOrderCallbackDtoReq.getAccNo());
			notifyPaymentDtoReq.setCurrency("CNY");
			notifyPaymentDtoReq.setCusNo(payOrderInfoModel.getCusno());
			notifyPaymentDtoReq.setEbsOrderNo(orderDtoResult.getEbsorderNo());
			notifyPaymentDtoReq.setMoneyfeedPayNo(payOrderInfoModel.getPayOrderNo());
			notifyPaymentDtoReq.setPayDate(DateUtils.str2Date(
					DateUtils.formatDate(DateUtils.str2Date(payOrderCallbackDtoReq.getAccDate(), DateUtils.yyyymmddhhmmss).getTime()), DateUtils.date_sdf));
			notifyPaymentDtoReq.setPayDateString(DateUtils.date2Str(newPayOrderInfoModel.getBankTradeTime(), DateUtils.date_sdf));
			notifyPaymentDtoReq.setTotalPayAmount(newPayOrderInfoModel.getBankAmount());
			notifyPaymentDtoReq.setOrgId(payOrderCallbackDtoReq.getOrgId());
			notifyPaymentDtoReq.setEbsRealAccount(payOrderCallbackDtoReq.getBankCardNo());

			DtoResult notifydtoResult = payIntegrationService.notifyOrderPaymentByBankcard(notifyPaymentDtoReq);
			if (!ResultCode.SUCCESS.getCode().equals(notifydtoResult.getCode())) {
				logger.error("[PayOrderServiceImpl.payCallback]调用EBS接口同步支付信息错误" + JSON.toJSONString(notifydtoResult));
				addPayLog(payOrderInfoModel, notifydtoResult.getMsg());
				throw new BizException(ResultCode.PAY_EBS_PAY_ERROR);
			}
		} else if(PayTypeEnums.ACC_PAY.name().equals(payOrderInfoModel.getPayType())){
			NotifyRechargeDtoReq notifyRechargeDtoReq = new NotifyRechargeDtoReq();
			notifyRechargeDtoReq.setAccNo(payOrderCallbackDtoReq.getAccNo());
			notifyRechargeDtoReq.setCurrency("CNY");
			notifyRechargeDtoReq.setCusNo(payOrderInfoModel.getCusno());
			notifyRechargeDtoReq.setMoneyfeedPayNo(payOrderInfoModel.getPayOrderNo());
			notifyRechargeDtoReq.setPayType(payOrderInfoModel.getPayType());
			notifyRechargeDtoReq.setRechargeAmount(newPayOrderInfoModel.getBankAmount());
			notifyRechargeDtoReq.setRechargeDate(newPayOrderInfoModel.getBankTradeTime());
			notifyRechargeDtoReq.setOrgId(payOrderCallbackDtoReq.getOrgId());
			notifyRechargeDtoReq.setEbsRealAccount(payOrderCallbackDtoReq.getBankCardNo());

			DtoResult notifydtoResult = payIntegrationService.notifyCustomerRecharge(notifyRechargeDtoReq);
			if (!ResultCode.SUCCESS.getCode().equals(notifydtoResult.getCode())) {
				logger.error("[PayOrderServiceImpl.payCallback]调用EBS接口同步充值 信息错误" + JSON.toJSONString(notifydtoResult));
				addPayLog(payOrderInfoModel, notifydtoResult.getMsg());
				throw new BizException(ResultCode.PAY_EBS_RECHARGE_ERROR);
			}
		}
		else if (PayTypeEnums.SO_PAY.name().equals(payOrderInfoModel.getPayType())) {
			NotifyRechargeDtoReq notifyRechargeDtoReq = new NotifyRechargeDtoReq();
			notifyRechargeDtoReq.setAccNo(payOrderCallbackDtoReq.getAccNo());
			notifyRechargeDtoReq.setCurrency("CNY");
			notifyRechargeDtoReq.setCusNo(payOrderInfoModel.getCusno());
			notifyRechargeDtoReq.setEbsOrderNo(orderDtoResult.getEbsorderNo());
			notifyRechargeDtoReq.setMoneyfeedPayNo(payOrderInfoModel.getPayOrderNo());
			notifyRechargeDtoReq.setPayType(payOrderInfoModel.getPayType());
			notifyRechargeDtoReq.setRechargeAmount(newPayOrderInfoModel.getBankAmount());
			notifyRechargeDtoReq.setRechargeDate(DateUtils.str2Date(
					DateUtils.formatDate(DateUtils.str2Date(payOrderCallbackDtoReq.getAccDate(), DateUtils.yyyymmddhhmmss).getTime()), DateUtils.date_sdf));
			notifyRechargeDtoReq.setOrgId(payOrderCallbackDtoReq.getOrgId());
			notifyRechargeDtoReq.setEbsRealAccount(payOrderCallbackDtoReq.getBankCardNo());

			DtoResult notifydtoResult = payIntegrationService.notifyCustomerRecharge(notifyRechargeDtoReq);
			if (!ResultCode.SUCCESS.getCode().equals(notifydtoResult.getCode())) {
				logger.error("[PayOrderServiceImpl.payCallback]调用EBS接口同步充值 信息错误" + JSON.toJSONString(notifydtoResult));
				addPayLog(payOrderInfoModel, notifydtoResult.getMsg());
				throw new BizException(ResultCode.PAY_EBS_RECHARGE_ERROR);
			}
		}
	}

	/**  
	* @Title: addPayLog  
	* @Description: 记录支付日志
	*/
	private void addPayLog(PayOrderInfoModel oldOrderInfoModel, String remark) {
		//查询支付订单
		PayOrderInfoModel newOrderInfoModel = new PayOrderInfoModel();
		newOrderInfoModel.setId(oldOrderInfoModel.getId());
		newOrderInfoModel = payOrderInfoDao.select(newOrderInfoModel).get(0);

		List<PayOrderLogModel> payOrderLogModels = new ArrayList<>();
		PayOrderLogModel payOrderLogModel = new PayOrderLogModel();
		payOrderLogModel.setAfterCheckStatus(newOrderInfoModel.getCheckStatus());
		payOrderLogModel.setAfterEbsStatus(newOrderInfoModel.getEbsStatus());
		payOrderLogModel.setAfterStatus(newOrderInfoModel.getStatus());
		payOrderLogModel.setBeforeCheckStatus(oldOrderInfoModel.getCheckStatus());
		payOrderLogModel.setBeforeEbsStatus(oldOrderInfoModel.getEbsStatus());
		payOrderLogModel.setBeforeStatus(oldOrderInfoModel.getStatus());
		payOrderLogModel.setPayOrderId(oldOrderInfoModel.getId());
		payOrderLogModel.setPayOrderNo(oldOrderInfoModel.getPayOrderNo());
		payOrderLogModel.setRemark(remark);
		payOrderLogModels.add(payOrderLogModel);
		long i = payOrderLogDao.insert(payOrderLogModels);
		if (i <= 0) {
			logger.error("[PayOrderServiceImpl.addPayLog]插入支付订单日志失败");
			throw new BizException(ResultCode.PAY_LOG_ERROR);
		}
	}

	/**  
	* @Title: addUcLog  
	* @Description: 记录统一日志
	*/
	private void addUcLog(PayOrderInfoModel oldOrderInfoModel, String comment) {
		//查询支付订单
		PayOrderInfoModel newOrderInfoModel = new PayOrderInfoModel();
		newOrderInfoModel.setId(oldOrderInfoModel.getId());
		newOrderInfoModel = payOrderInfoDao.select(newOrderInfoModel).get(0);

		UserOperationLogDtoReq userOperationLogDtoReq = new UserOperationLogDtoReq();
		userOperationLogDtoReq.setUserOperEventType(UserOperEventType.PAY);
		userOperationLogDtoReq.setEventId(oldOrderInfoModel.getId());
		userOperationLogDtoReq.setOrderId(oldOrderInfoModel.getId());
		userOperationLogDtoReq.setEventDate(new Date());
		userOperationLogDtoReq.setBeforeEventAmount(oldOrderInfoModel.getAmount());
		userOperationLogDtoReq.setAfterEventAmount(oldOrderInfoModel.getAmount());
		userOperationLogDtoReq.setBeforeEventStatus(oldOrderInfoModel.getStatus());
		userOperationLogDtoReq.setAfterEventStatus(newOrderInfoModel.getStatus());
		userOperationLogDtoReq.setUserOperationEnums(UserOperationEnums.PA_SHOP_CREATE_PAY_TO_BANK);
		userOperationLogDtoReq.setBeforeEventModel(JSON.toJSONString(oldOrderInfoModel));
		userOperationLogDtoReq.setAfterEventModel(JSON.toJSONString(newOrderInfoModel));
		userOperationLogDtoReq.setComment(comment);
		userOperationLogDtoReq.setSource(UserOperSourceType.PAY_CENTER);
		BaseResponse<DtoResult> operaResp = userOperationFeignClient.recordingUserOperation(userOperationLogDtoReq);
		if (!operaResp.isSuccess()) {
			logger.error("[PayOrderServiceImpl.addUcLog]插入支付订单统一日志失败");
			throw new BizException(ResultCode.PAY_LOG_ERROR);
		}
	}

	@Override
	public PageList<PayOrderInfoModel> queryList(PayOrderInfoQueryDtoReq queryReq) {

		PageBounds pageBounds;
		if (queryReq.getPage() == null || queryReq.getPageSize() == null) {
			pageBounds = new PageBounds();
		} else {
			pageBounds = new PageBounds(queryReq.getPage().intValue(), queryReq.getPageSize());
		}
		return payOrderInfoDao.queryList(queryReq, pageBounds);
	}

	@Override
	public DtoResult paySpecial(PaySpecialDtoReq paySpecialDtoReq) {
		logger.info("[PayOrderInfoController.paySpecial]特殊处理传入的参数" + JSON.toJSONString(paySpecialDtoReq));
		DtoResult dtoResult = new DtoResult();
		
		if (StringUtils.isEmpty(paySpecialDtoReq.getPayNo())) {
			logger.error("[PayOrderServiceImpl.payCallback]支付回调时支付订单号为空" + JSON.toJSONString(paySpecialDtoReq));
			dtoResult.setCode(ResultCode.PAY_ORDER_NOT_EXIST.getCode());
			dtoResult.setMsg(ResultCode.PAY_ORDER_NOT_EXIST.getDesc());
			return dtoResult;
		}

		//查询支付订单
		PayOrderInfoModel payOrderInfoModel = new PayOrderInfoModel();
		if(paySpecialDtoReq.getPayNo() != null){
			payOrderInfoModel.setPayOrderNo(paySpecialDtoReq.getPayNo());
			List<PayOrderInfoModel> payOrderInfoModels = payOrderInfoDao.select(payOrderInfoModel);
			if (CollectionUtils.isEmpty(payOrderInfoModels)) {
				logger.error("[PayOrderServiceImpl.payCallback]支付回调时支付订单不存在" + JSON.toJSONString(payOrderInfoModel));
				dtoResult.setCode(ResultCode.PAY_ORDER_NOT_EXIST.getCode());
				dtoResult.setMsg(ResultCode.PAY_ORDER_NOT_EXIST.getDesc());
				return dtoResult;
			}
			payOrderInfoModel = payOrderInfoModels.get(0);
		}
		
		//查询商城订单
		OrderDtoResult orderDtoResult = null;
		if(payOrderInfoModel.getOrderNo() != null){
			OrderInfoQueryDtoReq req = new OrderInfoQueryDtoReq();
			req.setOrderNo(payOrderInfoModel.getOrderNo());
			BaseResponse<OrderListDtoResult> feginOrderListDtoResult = orderFeignClient.queryOrderInfoList(req);
			if (!feginOrderListDtoResult.isSuccess() || CollectionUtils.isEmpty(feginOrderListDtoResult.getData().getOrderList())) {
				logger.error("[PayOrderServiceImpl.payCallback]支付回调时商城订单不存在" + JSON.toJSONString(req));
				dtoResult.setCode(ResultCode.OC_ORDER_NOT_EXIST.getCode());
				dtoResult.setMsg(ResultCode.OC_ORDER_NOT_EXIST.getDesc());
				return dtoResult;
			}
			orderDtoResult = feginOrderListDtoResult.getData().getOrderList().get(0);
		}
		//同步ebs失败，执行同步，ebs余额支付不用同步
		if(paySpecialDtoReq.getType().equals("1")){
			if (PayTypeEnums.CARD_PAY.name().equals(payOrderInfoModel.getPayType())) {
				NotifyPaymentDtoReq notifyPaymentDtoReq = new NotifyPaymentDtoReq();
				notifyPaymentDtoReq.setAccNo(payOrderInfoModel.getBankOrderNo());
				notifyPaymentDtoReq.setCurrency("CNY");
				notifyPaymentDtoReq.setCusNo(payOrderInfoModel.getCusno());
				notifyPaymentDtoReq.setEbsOrderNo(orderDtoResult.getEbsorderNo());
				notifyPaymentDtoReq.setMoneyfeedPayNo(payOrderInfoModel.getPayOrderNo());
				notifyPaymentDtoReq.setPayDate(DateUtils.str2Date(
						DateUtils.formatDate(payOrderInfoModel.getBankTradeTime().getTime()), DateUtils.date_sdf));
				notifyPaymentDtoReq.setPayDateString(DateUtils.date2Str(payOrderInfoModel.getBankTradeTime(), DateUtils.date_sdf));
				notifyPaymentDtoReq.setTotalPayAmount(payOrderInfoModel.getBankAmount());
				notifyPaymentDtoReq.setOrgId(payOrderInfoModel.getOrgId());
				notifyPaymentDtoReq.setEbsRealAccount(payOrderInfoModel.getShopBankAcco());
				
				DtoResult notifydtoResult = payIntegrationService.notifyOrderPaymentByBankcard(notifyPaymentDtoReq);
				if (!ResultCode.SUCCESS.getCode().equals(notifydtoResult.getCode())) {
					logger.error("[PayOrderServiceImpl.payCallback]调用EBS接口同步支付信息错误" + JSON.toJSONString(notifydtoResult));
					addPayLog(payOrderInfoModel, notifydtoResult.getMsg());
					throw new BizException(ResultCode.PAY_EBS_PAY_ERROR);
				}
			} else if(PayTypeEnums.ACC_PAY.name().equals(payOrderInfoModel.getPayType())){
				NotifyRechargeDtoReq notifyRechargeDtoReq = new NotifyRechargeDtoReq();
				notifyRechargeDtoReq.setAccNo(payOrderInfoModel.getBankOrderNo());
				notifyRechargeDtoReq.setCurrency("CNY");
				notifyRechargeDtoReq.setCusNo(payOrderInfoModel.getCusno());
				notifyRechargeDtoReq.setMoneyfeedPayNo(payOrderInfoModel.getPayOrderNo());
				notifyRechargeDtoReq.setPayType(payOrderInfoModel.getPayType());
				notifyRechargeDtoReq.setRechargeAmount(payOrderInfoModel.getBankAmount());
				notifyRechargeDtoReq.setRechargeDate(payOrderInfoModel.getBankTradeTime());
				notifyRechargeDtoReq.setOrgId(payOrderInfoModel.getOrgId());
				notifyRechargeDtoReq.setEbsRealAccount(payOrderInfoModel.getShopBankAcco());

				DtoResult notifydtoResult = payIntegrationService.notifyCustomerRecharge(notifyRechargeDtoReq);
				if (!ResultCode.SUCCESS.getCode().equals(notifydtoResult.getCode())) {
					logger.error("[PayOrderServiceImpl.payCallback]调用EBS接口同步充值 信息错误" + JSON.toJSONString(notifydtoResult));
					addPayLog(payOrderInfoModel, notifydtoResult.getMsg());
					throw new BizException(ResultCode.PAY_EBS_RECHARGE_ERROR);
				}
			}
			else if (PayTypeEnums.SO_PAY.name().equals(payOrderInfoModel.getPayType())) {
				NotifyRechargeDtoReq notifyRechargeDtoReq = new NotifyRechargeDtoReq();
				notifyRechargeDtoReq.setAccNo(payOrderInfoModel.getBankOrderNo());
				notifyRechargeDtoReq.setCurrency("CNY");
				notifyRechargeDtoReq.setCusNo(payOrderInfoModel.getCusno());
				notifyRechargeDtoReq.setEbsOrderNo(orderDtoResult.getEbsorderNo());
				notifyRechargeDtoReq.setMoneyfeedPayNo(payOrderInfoModel.getPayOrderNo());
				notifyRechargeDtoReq.setPayType(payOrderInfoModel.getPayType());
				notifyRechargeDtoReq.setRechargeAmount(payOrderInfoModel.getBankAmount());
				notifyRechargeDtoReq.setRechargeDate(DateUtils.str2Date(
						DateUtils.formatDate(payOrderInfoModel.getBankTradeTime().getTime()), DateUtils.date_sdf));
				notifyRechargeDtoReq.setOrgId(payOrderInfoModel.getOrgId());
				notifyRechargeDtoReq.setEbsRealAccount(payOrderInfoModel.getShopBankAcco());

				DtoResult notifydtoResult = payIntegrationService.notifyCustomerRecharge(notifyRechargeDtoReq);
				if (!ResultCode.SUCCESS.getCode().equals(notifydtoResult.getCode())) {
					logger.error("[PayOrderServiceImpl.payCallback]调用EBS接口同步充值 信息错误" + JSON.toJSONString(notifydtoResult));
					addPayLog(payOrderInfoModel, notifydtoResult.getMsg());
					throw new BizException(ResultCode.PAY_EBS_RECHARGE_ERROR);
				}
			}
		}
		//商城有支付订单，但支付平台不存在，置为无用的订单
		else if(paySpecialDtoReq.getType().equals("2")){
			if (PayStatusEnum.PAY_PROGRESS.name().equals(payOrderInfoModel.getStatus())) {
				PayOrderInfoModel oldModel = new PayOrderInfoModel();
				oldModel.setId(payOrderInfoModel.getId());
				oldModel.setStatus(payOrderInfoModel.getStatus());
				PayOrderInfoModel newModel = new PayOrderInfoModel();
				newModel.setStatus(PayStatusEnum.PAY_USELESS.name());
				newModel.setRemark("手动设置状态为无用");
				update(oldModel, newModel);
			}else {
				dtoResult.setCode(ResultCode.FAIL.getCode());
				dtoResult.setMsg("支付订单状态不为进行中状态");
				return dtoResult;
			}
		}
		//同步到了ebs，但是没改商城的同步状态
		else if(paySpecialDtoReq.getType().equals("3")
				|| paySpecialDtoReq.getType().equals("4")){
			ReciveNotifyEbsPayDtoReq dtoReq = new ReciveNotifyEbsPayDtoReq();
			dtoReq.setEbsorderNo(orderDtoResult.getEbsorderNo());
			dtoReq.setMoneyfeedPayNo(payOrderInfoModel.getPayOrderNo());
			dtoReq.setOrgId(payOrderInfoModel.getOrgId());
			dtoReq.setPayType(payOrderInfoModel.getPayType());
			if(paySpecialDtoReq.getType().equals("3")){
				dtoReq.setPayFlag(true);
				dtoReq.setMsg("手工置为成功");
			}else if(paySpecialDtoReq.getType().equals("4")){
				dtoReq.setPayFlag(false);
				dtoReq.setMsg("手工置为失败");
			}
			payIntegrationService.reciveNotifyEbsPay(dtoReq);
		}
		
		return dtoResult.success();
	}

	@Override
	public PayOrderListDtoResult queryPayOrderList(PayOrderDtoReq payOrderDtoReq) {
		PayOrderListDtoResult payOrderListDtoResult = new PayOrderListDtoResult();
		PayOrderInfoModel payOrderInfoModel = new PayOrderInfoModel();
		payOrderInfoModel.setPayOrderNo(payOrderDtoReq.getPayOrderNo());
		List<PayOrderInfoModel> list = query(payOrderInfoModel);
		List<PayOrderDtoResult> payOrderDtoResults = new ArrayList<>();
		for (PayOrderInfoModel payOrderInfoModel2 : list) {
			PayOrderDtoResult payOrderDtoResult = new PayOrderDtoResult();
			BeanUtils.copyProperties(payOrderInfoModel2, payOrderDtoResult);
			payOrderDtoResults.add(payOrderDtoResult);
		}
		payOrderListDtoResult.setPayOrderDtoResult(payOrderDtoResults);
		payOrderListDtoResult.setCode(ResultCode.SUCCESS.getCode());
		return payOrderListDtoResult;
	}

}
