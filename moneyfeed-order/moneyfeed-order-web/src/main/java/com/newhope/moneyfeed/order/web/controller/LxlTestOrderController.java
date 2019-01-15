package com.newhope.moneyfeed.order.web.controller;

import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.integration.api.exbean.common.SendMQCommonModel;
import com.newhope.moneyfeed.order.api.dto.request.integration.RepositoryLowDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.pay.PayResultDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderValidateRepositoryDtoResult;
import com.newhope.moneyfeed.order.api.enums.PayTypeEnums;
import com.newhope.moneyfeed.order.api.rest.LxlTestOrderAPI;
import com.newhope.moneyfeed.pay.api.dto.req.integration.ReciveNotifyEbsPayDtoReq;
import com.newhope.order.biz.rpc.feign.pay.PayIntegrationFeignClient;
import com.newhope.order.biz.service.OrderIntegrationService;
import com.newhope.order.biz.service.OrderService;

@RestController
public class LxlTestOrderController extends AbstractController implements LxlTestOrderAPI {

	private static Logger logger = LoggerFactory.getLogger(LxlTestOrderController.class);

	@Autowired
	OrderIntegrationService orderIntegrationService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	PayIntegrationFeignClient payIntegrationFeignClient;
	
	public static void main(String[] args) {
		long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long seven=zero+7*60*60*1000-1;//今天7点59分59秒的毫秒数
        if(current > seven){
        	System.out.println(">>>>>");
        }
	}
	
