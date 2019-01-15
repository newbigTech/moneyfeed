package com.newhope.openapi.api.rest.pay;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.openapi.api.vo.request.pay.PayOrderCallbackReq;
import com.newhope.openapi.api.vo.request.pay.PayOrderCustomerReq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="OrderPayOpenAPI", description="订单支付服务open-api", protocols="http")
public interface PayOrderOpenAPI {

	@ApiOperation(value = "用户提交支付", notes = "用户提交支付", protocols = "http")
    @RequestMapping(value = "/pay/customer", method = RequestMethod.POST)
    public void payCustomer(HttpServletRequest request, HttpServletResponse response, Writer writer, @ModelAttribute("form") PayOrderCustomerReq reqBody);
	
	@ApiOperation(value="支付回调地址", notes="支付回调地址", protocols="http")
	@ApiImplicitParams({
			@ApiImplicitParam(name="reqBody", value="reqBody", required=true, paramType="body", dataType="PayOrderCallbackReq")
	})
	@RequestMapping(value = "/pay/callback", method = RequestMethod.POST)
	public String payCallback(HttpServletRequest request, HttpServletResponse response, Writer writer);

	@ApiOperation(value="特殊处理", notes="特殊处理", protocols="http")
	@RequestMapping(value = "/pay/special", method = RequestMethod.POST)
	public String paySpecial(HttpServletRequest request, HttpServletResponse response, Writer writer);

}