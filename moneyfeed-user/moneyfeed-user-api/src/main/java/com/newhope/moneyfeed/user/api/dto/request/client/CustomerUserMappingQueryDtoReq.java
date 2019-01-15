package com.newhope.moneyfeed.user.api.dto.request.client;

import java.io.Serializable;

public class CustomerUserMappingQueryDtoReq implements Serializable {

	private static final long serialVersionUID = 1612175876144680438L;

	private Long customerId;

	private Long userId;
	
    private Boolean enable;

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

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}
