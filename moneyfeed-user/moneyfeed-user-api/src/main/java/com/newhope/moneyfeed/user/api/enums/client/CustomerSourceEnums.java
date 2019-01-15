package com.newhope.moneyfeed.user.api.enums.client;

public enum CustomerSourceEnums {

	EBS("ebs客户", "EBS"), 
	SYSTEM("系统客户", "SYSTEM");
	private String desc;

	private String value;

	private CustomerSourceEnums(String desc, String value) {
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
