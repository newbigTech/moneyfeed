package com.newhope.moneyfeed.user.api.dto.response.client;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

public class ClientUserLoginDtoResult extends DtoResult {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6699460109590408171L;
	
	/** 用户三方账号 */
    private String thirdAccount;
    /** 第三方账户头像 */
    private String thirdImg;
    /** 第三方账户昵称 */
    private String thirdNickName;
    
    /** 是否关注公众号 */
    private String subscribeFlag;
   
    private Long userId;
    
    private String userName;
    
    /**  是否是用户本人 */
    private Boolean userIsSelf = false;
    
    /** 是否首次登陆系统*/
    private boolean firstLogin;
    
	public boolean getFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
	public String getSubscribeFlag() {
		return subscribeFlag;
	}
	public void setSubscribeFlag(String subscribeFlag) {
		this.subscribeFlag = subscribeFlag;
	}
	public Boolean getUserIsSelf() {
		return userIsSelf;
	}
	public void setUserIsSelf(Boolean userIsSelf) {
		this.userIsSelf = userIsSelf;
	}
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
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
