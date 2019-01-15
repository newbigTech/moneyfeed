package com.newhope.openapi.api.rest.msg;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.vo.request.msg.SmsAuthCodeReq;
import com.newhope.openapi.api.vo.response.msg.SmsResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="Message", description="消息中心API", protocols="http")
public interface MessageAPI {

	@ApiOperation(value="发送短信验证码", notes="发送短信验证码", protocols="http")
	@ApiImplicitParams({
		@ApiImplicitParam(name="requestBody", value="发送短信验证码请求参数(json格式)", required=true, paramType="body", dataType="SmsAuthCodeReq")
	})
	@RequestMapping(value = "/sms/code", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<SmsResult> sendSmsCode(@RequestBody SmsAuthCodeReq requestBody);

	@ApiOperation(value="用户修改个人信息发送短信验证码", notes="用户修改个人信息发送短信验证码", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="requestBody", value="发送短信验证码请求参数(json格式)", required=true, paramType="body", dataType="SmsAuthCodeReq")
	})
	@RequestMapping(value = "/user/sms/code", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<SmsResult> sendUserSmsCode(@RequestBody SmsAuthCodeReq requestBody);
}