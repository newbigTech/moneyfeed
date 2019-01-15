package com.newhope.openapi.api.vo.response.msg;

import java.io.Serializable;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class SmsResult extends Result implements Serializable{
	
	private static final long serialVersionUID = 6831492900114615661L;
	
	@ApiModelProperty(name = "smsCode", notes = "短信验证码")
	private String smsCode;

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

}
