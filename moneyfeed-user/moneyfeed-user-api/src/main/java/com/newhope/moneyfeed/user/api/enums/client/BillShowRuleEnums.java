package com.newhope.moneyfeed.user.api.enums.client;

public enum BillShowRuleEnums {

	UN_SHOW("不显示", "UN_SHOW"), 
	SHOW_PANDECT_DETAIL("显示概况及明细", "SHOW_PANDECT_DETAIL"),
	SHOW_PANDECT("显示概况", "SHOW_PANDECT");
	private String desc;

	private String value;

	private BillShowRuleEnums(String desc, String value) {
		this.desc = desc;
		this.value = value;
	}
	
	public String getDesc() {
		return desc;
	}

	public String getValue() {
		return value;
	}}
