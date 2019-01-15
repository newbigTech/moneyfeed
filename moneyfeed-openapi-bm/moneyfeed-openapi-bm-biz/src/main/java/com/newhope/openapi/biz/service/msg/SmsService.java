package com.newhope.openapi.biz.service.msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.dto.request.sms.SmsSendDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.SmsTemplateEnums;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.vo.request.msg.SmsAuthCodeReq;
import com.newhope.openapi.api.vo.response.msg.SmsResult;
import com.newhope.openapi.biz.rpc.feign.msg.SmsFeignClient;

@Service
public class SmsService {
	
	@Autowired
	SmsFeignClient smsFeignClient;
	
	public SmsResult sendSmsAuthCode(final SmsAuthCodeReq request) {
		SmsResult result = new SmsResult();
		
		SmsSendDtoReq dtoReq = new SmsSendDtoReq();
		dtoReq.setMobile(request.getMobile());
		dtoReq.setTemplateId(SmsTemplateEnums.SMS_151772088.getTemplateId());
		dtoReq.setAuthCode(true);
		BaseResponse<DtoResult> feignResp = smsFeignClient.sendSms(dtoReq);
		
		result.setSmsCode((String) feignResp.getData().getData());
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		return result;
	}

}
