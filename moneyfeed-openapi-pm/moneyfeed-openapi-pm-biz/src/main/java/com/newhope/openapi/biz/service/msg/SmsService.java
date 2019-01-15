package com.newhope.openapi.biz.service.msg;

import com.newhope.openapi.biz.rpc.feign.user.PmUserFeignClient;
import org.springframework.beans.BeanUtils;
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
	@Autowired
	PmUserFeignClient pmUserFeignClient;

	public SmsResult sendSmsAuthCode(final SmsAuthCodeReq request) {
		SmsResult result = new SmsResult();
		//获取验证码时校验是否平台账户,是才发送短信，否则直接返回
		BaseResponse<DtoResult> enablePmUserInfoByMobile =
				pmUserFeignClient.getEnablePmUserInfoByMobile(request.getMobile());
		if(null == enablePmUserInfoByMobile.getData() ){
			result.setCode(enablePmUserInfoByMobile.getCode());
			result.setMsg(enablePmUserInfoByMobile.getMsg());
			return result;
		}

		SmsSendDtoReq dtoReq = new SmsSendDtoReq();
		dtoReq.setMobile(request.getMobile());
		dtoReq.setTemplateId(SmsTemplateEnums.SMS_151772088.getTemplateId());
		dtoReq.setAuthCode(true);
		BaseResponse<DtoResult> feignResp = smsFeignClient.sendSms(dtoReq);

		if (feignResp.isSuccess()) {
			result.setSmsCode((String) feignResp.getData().getData());
		}
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		return result;
	}

}
