package com.newhope.moneyfeed.user.api.dto.request.client;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class ClientUserLoginDtoReq implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6668154541097034077L;
	@ApiModelProperty(name="authcode", notes="三方授权码", required=false)
	private String authcode;
	
	@ApiModelProperty(name="thirdSource", notes="三方来源", required=false)
	private String thirdSource;
	/** 用户三方账号 */
    private String thirdAccount;
    /** 第三方账户头像 */
    private String thirdImg;
    /** 第三方账户昵称 */
    private String thirdNickName;
    
	@ApiModelProperty(name="mobile", notes="用户登录手机号", required=false)
	private String mobile;
	
	@ApiModelProperty(name="password", notes="用户登录密码", required=false)
	private String password;
	
	@ApiModelProperty(name="smscode", notes="短信验证码", required=false)
	private String smscode;
	
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
	public String getThirdAccount() {
		return thirdAccount;
	}
	public void setThirdAccount(String thirdAccount) {
		this.thirdAccount = thirdAccount;
	}
	public String getThirdImg() {
		return thirdImg;
	}
	public void setThirdImg(String thirdImg) {
		this.thirdImg = thirdImg;
	}
	public String getThirdNickName() {
		return thirdNickName;
	}
	public void setThirdNickName(String thirdNickName) {
		this.thirdNickName = thirdNickName;
	}
	
}
