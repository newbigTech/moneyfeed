package com.newhope.moneyfeed.user.api.enums.client;

public enum CustomerTypeEnums {

	PERSON("个人客户", "PERSON"), 
	ENTERPRISE("企业客户", "ENTERPRISE");
	private String desc;

	private String value;

	private CustomerTypeEnums(String desc, String value) {
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
