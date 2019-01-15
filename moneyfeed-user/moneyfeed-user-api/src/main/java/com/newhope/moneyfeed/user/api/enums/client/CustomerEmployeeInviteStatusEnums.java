package com.newhope.moneyfeed.user.api.enums.client;

public enum CustomerEmployeeInviteStatusEnums {

	NORMAL("正常", "NORMAL"), 
	NOT_CONFIRMED("待确认", "NOT_CONFIRMED");
	private String desc;

	private String value;

	private CustomerEmployeeInviteStatusEnums(String desc, String value) {
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
