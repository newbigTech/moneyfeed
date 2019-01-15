package com.newhope.moneyfeed.order.api.enums;

public enum OrderSysParamsTypeEnum {
	
	ID_GENERATION("ID_GENERATION"),
	TRANSPORT_SUGGEST("TRANSPORT_SUGGEST");
	
	private String type;
	
	OrderSysParamsTypeEnum(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
