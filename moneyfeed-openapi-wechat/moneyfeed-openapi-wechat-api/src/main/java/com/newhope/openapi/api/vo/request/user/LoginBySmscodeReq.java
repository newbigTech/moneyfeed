package com.newhope.openapi.api.vo.request.user;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class LoginBySmscodeReq implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8446630469940054172L;
	
	@NotBlank(message="手机号为空")
	@ApiModelProperty(name="mobile", notes="登录手机号", required=true)
	private String mobile;
	@NotBlank(message="短信验证码为空")
	@ApiModelProperty(name="smscode", notes="短信验证码", required=true)
	private String smscode;
	
	@ApiModelProperty(name="thirdAccount", notes="用户三方账号", required=false)
    private String thirdAccount;
	
	@ApiModelProperty(name="thirdImg", notes="第三方账户头像地址", required=false)
    private String thirdImg;

	@ApiModelProperty(name="thirdNickName", notes="第三方账户昵称", required=false)
    private String thirdNickName;

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
