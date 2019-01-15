package com.newhope.moneyfeed.user.api.enums.client;

public enum CustomerPropertyTypeEnums {

	ALLOW_ONLINE_BUSINESS("允许线上交易", "ALLOW_ONLINE_BUSINESS"), 
	ALLOW_OFFLINE_BUSINESS("允许线下交易", "ALLOW_OFFLINE_BUSINESS"), 
	CREDIT_BALANCE_SHOW_RULE("可用额度显示规则", "CREDIT_BALANCE_SHOW_RULE"),
	ORDER_CALCULATE_RULE("订单金额计算规则", "ORDER_CALCULATE_RULE"), 
	BILL_SHOW_RULE("账单显示设置规则", "BILL_SHOW_RULE"), 
	DAYBOOK_ONLINE_SHOW_RULE("线上流水显示设置规则", "DAYBOOK_ONLINE_SHOW_RULE"),
	MONTH_BILL_SHOW_RULE("月度对账单显示设置规则", "MONTH_BILL_SHOW_RULE");
	private String desc;

	private String value;

	private CustomerPropertyTypeEnums(String desc, String value) {
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
