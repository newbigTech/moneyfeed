package com.newhope.order.biz.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.dto.request.sms.SmsSendDtoReq;
import com.newhope.moneyfeed.api.dto.request.wechat.WechatMsgDtoReq;
import com.newhope.moneyfeed.api.enums.AppSourceEnums;
import com.newhope.moneyfeed.api.enums.wechat.WechatMsgTypeEnums;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserThirdAccountDtoResult;
import com.newhope.order.biz.rpc.feign.SmsFeignClient;
import com.newhope.order.biz.rpc.feign.WechatMsgFeignClient;
import com.newhope.order.biz.rpc.feign.uc.ClientUserFeignClient;
import com.newhope.order.biz.service.OrderMsgService;

@Service("OrderMsgService")
public class OrderMsgServiceImpl implements OrderMsgService{

    @Autowired
	ClientUserFeignClient clientUserFeignClient;
    @Autowired
	SmsFeignClient smsFeignClient;
    @Autowired
	WechatMsgFeignClient wechatMsgFeignClient;
	
    @Override
	public void sendWechatMsg(Long userId, WechatMsgTypeEnums msgType, List<String> params) {
		sendWechatMsg(userId, msgType, params, null);
	}
	
    @Override
	public void sendWechatMsg(Long userId, WechatMsgTypeEnums msgType, List<String> params,String url) {
		WechatMsgDtoReq wcDtoReq = new WechatMsgDtoReq();
		BaseResponse<ClientUserThirdAccountDtoResult> clientUserThirdAccountDtoResult = clientUserFeignClient
				.queryUserThirdAccount(userId , null, AppSourceEnums.WECHAT.name());
		if (clientUserThirdAccountDtoResult.isSuccess()
				&& clientUserThirdAccountDtoResult.getData() != null
				&& clientUserThirdAccountDtoResult.getData().getAccount() != null
				&& StringUtils.isNotBlank(clientUserThirdAccountDtoResult.getData().getAccount()
						.getThirdAccount()) ) {
			wcDtoReq.setWechatMsgTypeEnums(msgType);
			wcDtoReq.setParams(params);
			wcDtoReq.setOpenid(
					clientUserThirdAccountDtoResult.getData().getAccount().getThirdAccount());
			wcDtoReq.setUrl(url);
			wechatMsgFeignClient.sendWechatMsg(wcDtoReq);
		}
		
	}
	@Override
	public void sendSmsMsg(String mobile, String templateId, Map<String, String> paramMap) {
		sendSmsMsg(mobile, templateId,false, paramMap);
	}

	@Override
	public void sendSmsMsg(String mobile, String templateId, boolean authCode,Map<String, String> paramMap) {
		SmsSendDtoReq smsDtoReq = new SmsSendDtoReq();
		smsDtoReq.setMobile(mobile);
		smsDtoReq.setTemplateId(templateId);
		smsDtoReq.setParamMap(paramMap);
		smsDtoReq.setAuthCode(authCode);
		smsFeignClient.sendSms(smsDtoReq);
	}
	

}
