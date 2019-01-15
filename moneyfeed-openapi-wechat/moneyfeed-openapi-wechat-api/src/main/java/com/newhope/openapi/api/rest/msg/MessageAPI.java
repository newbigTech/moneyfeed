package com.newhope.openapi.api.rest.msg;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.msg.SmsAuthCodeReq;
import com.newhope.openapi.api.vo.request.msg.VerdictSmsCodeReq;
import com.newhope.openapi.api.vo.response.msg.SmsResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="Message", description="消息中心API", protocols="http")
public interface MessageAPI {

	@ApiOperation(value="用户登录商城发送短信验证码", notes="用户登录商城发送短信验证码", protocols="http")
	@ApiImplicitParams({
		@ApiImplicitParam(name="requestBody", value="发送短信验证码请求参数(json格式)", required=true, paramType="body", dataType="SmsAuthCodeReq")
	})
	@RequestMapping(value = "/sms/code", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<SmsResult> sendSmsCode(@Valid @RequestBody SmsAuthCodeReq requestBody);
	

	@ApiOperation(value="修改支付密码-发送短信验证码", notes="修改支付密码-发送短信验证码", protocols="http")
	@ApiImplicitParams({
		@ApiImplicitParam(name="requestBody", value="发送短信验证码请求参数(json格式)", required=true, paramType="body", dataType="SmsAuthCodeReq")
	})
	@RequestMapping(value = "/sms/code/account/password", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<SmsResult> sendAccountPwdSmsCode(@Valid @RequestBody SmsAuthCodeReq requestBody);
	
	
	@ApiOperation(value="验证短信验证码", notes="验证短信验证码", protocols="http")
	@ApiImplicitParams({
		@ApiImplicitParam(name="requestBody", value="发送短信验证码请求参数(json格式)", required=true, paramType="body", dataType="VerdictSmsCodeReq")
	})
	@RequestMapping(value = "/sms/code/verdict", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<Result> verdictSmsCode(@Valid @RequestBody VerdictSmsCodeReq requestBody);
	
	@ApiOperation(value="个人信息中修改手机号码发送短信验证码", notes="个人信息中修改手机号码发送短信验证码", protocols="http")
	@ApiImplicitParams({
		@ApiImplicitParam(name="requestBody", value="发送短信验证码请求参数(json格式)", required=true, paramType="body", dataType="SmsAuthCodeReq")
	})
	@RequestMapping(value = "/sms/code/edit/mobile", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<SmsResult> sendSmsCodeEditMobile(@Valid @RequestBody SmsAuthCodeReq requestBody);
	
	@ApiOperation(value="被邀请人确认邀请发送短信验证码", notes="被邀请人确认邀请发送短信验证码", protocols="http")
	@ApiImplicitParams({
		@ApiImplicitParam(name="requestBody", value="发送短信验证码请求参数(json格式)", required=true, paramType="body", dataType="SmsAuthCodeReq")
	})
	@RequestMapping(value = "/sms/code/confirm", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<SmsResult> sendSmsCodeConfirm(@Valid @RequestBody SmsAuthCodeReq requestBody);
	
	@ApiOperation(value="修改员工手机号发送短信验证码", notes="修改员工手机号发送短信验证码", protocols="http")
	@ApiImplicitParams({
		@ApiImplicitParam(name="requestBody", value="发送短信验证码请求参数(json格式)", required=true, paramType="body", dataType="SmsAuthCodeReq")
	})
	@RequestMapping(value = "/sms/code/edit/employee/mobile", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<SmsResult> sendSmsCodeEditEmployeeMobile(@Valid @RequestBody SmsAuthCodeReq requestBody);
	
	
	
}