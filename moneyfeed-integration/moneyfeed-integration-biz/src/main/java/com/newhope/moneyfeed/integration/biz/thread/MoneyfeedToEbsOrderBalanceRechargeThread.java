package com.newhope.moneyfeed.integration.biz.thread;

import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.newhope.commons.lang.DateUtils;
import com.newhope.moneyfeed.api.enums.UserOperEventType;
import com.newhope.moneyfeed.api.enums.UserOperSourceType;
import com.newhope.moneyfeed.api.enums.UserOperationEnums;
import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsRechargeModel;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderRechargeDtoReq;
import com.newhope.moneyfeed.integration.api.enums.common.MQMsgKindEnum;
import com.newhope.moneyfeed.integration.api.enums.order.EbsOrderLogTypeEnum;
import com.newhope.moneyfeed.integration.api.enums.order.MoneyfeedToEbsOperationStatusEnum;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderExModel;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs.EbsOrderPayOrChargeReq;
import com.newhope.moneyfeed.integration.biz.service.common.CommonService;
import com.newhope.moneyfeed.integration.biz.service.common.TransferWithEbsServiceImpl;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.EbsOrderLogServiceImpl;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.EbsOrderServiceImpl;
import com.newhope.moneyfeed.integration.biz.service.ebs.order.EbsRechargeServiceImpl;
import com.newhope.moneyfeed.order.api.enums.PayTypeEnums;

/**
 * Created by liuyc on 2018/12/18
 */
public class MoneyfeedToEbsOrderBalanceRechargeThread implements Runnable {

    private Logger logger = LoggerFactory.getLogger(MoneyfeedToEbsOrderBalanceRechargeThread.class);

    private MoneyfeedToEbsOrderRechargeDtoReq orderRechargeDtoReq;
    
    private String key;

    public MoneyfeedToEbsOrderBalanceRechargeThread(MoneyfeedToEbsOrderRechargeDtoReq orderRechargeDtoReq , String key) {
        this.orderRechargeDtoReq = orderRechargeDtoReq;
        this.key = key;
    }

