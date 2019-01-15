package com.newhope.pay.biz.service.impl;

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
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.dto.uclog.UserOperationLogDtoReq;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.enums.UserOperEventType;
import com.newhope.moneyfeed.api.enums.UserOperSourceType;
import com.newhope.moneyfeed.api.enums.UserOperationEnums;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderPayInBankCardTypeDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderRechargeDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoModifyDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.OrderDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.OrderListDtoResult;
import com.newhope.moneyfeed.order.api.enums.OrderStatusEnum;
import com.newhope.moneyfeed.order.api.enums.PayTypeEnums;
import com.newhope.moneyfeed.pay.api.bean.PayOrderInfoModel;
import com.newhope.moneyfeed.pay.api.bean.PayOrderLogModel;
import com.newhope.moneyfeed.pay.api.dto.req.integration.NotifyPaymentDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.integration.NotifyRechargeDtoReq;
import com.newhope.moneyfeed.pay.api.dto.req.integration.ReciveNotifyEbsPayDtoReq;
import com.newhope.moneyfeed.pay.api.enums.EbsPayStatusEnum;
import com.newhope.moneyfeed.pay.api.enums.PayStatusEnum;
import com.newhope.moneyfeed.pay.dal.dao.PayOrderInfoDao;
import com.newhope.moneyfeed.pay.dal.dao.PayOrderLogDao;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerAccountStatementPostDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerAccountStatementPostListDtoReq;
import com.newhope.pay.biz.rpc.feign.CustomerAccountFeignClient;
import com.newhope.pay.biz.rpc.feign.MoneyfeedToEbsOrderFeignClient;
import com.newhope.pay.biz.rpc.feign.OrderFeignClient;
import com.newhope.pay.biz.rpc.feign.UserOperationFeignClient;
import com.newhope.pay.biz.service.PayIntegrationService;

/**
 * @author bena.peng
 * @date 2018/12/18 10:58
 */

@Service
public class PayIntegrationServiceImpl implements PayIntegrationService {
    private final Logger logger = LoggerFactory.getLogger(PayIntegrationServiceImpl.class);
    @Autowired
    OrderFeignClient orderFeignClient;

    @Autowired
    MoneyfeedToEbsOrderFeignClient ebsClient;

    @Autowired
	PayOrderInfoDao payOrderInfoDao;
	
	@Autowired
	PayOrderLogDao payOrderLogDao;
	
	@Autowired
	UserOperationFeignClient userOperationFeignClient;
	
	@Autowired
	CustomerAccountFeignClient customerAccountFeignClient;
	
    @Override
    public DtoResult notifyOrderPaymentByBankcard(NotifyPaymentDtoReq dtoReq) {
        MoneyfeedToEbsOrderPayInBankCardTypeDtoReq ebsReq = new MoneyfeedToEbsOrderPayInBankCardTypeDtoReq();
        ebsReq.setAccNo(dtoReq.getAccNo());
        ebsReq.setCurrency(dtoReq.getCurrency());
        ebsReq.setCusNo(dtoReq.getCusNo());
        ebsReq.setEbsOrderNo(dtoReq.getEbsOrderNo());
        ebsReq.setEbsRealAccount(dtoReq.getEbsRealAccount());
        ebsReq.setMoneyfeedPayNo(dtoReq.getMoneyfeedPayNo());
        ebsReq.setOrgId(dtoReq.getOrgId());
        ebsReq.setPayDateString(dtoReq.getPayDateString());
        ebsReq.setTotalPayAmount(dtoReq.getTotalPayAmount());
        
    	logger.info("[PayIntegrationServiceImpl.notifyOrderPaymentByBankcard]通知EBS支付成功的参数" + JSON.toJSONString(ebsReq));
        BaseResponse<DtoResult> response = ebsClient.moneyfeedToEbsOrderPayInBankCardType(ebsReq);
    	logger.info("[PayIntegrationServiceImpl.notifyOrderPaymentByBankcard]EBS支付的返回结果" + JSON.toJSONString(response));
        return new DtoResult(response.getCode(), response.getMsg());
    }
    
