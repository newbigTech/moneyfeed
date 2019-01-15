package com.newhope.openapi.api.vo.response.user;



import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class LoginByOauthResult extends Result{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7942415576905377051L;

	@ApiModelProperty(name = "thirdAccount", notes = "用户三方账号")
    private String thirdAccount;
	
    @ApiModelProperty(name = "thirdImg", notes = "第三方账户头像")
    private String thirdImg;
    
    @ApiModelProperty(name = "thirdNickName", notes = "第三方账户昵称")
    private String thirdNickName;
    
    @ApiModelProperty(name = "userId", notes = "用户id")
    private Long userId;
    
    @ApiModelProperty(name = "customerId", notes = "客户id")
    private Long customerId;
    
    @ApiModelProperty(name = "subscribeFlag", notes = "是否关注公众号,0或者1")
    private String subscribeFlag;
    
	public String getSubscribeFlag() {
		return subscribeFlag;
	}
	public void setSubscribeFlag(String subscribeFlag) {
		this.subscribeFlag = subscribeFlag;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
    
}