    @Override
    public void run() {
    	
    	logger.info("开始向EBS发送充值信息, EBS充值类型：" + orderRechargeDtoReq.getPayType() + " , EBS订单编码："
    			+ orderRechargeDtoReq.getEbsOrderNo() == null ? "" : orderRechargeDtoReq.getEbsOrderNo() + " ,"
    					+ "  商城充值流水号: " + orderRechargeDtoReq.getMoneyfeedPayNo() + "，orgId：" + orderRechargeDtoReq.getOrgId() + " ，客户编号："+ orderRechargeDtoReq.getCusNo()
	                + " ，充值金额：" + orderRechargeDtoReq.getRechargeAmount());
    	
    	final EbsOrderServiceImpl ebsOrderService = AppContext.getSpringContext().getBean(EbsOrderServiceImpl.class);
    	final EbsRechargeServiceImpl ebsOrderRechargeService = AppContext.getSpringContext().getBean(EbsRechargeServiceImpl.class);
    	final EbsOrderLogServiceImpl ebsOrderLogService = AppContext.getSpringContext().getBean(EbsOrderLogServiceImpl.class);
    	final TransferWithEbsServiceImpl transferWithEbsService = AppContext.getSpringContext().getBean(TransferWithEbsServiceImpl.class);
    	final CommonService commonService = AppContext.getSpringContext().getBean(CommonService.class);
    	try{
	
	        EbsOrderExModel ebsOrderWithDetail = ebsOrderService.getEbsOrderWithDetailByEbsOrderNo(orderRechargeDtoReq.getEbsOrderNo());
	        
	        List<EbsRechargeModel> ebsRechargeModelList =
	        		ebsOrderRechargeService.queryEbsRechargeModelByMoneyfeedPayNo(orderRechargeDtoReq.getMoneyfeedPayNo());
	        String status = null;
	        EbsRechargeModel ebsRechargeModel = null;
	        if(!CollectionUtils.isEmpty(ebsRechargeModelList)){
	        	ebsRechargeModel = ebsRechargeModelList.get(0);
	        	status = ebsRechargeModel.getPayStatus();
	        }else{
	        	logger.error("根据商城流水号查询充值记录表为空：" + orderRechargeDtoReq.getMoneyfeedPayNo());
	        	return;
	        }
	        
	        // 记录日志
	        commonService.saveFulllog(orderRechargeDtoReq.getRechargeAmount(),
	        		orderRechargeDtoReq.getRechargeAmount(),
	        		status,
	        		status,
	        		ebsRechargeModel,
	        		ebsRechargeModel,
	                orderRechargeDtoReq.getEbsOrderNo(),
	                new Date(),
	                ebsRechargeModel.getId(),
	                null,
	                Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
	                UserOperEventType.RECHARGE,
	                UserOperationEnums.INT_RECEIVE_RECHARGE_OF_SHOP,
	                "收到商城订单充值的请求，, EBS订单编码："
	            			+ orderRechargeDtoReq.getEbsOrderNo()+" , 商城充值流水号: " + orderRechargeDtoReq.getMoneyfeedPayNo() + "，orgId：" + orderRechargeDtoReq.getOrgId() + 
	            			" ，客户编号："+ orderRechargeDtoReq.getCusNo()
	                + " ，充值金额：" + orderRechargeDtoReq.getRechargeAmount(),
	                UserOperSourceType.MEDIUM_CENTER);
	
	        // 发送EBS，通知EBS余额支付
	        MoneyfeedToEbsOperationStatusEnum result = null;
	
	        try {
	            EbsOrderPayOrChargeReq req = converseDtoReqToEbsReq(orderRechargeDtoReq, ebsOrderWithDetail);
	            
	            transferWithEbsService.sendEbsOrderPayOrChargeRequest(req);
	
	            // 记录日志
	            ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderWithDetail.getId(),
	                    ebsOrderWithDetail.getSaleOrderId(),
	                    EbsOrderLogTypeEnum.TO_EBS_RECHARGE_REQUEST.name(),
	                    new Date(),
	                    JSON.toJSONString(orderRechargeDtoReq),
	                    StringUtils.EMPTY,
	                    0,
	                    (byte) 1);
	
	            result = MoneyfeedToEbsOperationStatusEnum.OPERATING;
	        } catch (Exception e) {
	            CommonService.formatExceptionMsg(MoneyfeedToEbsOrderBalanceRechargeThread.class, e);
	
	            logger.error("请求ebs订单充值失败，商城订单编号：" + ebsOrderWithDetail.getEbsOrderNo() + 
	            		" ， 商城充值流水号: " + orderRechargeDtoReq.getMoneyfeedPayNo() + "，orgId：" + orderRechargeDtoReq.getOrgId() + 
	            		" ，客户编号："+ orderRechargeDtoReq.getCusNo()
		                + " ，充值金额：" + orderRechargeDtoReq.getRechargeAmount() + " , 原因: " + e.getMessage());
	
	            if (e.getCause() != null && e.getCause() instanceof ConnectTimeoutException) {
	                // 记录错误日志
	                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderWithDetail.getId(),
	                        ebsOrderWithDetail.getSaleOrderId(),
	                        EbsOrderLogTypeEnum.TO_EBS_RECHARGE_REQUEST.name(),
	                        new Date(),
	                        JSON.toJSONString(orderRechargeDtoReq),
	                        e.getMessage(),
	                        1,
	                        (byte) 0);
	
	                // 通知商城，订单因为网络问题，不能创建
	                logger.error("网络连接EBS系统失败, 订单充值失败, 商城订单编号 " + ebsOrderWithDetail.getEbsOrderNo()
	                + " ， 商城充值流水号: " + orderRechargeDtoReq.getMoneyfeedPayNo() + "，orgId：" + orderRechargeDtoReq.getOrgId() + 
	                " ，客户编号："+ orderRechargeDtoReq.getCusNo()
	                + " ，充值金额：" + orderRechargeDtoReq.getRechargeAmount());
	                
					// TODO 调用ebs中心失败时，在后续的定时任务中，根据日志类型进行操作，发送MQ消息(通知商城)
	        		commonService.sendToOrderPayOrChargeMqResult(orderRechargeDtoReq.getEbsOrderNo(),
	        				ebsRechargeModel.getPayType(),
	        				false,
	        				"网络连接EBS系统失败, 订单充值失败, 商城订单编号 " + ebsOrderWithDetail.getEbsOrderNo(),
	        				orderRechargeDtoReq.getOrgId(),
	        				orderRechargeDtoReq.getMoneyfeedPayNo(),
	        				MQMsgKindEnum.recharge);
	                
	                
	                result = MoneyfeedToEbsOperationStatusEnum.INVOKE_EBS_FAIL;
	            } else if (e.getCause() != null && e.getCause() instanceof SocketTimeoutException) {
	                // 请求EBS返回结果超时，在定时任务中请求创建结果即可
	
	                // 记录成功的日志
	                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderWithDetail.getId(),
	                        ebsOrderWithDetail.getSaleOrderId(),
	                        EbsOrderLogTypeEnum.TO_EBS_RECHARGE_REQUEST.name(),
	                        new Date(),
	                        JSON.toJSONString(orderRechargeDtoReq),
	                        e.getMessage(),
	                        0,
	                        (byte) 1);
	
	                result = MoneyfeedToEbsOperationStatusEnum.OPERATING;
	            } else {
	                // 记录日志
	                ebsOrderLogService.saveEbsOrderLogWithNoRollback(ebsOrderWithDetail.getId(),
	                        ebsOrderWithDetail.getSaleOrderId(),
	                        EbsOrderLogTypeEnum.TO_EBS_RECHARGE_REQUEST.name(),
	                        new Date(),
	                        JSON.toJSONString(orderRechargeDtoReq),
	                        e.getMessage(),
	                        1,
	                        (byte) 0);
	
	                // TODO 调用ebs中心失败时，在后续的定时任务中，根据日志类型进行操作，发送MQ消息(通知商城)
	        		commonService.sendToOrderPayOrChargeMqResult(orderRechargeDtoReq.getEbsOrderNo(),
	        				ebsRechargeModel.getPayType(),
	        				false,
	        				"请求EBS系统异常, 订单充值失败, 商城订单编号 " + ebsOrderWithDetail.getEbsOrderNo(),
	        				orderRechargeDtoReq.getOrgId(),
	        				orderRechargeDtoReq.getMoneyfeedPayNo(),
	        				MQMsgKindEnum.recharge);
	                
	                result = MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_FAIL;
	            }
	
	        }
	        
	        // 根据请求EBS的结果result，进行业务处理，如果请求EBS成功，则记录一条充值记录，如果失败，就不记录充值记录
	        String beforeStatus = null;
	        EbsRechargeModel newModel = null;
	        if(ebsRechargeModel != null && ebsRechargeModel.getId() != null){
	        	beforeStatus = ebsRechargeModel.getPayStatus();
	        	newModel = new EbsRechargeModel();
	        	newModel.setPayStatus(result.name());
				ebsOrderRechargeService.update(ebsRechargeModel, newModel);
	        }
	
	        // 记录日志
	        commonService.saveFulllog(orderRechargeDtoReq.getRechargeAmount(),
	        		orderRechargeDtoReq.getRechargeAmount(),
	        		beforeStatus,
	                result.name(),
	                ebsRechargeModel,
	                newModel,
	                orderRechargeDtoReq.getEbsOrderNo(),
	                new Date(),
	                ebsRechargeModel.getId(),
	                null,
	                Long.valueOf(ebsOrderWithDetail.getSaleOrderId()),
	                UserOperEventType.RECHARGE,
	                UserOperationEnums.INT_SEND_RECHARGE_INFO_TO_EBS,
	                "请求EBS订单充值，商城ebs编号：" + ebsOrderWithDetail.getSaleOrderId() + 
	                " ， 商城充值流水号: " + orderRechargeDtoReq.getMoneyfeedPayNo() + "，orgId：" + orderRechargeDtoReq.getOrgId() + 
	                " ，客户编号："+ orderRechargeDtoReq.getCusNo()
	                + " ，充值金额：" + orderRechargeDtoReq.getRechargeAmount(),
	                UserOperSourceType.MEDIUM_CENTER);
    	}finally{
    		//移除锁
    		commonService.removeLock(key);
    	}
    }

    private EbsOrderPayOrChargeReq converseDtoReqToEbsReq(MoneyfeedToEbsOrderRechargeDtoReq orderRechargeDtoReq, EbsOrderExModel ebsOrder) {
        EbsOrderPayOrChargeReq req = new EbsOrderPayOrChargeReq();

        if (null == orderRechargeDtoReq) {
            return req;
        }
        
        String date2Str = DateUtils.date2Str(orderRechargeDtoReq.getRechargeDate() , DateUtils.date_sdf);

        req.setMoneyfeedOrderId(ebsOrder.getSaleOrderId());
        req.setEbsOrderNo(orderRechargeDtoReq.getEbsOrderNo());
        req.setCurrency(orderRechargeDtoReq.getCurrency());
        req.setPayOrChargeType(PayTypeEnums.SO_PAY.name()); // 余额充值
        req.setPayDateString(date2Str);
//        req.setTotalBalanceAmount(balanceTypeDtoReq.getTotalBalanceAmount());
//        req.setDiscountAmount(discountAmount);
        req.setTotalBankAmount(orderRechargeDtoReq.getRechargeAmount());
        req.setMoneyfeedPayNo(orderRechargeDtoReq.getMoneyfeedPayNo());
        req.setAccNo(orderRechargeDtoReq.getAccNo());
        req.setCustomerNo(orderRechargeDtoReq.getCusNo()); //客户编号
        req.setOrgId(orderRechargeDtoReq.getOrgId());
        req.setCollectionMethod(orderRechargeDtoReq.getEbsRealAccount());
        
        return req;
    }

}