	@Override
	public DtoResult test1() {
		logger.info("lxl>>>>>>>>>>>测试开始");
//		CancelOrderToEbsDtoReq cancelOrderToEbsDtoReq = new CancelOrderToEbsDtoReq();
//		cancelOrderToEbsDtoReq.setCompanyShortCode("123456");
//		cancelOrderToEbsDtoReq.setEbsOrderNo("EBS123456");
////		DtoResult dtoResult = orderIntegrationService.sendCancelOrderToEbs(cancelOrderToEbsDtoReq);
//		
//		orderIntegrationService.pullOrderInfo();
//		OrderInfoQueryDtoReq req = new OrderInfoQueryDtoReq();
//		PageList<OrderInfoDtoResult> dtoResult = orderService.queryOrderInfoList(req);
//		logger.info(">>>>>>>>>>>>>go"+JSON.toJSONString(dtoResult));
		
//		TransportInfoToEbsDtoReq transportInfoToEbsDtoReq = new TransportInfoToEbsDtoReq();
//		transportInfoToEbsDtoReq.setCarNo("川测77774");
//		transportInfoToEbsDtoReq.setOrderFeedTransportId(236L);
//		transportInfoToEbsDtoReq.setOrderId(306L);
//		transportInfoToEbsDtoReq.setPlanTransportTime(DateUtil.addMinute(new Date(), 1440));
//		orderIntegrationService.sendTransportInfoToEbs(transportInfoToEbsDtoReq);
//		orderIntegrationService.pullOrderEventInfo();
//		orderIntegrationService.pullEbsOrderInfo();
		
//		String s = "{\"carNo\":\"罗1234\",\"companyOrgId\":\"606\",\"companyShortCode\":\"606\",\"ebsOrderNo\":\"114980045652\",\"ebsOrderStatus\":\"ENTERED\",\"materials\":[{\"factor\": 1,\"discountAmount\":6.0000,\"itemType\":\"NORMAL_PRODUCT\",\"materielNo\":\"1502005706\",\"orderId\":323,\"orgId\":\"606\",\"planAmount\":6.0000,\"quantity\":30.00,\"realPayAmount\":240.0000,\"unit\":\"kg\"},{\"factor\":40,\"discountAmount\":6.0000,\"itemType\":\"NORMAL_PRODUCT\",\"materielNo\":\"1502002372\",\"orderId\":323,\"orgId\":\"606\",\"planAmount\":6.0000,\"quantity\":40.00,\"realPayAmount\":240.0000,\"unit\":\"kg\"}],\"moneyfeedOrderId\":\"339\",\"planTransportTime\":1544284800000}";
//		s = "{\"carNo\":\"\",\"ebsOrderNo\":\"114980045326\",\"ebsOrderStatus\":\"ENTERED\",\"lastUpdateTime\":1543542058043,\"materials\":[{\"discountAmount\":4.0000,\"factor\":40.00,\"itemType\":\"NORMAL_PRODUCT\",\"materielNo\":\"1502002372\",\"orderId\":347,\"orgId\":\"606\",\"planAmount\":4.4200,\"quantity\":40.00,\"realPayAmount\":160.0000,\"unit\":\"kg\"}],\"moneyfeedOrderId\":339,\"planTransportTime\":1543507200000}";
		
//		IntegrationTimeInfoDtoReq integrationTimeInfoDtoReq = JSON.parseObject(s, IntegrationTimeInfoDtoReq.class);
//		orderIntegrationService.reciveIntegrationOrderInfo(integrationTimeInfoDtoReq);
//		String s = "{\"data\":{\"ebsorderNo\":\"\",\"moneyfeedPayNo\":\"841305001012\",\"msg\":\"\",\"orgId\":\"606\",\"payFlag\":true,\"payType\":\"ACC_PAY\"},\"mqMsgKind\":\"recharge\",\"mqSendToClient\":\"order\"}";
//		
//		SendMQCommonModel sendMQCommonModel =JSON.parseObject(s, SendMQCommonModel.class);
//		PayResultDtoReq payResultDtoReq = JSON.toJavaObject((JSONObject)sendMQCommonModel.getData(), PayResultDtoReq.class);
//		
//		//ebs支付
//		if (PayTypeEnums.BAL_PAY.name().equals(payResultDtoReq.getPayType())) {
//			orderIntegrationService.receiveOrderPayInfo(payResultDtoReq);
//		}
//		//银行卡支付
//		else if (PayTypeEnums.CARD_PAY.name().equals(payResultDtoReq.getPayType())) {
//			ReciveNotifyEbsPayDtoReq dtoReq = new ReciveNotifyEbsPayDtoReq();
//			BeanUtils.copyProperties(payResultDtoReq, dtoReq);
//			payIntegrationFeignClient.reciveNotifyEbsPay(dtoReq);
//		}
//		//订单充值
//		else if (PayTypeEnums.SO_PAY.name().equals(payResultDtoReq.getPayType())) {
//			ReciveNotifyEbsPayDtoReq dtoReq = new ReciveNotifyEbsPayDtoReq();
//			BeanUtils.copyProperties(payResultDtoReq, dtoReq);
//			payIntegrationFeignClient.reciveNotifyEbsPay(dtoReq);
//		}
//		//账户充值
//		else if (PayTypeEnums.ACC_PAY.name().equals(payResultDtoReq.getPayType())) {
//			ReciveNotifyEbsPayDtoReq dtoReq = new ReciveNotifyEbsPayDtoReq();
//			BeanUtils.copyProperties(payResultDtoReq, dtoReq);
//			payIntegrationFeignClient.reciveNotifyEbsPay(dtoReq);
//		}else {
//			logger.error("--支付返回结果未找到匹配类型---");
//		}
		
//		OrderValidateRepositoryDtoResult orderValidateRepositoryDtoResult = orderIntegrationService.validateRepository(470L);
//		logger.info(JSON.toJSONString(orderValidateRepositoryDtoResult));
//		RepositoryLowDtoReq repositoryLowDtoReq = new RepositoryLowDtoReq();
//		repositoryLowDtoReq.setBeforeRepositoryLowFlag(false);
//		repositoryLowDtoReq.setMoneyfeedOrderId(499L);
//		repositoryLowDtoReq.setRepositoryLowFlag(true);
//		orderIntegrationService.reciveRepositoryLow(repositoryLowDtoReq);
		
		orderIntegrationService.pullOrderEventInfo();
		logger.info("lxl>>>>>>>>>>>测试结束");
		return null;
	}
}
