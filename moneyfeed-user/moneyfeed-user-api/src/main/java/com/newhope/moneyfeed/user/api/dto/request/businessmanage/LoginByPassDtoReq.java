package com.newhope.moneyfeed.user.api.dto.request.businessmanage;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class LoginByPassDtoReq implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4428784075674802194L;

	@NotBlank(message="手机号为空")
	@ApiModelProperty(name="mobile", notes="登录手机号", required=true)
	private String mobile;
	
	@NotBlank(message="密码为空")
	@ApiModelProperty(name="password", notes="登录密码", required=true)
	private String password;

	public String getMobile() {
		return mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
