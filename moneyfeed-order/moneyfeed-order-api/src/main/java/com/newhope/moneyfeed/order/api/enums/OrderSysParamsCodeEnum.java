package com.newhope.moneyfeed.order.api.enums;

public enum OrderSysParamsCodeEnum {
	
	TRANSPORT_WEIGHT("TRANSPORT_WEIGHT"),
	ORDER_NUMBER("ORDER_NUMBER"),
	PAY_NUMBER("PAY_NUMBER");
	
	private String type;
	
	OrderSysParamsCodeEnum(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
