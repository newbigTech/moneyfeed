package com.newhope.moneyfeed.integration.api.vo.request.third;

import java.io.Serializable;

public class QueryProductPriceReq implements Serializable {

	private static final long serialVersionUID = 1125097150814249546L;
	
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
