package com.newhope.moneyfeed.pay.web.controller.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.integration.api.enums.common.MQMsgKindEnum;
import com.newhope.moneyfeed.integration.api.exbean.common.SendMQCommonModel;
import com.newhope.moneyfeed.order.api.bean.IntegrationLogModel;
import com.newhope.moneyfeed.order.api.dto.request.integration.RepositoryLowDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.pay.PayResultDtoReq;
import com.newhope.moneyfeed.order.api.enums.PayTypeEnums;
import com.newhope.moneyfeed.pay.api.dto.req.integration.ReciveNotifyEbsPayDtoReq;
import com.newhope.moneyfeed.pay.api.rest.pay.LxlTestPayAPI;
import com.newhope.pay.biz.service.PayIntegrationService;

@RestController
public class LxlTestPayController extends AbstractController implements LxlTestPayAPI {
	
	private static Logger logger = LoggerFactory.getLogger(LxlTestPayController.class);

//	@Autowired
//	OrderIntegrationService orderIntegrationService;
	
	@Autowired
	PayIntegrationService payIntegrationService;
	
//	@Override
//	public void test1(Writer writer, @ModelAttribute("form") PayOrderCustomerDtoReq reqBody) {
//		logger.info("lxl>>>>>>>>>>>测试开始");
//		String reqUrl = "http://ccb.com/";
//		Map<String, String> hiddens = new HashMap<String, String>();
//		hiddens.put("lxl", "Lxl");
//		StringBuffer sf = new StringBuffer();
//		sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset="+"UTF-8"+"\"/></head><body>");
//		sf.append("<form id = \"pay_form\" action=\"" + reqUrl
//				+ "\" method=\"post\">");
//			Set<Entry<String, String>> set = hiddens.entrySet();
//			Iterator<Entry<String, String>> it = set.iterator();
//			while (it.hasNext()) {
//				Entry<String, String> ey = it.next();
//				String key = ey.getKey();
//				String value = ey.getValue();
//				sf.append("<input type=\"hidden\" name=\"" + key + "\" id=\""
//						+ key + "\" value=\"" + value + "\"/>");
//			}
//		sf.append("</form>");
//		sf.append("</body>");
//		sf.append("<script type=\"text/javascript\">");
//		sf.append("document.all.pay_form.submit();");
//		sf.append("</script>");
//		sf.append("</html>");
//        try {
//			writer.write(sf.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//		logger.info("lxl>>>>>>>>>>>测试结束");
//	}

	@Override
	public void test1() {
		logger.info("lxl>>>>>>>>>>>测试开始");
//		ReciveNotifyEbsPayDtoReq dtoReq = new ReciveNotifyEbsPayDtoReq();
//		dtoReq.setEbsorderNo("114980046596");
//		dtoReq.setPayFlag(false);
//		dtoReq.setMoneyfeedPayNo("200704001012");
//		
//		dtoReq.setMsg("好");
//		dtoReq.setOuCode("606");
//		dtoReq.setPayType(PayTypeEnums.CARD_PAY.name());
		
		String s = "{\"data\":{\"ebsorderNo\":\"114980046643\",\"moneyfeedPayNo\":\"610704001012\",\"msg\":\"\",\"orgId\":\"606\",\"payFlag\":true,\"payType\":\"SO_PAY\"},\"mqMsgKind\":\"recharge\",\"mqSendToClient\":\"order\"}";
		logger.info("--接受到中台到order的消息---" + JSON.toJSONString(s));
		SendMQCommonModel sendMQCommonModel =JSON.parseObject(s, SendMQCommonModel.class);
		//支付、充值消息
		if (MQMsgKindEnum.pay.equals(sendMQCommonModel.getMqMsgKind())) {
			PayResultDtoReq payResultDtoReq = JSON.toJavaObject((JSONObject)sendMQCommonModel.getData(), PayResultDtoReq.class);
			IntegrationLogModel integrationLogModel = new IntegrationLogModel();
			//integrationLogModel.setOrderId(Long.valueOf(payResultDtoReq.getEbsorderNo()));
			integrationLogModel.setType("RECIVE-MESSAGGE");
			integrationLogModel.setBizData(JSON.toJSONString(sendMQCommonModel));
			integrationLogModel.setRemark("接收中台订单支付信息,ebsNo:" + payResultDtoReq.getEbsorderNo());
			integrationLogModel.setOperType("eventHandle");
//			integrationLogService.addLog(integrationLogModel);
			
			//ebs支付
			if (PayTypeEnums.BAL_PAY.name().equals(payResultDtoReq.getPayType())) {
//				orderIntegrationService.receiveOrderPayInfo(payResultDtoReq);
			}
			//银行卡支付
			else if (PayTypeEnums.CARD_PAY.name().equals(payResultDtoReq.getPayType())) {
				ReciveNotifyEbsPayDtoReq dtoReq = new ReciveNotifyEbsPayDtoReq();
				BeanUtils.copyProperties(payResultDtoReq, dtoReq);
				payIntegrationService.reciveNotifyEbsPay(dtoReq);
			}else {
				logger.error("--支付返回结果未找到匹配类型---");
			}
		}else if(MQMsgKindEnum.recharge.equals(sendMQCommonModel.getMqMsgKind())){
			PayResultDtoReq payResultDtoReq = JSON.toJavaObject((JSONObject)sendMQCommonModel.getData(), PayResultDtoReq.class);
			//订单充值
			if (PayTypeEnums.SO_PAY.name().equals(payResultDtoReq.getPayType())) {
				ReciveNotifyEbsPayDtoReq dtoReq = new ReciveNotifyEbsPayDtoReq();
				BeanUtils.copyProperties(payResultDtoReq, dtoReq);
				payIntegrationService.reciveNotifyEbsPay(dtoReq);
			}
			//账户充值
			else if (PayTypeEnums.ACC_PAY.name().equals(payResultDtoReq.getPayType())) {
				ReciveNotifyEbsPayDtoReq dtoReq = new ReciveNotifyEbsPayDtoReq();
				BeanUtils.copyProperties(payResultDtoReq, dtoReq);
				payIntegrationService.reciveNotifyEbsPay(dtoReq);
			}else {
				logger.error("--充值返回结果未找到匹配类型---");
			}
		}else if(MQMsgKindEnum.lock_library.equals(sendMQCommonModel.getMqMsgKind())){
//			RepositoryLowDtoReq repositoryLowDtoReq = JSON.toJavaObject((JSONObject)sendMQCommonModel.getData(), RepositoryLowDtoReq.class);
//			IntegrationLogModel integrationLogModel = new IntegrationLogModel();
//			//integrationLogModel.setOrderId(Long.valueOf(payResultDtoReq.getEbsorderNo()));
//			integrationLogModel.setType("RECIVE-MESSAGGE");
//			integrationLogModel.setBizData(JSON.toJSONString(sendMQCommonModel));
//			integrationLogModel.setRemark("接收中台订单支付信息,moneyfeedOrderId:" + repositoryLowDtoReq.getMoneyfeedOrderId());
//			integrationLogModel.setOperType("eventHandle");
//			integrationLogService.addLog(integrationLogModel);
//			
//			orderIntegrationService.reciveRepositoryLow(repositoryLowDtoReq);
		}
		
//		DtoResult dtoResult = payIntegrationService.reciveNotifyEbsPay(dtoReq);
		logger.info("lxl>>>>>>>>>>>测试结束");
	}
}










