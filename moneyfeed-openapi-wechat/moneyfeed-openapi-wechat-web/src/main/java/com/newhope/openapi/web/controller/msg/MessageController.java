package com.newhope.openapi.web.controller.msg;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.request.sms.SmsSendDtoReq;
import com.newhope.moneyfeed.api.dto.request.sms.SmsVerdictDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.SmsTemplateEnums;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.rest.msg.MessageAPI;
import com.newhope.openapi.api.vo.request.msg.SmsAuthCodeReq;
import com.newhope.openapi.api.vo.request.msg.VerdictSmsCodeReq;
import com.newhope.openapi.api.vo.response.msg.SmsResult;
import com.newhope.openapi.biz.rpc.feign.msg.SmsFeignClient;


@RestController
public class MessageController extends AbstractController implements MessageAPI {

	@Autowired
	SmsFeignClient smsFeignClient;

	@Override
	public BaseResponse<SmsResult> sendSmsCode(@Valid @RequestBody SmsAuthCodeReq requestBody) {
		SmsResult result = new SmsResult();
		SmsSendDtoReq smsDto = new SmsSendDtoReq();
		smsDto.setMobile(requestBody.getMobile());
		smsDto.setAuthCode(true);
		smsDto.setTemplateId(SmsTemplateEnums.SMS_151772058.getTemplateId());
		BaseResponse<DtoResult> feignResult = smsFeignClient.sendSms(smsDto);
		BeanUtils.copyProperties(feignResult,result);
		if(feignResult.isSuccess()){
			result.setSmsCode((String) feignResult.getData().getData());
		}
		result.setCode(feignResult.getCode());
		result.setMsg(feignResult.getMsg());
		return buildJson(result);
	}

	@Override
	public BaseResponse<SmsResult> sendAccountPwdSmsCode(@Valid @RequestBody SmsAuthCodeReq requestBody) {
		SmsResult result = new SmsResult();
		SmsSendDtoReq smsDto = new SmsSendDtoReq();
		smsDto.setMobile(requestBody.getMobile());
		smsDto.setAuthCode(true);
		smsDto.setTemplateId(SmsTemplateEnums.SMS_152205419.getTemplateId());
		BaseResponse<DtoResult> feignResult = smsFeignClient.sendSms(smsDto);
		BeanUtils.copyProperties(feignResult,result);
		if(feignResult.isSuccess()){
			result.setSmsCode((String) feignResult.getData().getData());
		}
		result.setCode(feignResult.getCode());
		result.setMsg(feignResult.getMsg());
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> verdictSmsCode(@Valid @RequestBody VerdictSmsCodeReq requestBody) {
		Result result = new Result();
		SmsVerdictDtoReq dtoReq = new SmsVerdictDtoReq();
		dtoReq.setMobile(requestBody.getMobile());
		dtoReq.setSmsCode(requestBody.getSmsCode());
		BaseResponse<DtoResult> feignResult = smsFeignClient.verdictSmsCode(dtoReq);
		result.setCode(feignResult.getCode());
		result.setMsg(feignResult.getMsg());
		return buildJson(result);
	}
	
	@Override
	public BaseResponse<SmsResult> sendSmsCodeEditMobile(@Valid @RequestBody SmsAuthCodeReq requestBody) {
		SmsResult result = new SmsResult();
		SmsSendDtoReq smsDto = new SmsSendDtoReq();
		smsDto.setMobile(requestBody.getMobile());
		smsDto.setAuthCode(true);
		smsDto.setTemplateId(SmsTemplateEnums.SMS_154592045.getTemplateId());
		BaseResponse<DtoResult> feignResult = smsFeignClient.sendSms(smsDto);
		BeanUtils.copyProperties(feignResult,result);
		if(feignResult.isSuccess()){
			result.setSmsCode((String) feignResult.getData().getData());
		}
		result.setCode(feignResult.getCode());
		result.setMsg(feignResult.getMsg());
		return buildJson(result);
	}
	
	@Override
	public BaseResponse<SmsResult> sendSmsCodeConfirm(@Valid @RequestBody SmsAuthCodeReq requestBody) {
		SmsResult result = new SmsResult();
		SmsSendDtoReq smsDto = new SmsSendDtoReq();
		smsDto.setMobile(requestBody.getMobile());
		smsDto.setAuthCode(true);
		smsDto.setTemplateId(SmsTemplateEnums.SMS_154592060.getTemplateId());
		BaseResponse<DtoResult> feignResult = smsFeignClient.sendSms(smsDto);
		BeanUtils.copyProperties(feignResult,result);
		if(feignResult.isSuccess()){
			result.setSmsCode((String) feignResult.getData().getData());
		}
		result.setCode(feignResult.getCode());
		result.setMsg(feignResult.getMsg());
		return buildJson(result);
	}
	
	@Override
	public BaseResponse<SmsResult> sendSmsCodeEditEmployeeMobile(@Valid @RequestBody SmsAuthCodeReq requestBody) {
		SmsResult result = new SmsResult();
		SmsSendDtoReq smsDto = new SmsSendDtoReq();
		smsDto.setMobile(requestBody.getMobile());
		smsDto.setAuthCode(true);
		smsDto.setTemplateId(SmsTemplateEnums.SMS_154586889.getTemplateId());
		BaseResponse<DtoResult> feignResult = smsFeignClient.sendSms(smsDto);
		BeanUtils.copyProperties(feignResult,result);
		if(feignResult.isSuccess()){
			result.setSmsCode((String) feignResult.getData().getData());
		}
		result.setCode(feignResult.getCode());
		result.setMsg(feignResult.getMsg());
		return buildJson(result);
	}
	
}
