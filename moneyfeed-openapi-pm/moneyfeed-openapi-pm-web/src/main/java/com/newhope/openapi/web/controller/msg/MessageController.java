package com.newhope.openapi.web.controller.msg;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.enums.SmsTemplateEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.rest.msg.MessageAPI;
import com.newhope.openapi.api.vo.request.msg.SmsAuthCodeReq;
import com.newhope.openapi.api.vo.response.msg.SmsResult;
import com.newhope.openapi.biz.service.msg.SmsService;

@RestController
@RequestMapping(value = "/message")
public class MessageController extends AbstractController implements MessageAPI {
	
	@Autowired
	SmsService smsService;

	@Override
	public BaseResponse<SmsResult> sendSmsCode(@RequestBody SmsAuthCodeReq requestBody) {
		SmsResult result = new SmsResult();
		result = smsService.sendSmsAuthCode(requestBody);
//		result.setSmsCode("123456");
		return buildJson(result);
	}

	@Override
	public BaseResponse<SmsResult> sendUserSmsCode(@RequestBody SmsAuthCodeReq requestBody) {
		SmsResult result = new SmsResult();
		requestBody.setTemplateType(SmsTemplateEnums.SMS_154592207.getTemplateId());
		result = smsService.sendSmsAuthCode(requestBody);
		return buildJson(result);
	}
}