    @Override
    public DtoResult notifyCustomerRecharge(NotifyRechargeDtoReq dtoReq) {
    	MoneyfeedToEbsOrderRechargeDtoReq orderRechargeDtoReq = new MoneyfeedToEbsOrderRechargeDtoReq();
    	orderRechargeDtoReq.setAccNo(dtoReq.getAccNo());
    	orderRechargeDtoReq.setCurrency(dtoReq.getCurrency());
    	orderRechargeDtoReq.setCusNo(dtoReq.getCusNo());
    	orderRechargeDtoReq.setEbsOrderNo(dtoReq.getEbsOrderNo());
    	orderRechargeDtoReq.setMoneyfeedPayNo(dtoReq.getMoneyfeedPayNo());
    	orderRechargeDtoReq.setPayType(dtoReq.getPayType());
    	orderRechargeDtoReq.setRechargeAmount(dtoReq.getRechargeAmount());
    	orderRechargeDtoReq.setRechargeDate(new Date());
    	orderRechargeDtoReq.setOrgId(dtoReq.getOrgId());
    	orderRechargeDtoReq.setEbsRealAccount(dtoReq.getEbsRealAccount());
    	
    	logger.info("[PayIntegrationServiceImpl.notifyCustomerRecharge]通知EBS充值 成功的参数" + JSON.toJSONString(orderRechargeDtoReq));
    	BaseResponse<DtoResult> response =  ebsClient.moneyfeedToEbsOrderRecharge(orderRechargeDtoReq);
    	logger.info("[PayIntegrationServiceImpl.notifyCustomerRecharge]EBS充值的返回结果" + JSON.toJSONString(response));
    	return new DtoResult(response.getCode(), response.getMsg());
    }

