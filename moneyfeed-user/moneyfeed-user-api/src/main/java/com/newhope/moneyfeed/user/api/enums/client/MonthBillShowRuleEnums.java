package com.newhope.moneyfeed.user.api.enums.client;

public enum MonthBillShowRuleEnums {

	UN_SHOW("不显示", "UN_SHOW"), 
	SHOW("显示", "SHOW");
	private String desc;

	private String value;

	private MonthBillShowRuleEnums(String desc, String value) {
		this.desc = desc;
		this.value = value;
	}
	
	public String getDesc() {
		return desc;
	}

	public String getValue() {
		return value;
	}}
