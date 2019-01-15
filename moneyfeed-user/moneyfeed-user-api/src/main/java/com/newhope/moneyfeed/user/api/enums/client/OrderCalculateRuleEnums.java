package com.newhope.moneyfeed.user.api.enums.client;

public enum OrderCalculateRuleEnums {

	SYETEM_CALCULATE("自动计算", "SYETEM_CALCULATE"), 
	PERSON_CALCULATE("人工审核", "PERSON_CALCULATE");

	private String desc;

	private String value;

	private OrderCalculateRuleEnums(String desc, String value) {
		this.desc = desc;
		this.value = value;
	}
	
	public String getDesc() {
		return desc;
	}

	public String getValue() {
		return value;
	}}
