package com.newhope.moneyfeed.api.dto.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class LoginDtoResult extends DtoResult implements Serializable {

	private static final long serialVersionUID = -4010645599294924476L;
	
	/** 用户三方账号 */
    private String thirdAccount;
    /** 第三方账户头像 */
    private String thirdImg;
    /** 第三方账户昵称 */
    private String thirdNickName;
    
    private Long userId;

    private String subscribeFlag;
    
    @ApiModelProperty(name = "userName", notes = "被代理的用户名")
    private String userName;
    
    @ApiModelProperty(name = "proxyUserFlag", notes = "是否代理授权用户")
    private boolean proxyUserFlag;
    
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getUserName() {
		return userName;
	}
	public boolean isProxyUserFlag() {
		return proxyUserFlag;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setProxyUserFlag(boolean proxyUserFlag) {
		this.proxyUserFlag = proxyUserFlag;
	}

	public String getSubscribeFlag() {
		return subscribeFlag;
	}

	public void setSubscribeFlag(String subscribeFlag) {
		this.subscribeFlag = subscribeFlag;
	}
}
