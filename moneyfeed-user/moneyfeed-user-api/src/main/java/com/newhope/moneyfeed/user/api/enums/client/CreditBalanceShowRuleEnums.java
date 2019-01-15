package com.newhope.moneyfeed.user.api.enums.client;

public enum CreditBalanceShowRuleEnums {

	UN_SHOW("不显示", "UN_SHOW"), 
	SHOW_GROOS_DETAIL("显示总额及明细项", "SHOW_GROOS_DETAIL"),
	SHOW_GROOS("显示总额", "SHOW_GROOS");

	private String desc;

	private String value;

	private CreditBalanceShowRuleEnums(String desc, String value) {
		this.desc = desc;
		this.value = value;
	}
	
	public String getDesc() {
		return desc;
	}

	public String getValue() {
		return value;
	}}
