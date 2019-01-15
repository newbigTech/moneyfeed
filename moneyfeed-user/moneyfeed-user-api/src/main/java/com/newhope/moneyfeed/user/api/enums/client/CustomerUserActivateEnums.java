package com.newhope.moneyfeed.user.api.enums.client;

public enum CustomerUserActivateEnums {

	ACTIVATED("已激活", "ACTIVATED"), 
	NOT_ACTIVE("未激活", "NOT_ACTIVE");
	private String desc;

	private String value;

	private CustomerUserActivateEnums(String desc, String value) {
		this.desc = desc;
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public String getValue() {
		return value;
	}
}
