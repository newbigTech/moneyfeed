package com.newhope.moneyfeed.user.api.enums.client;

public enum CustomerAccountTypeEnums {

	BANK("银行卡", "BANK"), 
	CREDIT("余额", "CREDIT");
	private String desc;

	private String value;

	private CustomerAccountTypeEnums(String desc, String value) {
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
