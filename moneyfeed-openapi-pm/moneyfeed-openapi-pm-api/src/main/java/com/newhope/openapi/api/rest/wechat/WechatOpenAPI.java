package com.newhope.openapi.api.rest.wechat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.vo.request.WeChatReq;

import java.io.PrintWriter;

@Api(value="Wechat", description="微信API", protocols="http")
public interface WechatOpenAPI {

	@ApiOperation(value="微信token认证接入点", notes="微信token认证接入点，用于微信服务回调验证公众号", protocols="http")
	@RequestMapping(value = { "/wechat/accesspoint/jubaozhu" }, method = RequestMethod.GET)
	public void coreJoinGetJubaozhu(WeChatReq weChat, PrintWriter out);
	
}