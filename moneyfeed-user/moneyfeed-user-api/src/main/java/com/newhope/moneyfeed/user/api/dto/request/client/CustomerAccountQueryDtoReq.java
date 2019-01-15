package com.newhope.moneyfeed.user.api.dto.request.client;

import java.io.Serializable;

public class CustomerAccountQueryDtoReq implements Serializable {

	private static final long serialVersionUID = 2143168959038501626L;

	//客户id
	private Long customerId;

	//账户类型
	private String accountType;
	
	//是否可用
    private Boolean enable;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}