	@Override
	@Transactional
	public DtoResult reciveNotifyEbsPay(ReciveNotifyEbsPayDtoReq dtoReq) {
		DtoResult dtoResult= new DtoResult();
		if(StringUtils.isEmpty(dtoReq.getMoneyfeedPayNo())){
			dtoResult.setCode(ResultCode.FAIL.getCode());
			dtoResult.setMsg(ResultCode.FAIL.getDesc());
			return dtoResult;
		}
		logger.info("[PayIntegrationServiceImpl.reciveNotifyEbsPay]接收中台回调的充值，支付结果" + JSON.toJSONString(dtoReq));
		
		//查询支付订单
		PayOrderInfoModel payOrderInfoModel = new PayOrderInfoModel();
		payOrderInfoModel.setPayOrderNo(dtoReq.getMoneyfeedPayNo());
		List<PayOrderInfoModel> payOrderInfoModels = payOrderInfoDao.select(payOrderInfoModel);
		if(CollectionUtils.isEmpty(payOrderInfoModels)){
			dtoResult.setCode(ResultCode.FAIL.getCode());
			dtoResult.setMsg(ResultCode.FAIL.getDesc());
			return dtoResult;
		}
		payOrderInfoModel = payOrderInfoModels.get(0);
		
		//接收到请求后更新支付订单
		PayOrderInfoModel oldModel = new PayOrderInfoModel();
		oldModel.setId(payOrderInfoModel.getId());
		if(PayTypeEnums.BAL_PAY.name().equals(dtoReq.getPayType())){
			oldModel.setStatus(PayStatusEnum.PAY_PROGRESS.name());
		}else{
			oldModel.setStatus(PayStatusEnum.PAY_SUCESS.name());
		}
		oldModel.setPayOrderNo(payOrderInfoModel.getPayOrderNo());
		if(payOrderInfoModel.getEbsStatus().equals(EbsPayStatusEnum.EBS_PAY_FAIL.name())){
			oldModel.setEbsStatus(EbsPayStatusEnum.EBS_PAY_FAIL.name());
		}else{
			oldModel.setEbsStatus(EbsPayStatusEnum.EBS_PAY_PROGRESS.name());
		}
		
		PayOrderInfoModel newModel = new PayOrderInfoModel();
		
		if(dtoReq.getPayFlag()){
			newModel.setEbsStatus(EbsPayStatusEnum.EBS_PAY_SUCESS.name());
			if(PayTypeEnums.BAL_PAY.name().equals(dtoReq.getPayType())){
				newModel.setStatus(PayStatusEnum.PAY_SUCESS.name());
			}
			
			if(PayTypeEnums.CARD_PAY.name().equals(payOrderInfoModel.getPayType())){
				//查询商城订单
				OrderInfoQueryDtoReq req = new OrderInfoQueryDtoReq();
				req.setOrderNo(payOrderInfoModel.getOrderNo());
				BaseResponse<OrderListDtoResult> feginOrderListDtoResult = orderFeignClient.queryOrderInfoList(req);
				if (!feginOrderListDtoResult.isSuccess() || CollectionUtils.isEmpty(feginOrderListDtoResult.getData().getOrderList())) {
					logger.error("[PayOrderServiceImpl.reciveNotifyEbsPay]EBS消息中商城订单不存在" + JSON.toJSONString(dtoReq));
					dtoResult.setCode(ResultCode.OC_ORDER_NOT_EXIST.getCode());
					dtoResult.setMsg(ResultCode.OC_ORDER_NOT_EXIST.getDesc());
					return dtoResult;
				}
				OrderDtoResult orderDtoResult = feginOrderListDtoResult.getData().getOrderList().get(0);
				
				//更新商城订单
				OrderInfoModifyDtoReq requestBody = new OrderInfoModifyDtoReq();
				requestBody.setOrderId(orderDtoResult.getId());
				requestBody.setOldStatus(OrderStatusEnum.PAID.name());
				requestBody.setStatus(OrderStatusEnum.WAITING_PULL_MATERIAL.name());
				
				BaseResponse<DtoResult> orderFeginDtoResult = orderFeignClient.updateOrderInfo(requestBody);
				if (!orderFeginDtoResult.isSuccess()) {
					logger.error("[PayOrderServiceImpl.reciveNotifyEbsPay]修改商城订单不成功" + JSON.toJSONString(requestBody));
					throw new BizException(ResultCode.OC_ORDER_NOT_UPDATE_ERROR);
				}
			}
		}else{
			newModel.setEbsStatus(EbsPayStatusEnum.EBS_PAY_FAIL.name());
		}
		
		long i = payOrderInfoDao.update(oldModel, newModel);
		addPayLog(payOrderInfoModel, JSON.toJSONString(dtoReq));
		addUcLog(payOrderInfoModel);
		if(i > 0){
			//如果是余额支付成功，记录用户流水日志
			if (PayTypeEnums.BAL_PAY.name().equals(dtoReq.getPayType()) && dtoReq.getPayFlag()) {
				CustomerAccountStatementPostListDtoReq customerAccountStatementPostListDtoReq = new CustomerAccountStatementPostListDtoReq();
				List<CustomerAccountStatementPostDtoReq> dataList = new ArrayList<>();
				CustomerAccountStatementPostDtoReq cstomerAccountStatementPostDtoReq = new CustomerAccountStatementPostDtoReq();
				cstomerAccountStatementPostDtoReq.setAmount(payOrderInfoModel.getAmount());
				cstomerAccountStatementPostDtoReq.setBizType(payOrderInfoModel.getPayType());
				cstomerAccountStatementPostDtoReq.setClientUserId(payOrderInfoModel.getUserId());
				cstomerAccountStatementPostDtoReq.setOrderNo(payOrderInfoModel.getOrderNo());
				cstomerAccountStatementPostDtoReq.setSerialNumber(payOrderInfoModel.getBankOrderNo());
				cstomerAccountStatementPostDtoReq.setPayOrderNo(payOrderInfoModel.getPayOrderNo());
				cstomerAccountStatementPostDtoReq.setShopId(payOrderInfoModel.getShopId());
				dataList.add(cstomerAccountStatementPostDtoReq);
				customerAccountStatementPostListDtoReq.setDataList(dataList);
				BaseResponse<DtoResult> cusDtoResult = customerAccountFeignClient.saveCustomerAccountStatement(customerAccountStatementPostListDtoReq);
				if (!cusDtoResult.isSuccess()) {
					logger.error("[PayOrderServiceImpl.reciveNotifyEbsPay]插入支付订单日志失败");
					//throw new BizException(ResultCode.PAY_LOG_ERROR);
				}
			}
			return dtoResult.success();
		}else{
			throw new BizException(ResultCode.FAIL);
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
	private void addUcLog(PayOrderInfoModel oldOrderInfoModel) {
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
		userOperationLogDtoReq.setComment("EBS返回支付后结果");
		userOperationLogDtoReq.setSource(UserOperSourceType.PAY_CENTER);
		BaseResponse<DtoResult> operaResp = userOperationFeignClient.recordingUserOperation(userOperationLogDtoReq);
		if (!operaResp.isSuccess()) {
			logger.error("[PayOrderServiceImpl.addUcLog]插入支付订单统一日志失败");
			throw new BizException(ResultCode.PAY_LOG_ERROR);
		}
	}
}


