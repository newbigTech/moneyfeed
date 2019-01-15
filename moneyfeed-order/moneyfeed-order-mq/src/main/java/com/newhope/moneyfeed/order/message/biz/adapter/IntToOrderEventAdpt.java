package com.newhope.moneyfeed.order.message.biz.adapter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newhope.moneyfeed.integration.api.enums.common.MQMsgKindEnum;
import com.newhope.moneyfeed.integration.api.exbean.common.SendMQCommonModel;
import com.newhope.moneyfeed.mq.adapter.Channel;
import com.newhope.moneyfeed.mq.adapter.Message;
import com.newhope.moneyfeed.mq.adapter.event.CommonEvent;
import com.newhope.moneyfeed.mq.adapter.event.EventType;
import com.newhope.moneyfeed.mq.adapter.impl.DefaultMessageAdapter;
import com.newhope.moneyfeed.mq.exception.MessageProcessException;
import com.newhope.moneyfeed.order.api.bean.IntegrationLogModel;
import com.newhope.moneyfeed.order.api.dto.request.integration.RepositoryLowDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.pay.PayResultDtoReq;
import com.newhope.moneyfeed.order.api.enums.PayTypeEnums;
import com.newhope.moneyfeed.pay.api.dto.req.integration.ReciveNotifyEbsPayDtoReq;
import com.newhope.order.biz.rpc.feign.pay.PayIntegrationFeignClient;
import com.newhope.order.biz.service.IntegrationLogService;
import com.newhope.order.biz.service.OrderIntegrationService;

/**
 * Event Adapter: 应用消费者处理器
 * @author RDC-201
 *
 */
@Component
public class IntToOrderEventAdpt extends DefaultMessageAdapter {
	public final  static Logger logger = LoggerFactory.getLogger(IntToOrderEventAdpt.class);

	@Autowired
	private OrderIntegrationService orderIntegrationService;
	
	@Autowired
	private IntegrationLogService integrationLogService;
	
	public IntToOrderEventAdpt() {
		super(Channel.Type.Common);
	}

	@Override
	public boolean match(EventType eventType) {
        return EventType.INT_TO_ORDER_EVENT.name().equals(eventType.name());
    }
	
	@Override
	public void eventHandle(Message message, CommonEvent event) throws MessageProcessException {
		
		logger.info("--接受到中台到order的消息---" + JSON.toJSONString(message));
		
		SendMQCommonModel sendMQCommonModel =JSON.parseObject(message.getContent(), SendMQCommonModel.class);
		//支付、充值消息
		if (MQMsgKindEnum.pay.equals(sendMQCommonModel.getMqMsgKind())
				|| MQMsgKindEnum.recharge.equals(sendMQCommonModel.getMqMsgKind())) {
			PayResultDtoReq payResultDtoReq = JSON.toJavaObject((JSONObject)sendMQCommonModel.getData(), PayResultDtoReq.class);
			IntegrationLogModel integrationLogModel = new IntegrationLogModel();
			//integrationLogModel.setOrderId(Long.valueOf(payResultDtoReq.getEbsorderNo()));
			integrationLogModel.setType("RECIVE-MESSAGGE");
			integrationLogModel.setBizData(JSON.toJSONString(sendMQCommonModel));
			integrationLogModel.setRemark("接收中台订单支付信息,ebsNo:" + payResultDtoReq.getEbsorderNo());
			integrationLogModel.setOperType("eventHandle");
			integrationLogService.addLog(integrationLogModel);
			
			//ebs支付
			if (PayTypeEnums.BAL_PAY.name().equals(payResultDtoReq.getPayType())) {
				orderIntegrationService.receiveOrderPayInfo(payResultDtoReq);
			}else{
				ReciveNotifyEbsPayDtoReq dtoReq = new ReciveNotifyEbsPayDtoReq();
				dtoReq.setEbsorderNo(payResultDtoReq.getEbsorderNo());
				dtoReq.setMoneyfeedPayNo(payResultDtoReq.getMoneyfeedPayNo());
				dtoReq.setMsg(payResultDtoReq.getMsg());
				dtoReq.setOrgId(payResultDtoReq.getOrgId());
				dtoReq.setPayFlag(payResultDtoReq.getPayFlag());
				dtoReq.setPayType(payResultDtoReq.getPayType());
				orderIntegrationService.reciveNotifyEbsPay(dtoReq);
			}
			
//			//银行卡支付
//			if (PayTypeEnums.CARD_PAY.name().equals(payResultDtoReq.getPayType())) {
//				payIntegrationFeignClient.reciveNotifyEbsPay(dtoReq);
//			}
//			//订单充值
//			else if (PayTypeEnums.SO_PAY.name().equals(payResultDtoReq.getPayType())) {
//				payIntegrationFeignClient.reciveNotifyEbsPay(dtoReq);
//			}
//			//账户充值
//			else if (PayTypeEnums.ACC_PAY.name().equals(payResultDtoReq.getPayType())) {
//				payIntegrationFeignClient.reciveNotifyEbsPay(dtoReq);
//			}else {
//				logger.error("--支付返回结果未找到匹配类型---");
//			}
		}
		//锁库通知
		else if(MQMsgKindEnum.lock_library.equals(sendMQCommonModel.getMqMsgKind())){
			RepositoryLowDtoReq repositoryLowDtoReq = JSON.toJavaObject((JSONObject)sendMQCommonModel.getData(), RepositoryLowDtoReq.class);
			IntegrationLogModel integrationLogModel = new IntegrationLogModel();
			integrationLogModel.setOrderId(Long.valueOf(repositoryLowDtoReq.getMoneyfeedOrderId()));
			integrationLogModel.setType("RECIVE-MESSAGGE");
			integrationLogModel.setBizData(JSON.toJSONString(sendMQCommonModel));
			integrationLogModel.setRemark("接收中台锁库存信息,moneyfeedOrderId:" + repositoryLowDtoReq.getMoneyfeedOrderId());
			integrationLogModel.setOperType("eventHandle");
			integrationLogService.addLog(integrationLogModel);
			
			orderIntegrationService.reciveRepositoryLow(repositoryLowDtoReq);
		}

	}

}
