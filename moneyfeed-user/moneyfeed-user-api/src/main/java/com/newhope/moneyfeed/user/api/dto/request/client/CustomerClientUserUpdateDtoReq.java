package com.newhope.moneyfeed.user.api.dto.request.client;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class CustomerClientUserUpdateDtoReq extends CustomerClientUserDtoReq {
	
	@NotNull(message="主键Id不能为空")
	@ApiModelProperty("主键Id")
	private Long customerClientUserId;
	
	@NotNull(message="用户Id不能为空")
	@ApiModelProperty("联系人用户主键Id")
	private Long clientUserId;
	
    @ApiModelProperty("true-正常，false-禁用")
    private Boolean enable;
    
    @ApiModelProperty("短信验证码")
    private String smsCode;
    
	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public Long getCustomerClientUserId() {
		return customerClientUserId;
	}

	public void setCustomerClientUserId(Long customerClientUserId) {
		this.customerClientUserId = customerClientUserId;
	}

	public Long getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(Long clientUserId) {
		this.clientUserId = clientUserId;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}
