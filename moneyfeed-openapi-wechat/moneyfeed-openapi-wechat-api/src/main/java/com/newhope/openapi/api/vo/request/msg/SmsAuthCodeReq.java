package com.newhope.openapi.api.vo.request.msg;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

public class SmsAuthCodeReq implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5435510477421123813L;

	@NotEmpty(message = "手机号不能为空")
	@ApiModelProperty(name="mobile", notes="注册手机号", required=true)
	private String mobile;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
