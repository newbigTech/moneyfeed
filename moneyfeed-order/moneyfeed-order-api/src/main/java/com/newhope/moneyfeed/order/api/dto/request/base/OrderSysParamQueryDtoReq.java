package com.newhope.moneyfeed.order.api.dto.request.base;

import java.io.Serializable;

public class OrderSysParamQueryDtoReq implements Serializable{

	private static final long serialVersionUID = 8733997844809434352L;

	private String code;
	
	private String type;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
