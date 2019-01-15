package com.newhope.openapi.api.vo.request.msg;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

public class VerdictSmsCodeReq implements Serializable {

	private static final long serialVersionUID = 7297937847432655815L;

	@NotEmpty(message = "短信验证码不能为空")
    @ApiModelProperty(name = "smsCode", notes = "验证码", required = true)
    private String smsCode;

    @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty(name = "mobile", notes = "手机号", required = true)
    private String mobile;

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    
}
