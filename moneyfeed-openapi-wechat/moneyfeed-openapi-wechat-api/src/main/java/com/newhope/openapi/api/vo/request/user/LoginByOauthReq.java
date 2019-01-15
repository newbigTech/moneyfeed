package com.newhope.openapi.api.vo.request.user;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class LoginByOauthReq implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1465406733332268120L;
	@NotBlank(message="授权码为空")
	@ApiModelProperty(name="authcode", notes="授权码", required=true)
	private String authcode;
	
	@NotBlank(message="三方来源为空")
	@ApiModelProperty(name="thirdSource", notes="三方来源(WECHAT=微信)", required=true)
	private String thirdSource;

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getThirdSource() {
		return thirdSource;
	}

	public void setThirdSource(String thirdSource) {
		this.thirdSource = thirdSource;
	}
	
}
