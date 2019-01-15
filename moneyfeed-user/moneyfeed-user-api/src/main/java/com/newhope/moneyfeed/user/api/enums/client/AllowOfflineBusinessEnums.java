package com.newhope.moneyfeed.user.api.enums.client;

public enum AllowOfflineBusinessEnums {

	ALLOW("允许交易", "ALLOW"), 
	UN_ALLOW("不允许交易", "UN_ALLOW");

	private String desc;

	private String value;

	private AllowOfflineBusinessEnums(String desc, String value) {
		this.desc = desc;
		this.value = value;
	}
	
	public String getDesc() {
		return desc;
	}

	public String getValue() {
		return value;
	}}
