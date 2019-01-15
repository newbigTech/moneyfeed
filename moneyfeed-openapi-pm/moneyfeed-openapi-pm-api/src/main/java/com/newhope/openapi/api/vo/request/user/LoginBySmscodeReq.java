package com.newhope.openapi.api.vo.request.user;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import io.swagger.annotations.ApiModelProperty;

public class LoginBySmscodeReq implements Serializable {

	
	private static final long serialVersionUID = 38389803543678643L;
	
	@NotBlank(message="手机号为空")
	@ApiModelProperty(name="mobile", notes="登录手机号", required=true)
	private String mobile;
	@NotBlank(message="短信验证码为空")
	@ApiModelProperty(name="smscode", notes="短信验证码", required=true)
	private String smscode;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSmscode() {
		return smscode;
	}
	public void setSmscode(String smscode) {
		this.smscode = smscode;
	}
}
