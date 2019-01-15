package com.newhope.moneyfeed.api.dto.request.user;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class UserLoginDtoReq implements Serializable {

	private static final long serialVersionUID = 9008534012167945703L;
	
	@ApiModelProperty(name="authcode", notes="三方授权码", required=false)
	private String authcode;
	@ApiModelProperty(name="thirdSource", notes="三方来源", required=false)
	private String thirdSource;
	@ApiModelProperty(name="mobile", notes="用户登录手机号", required=false)
	private String mobile;
	@ApiModelProperty(name="password", notes="用户登录密码", required=false)
	private String password;
	@ApiModelProperty(name="smscode", notes="短信验证码", required=false)
	private String smscode;
	
	private String appCode;
	private String appSource;
	
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getAppSource() {
		return appSource;
	}
	public void setAppSource(String appSource) {
		this.appSource = appSource;
	}
	public String getThirdSource() {
		return thirdSource;
	}
	public void setThirdSource(String thirdSource) {
		this.thirdSource = thirdSource;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	public String getSmscode() {
		return smscode;
	}
	public void setSmscode(String smscode) {
		this.smscode = smscode;
	}
}
