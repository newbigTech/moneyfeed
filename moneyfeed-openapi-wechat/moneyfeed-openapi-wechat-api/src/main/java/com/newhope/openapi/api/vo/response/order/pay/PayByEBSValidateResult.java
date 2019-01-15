package com.newhope.openapi.api.vo.response.order.pay;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户余额支付验证信息
 * @author hp
 *
 */
public class PayByEBSValidateResult extends Result {
    
	private static final long serialVersionUID = -394236523797294454L;
	
    @ApiModelProperty("管理支付密码的手机")
    private String phone;
    
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}