package com.newhope.moneyfeed.user.api.enums.client;


public enum AllotCustomerStatusEnums{

	ALLOTTING("待分配", "ALLOTTING"),
	ALLOTTED("已分配", "ALLOTTED");

	private String desc;

	private String value;

	private AllotCustomerStatusEnums(String desc, String value) {
		this.desc = desc;
		this.value = value;
	}
	
	public String getDesc() {
		return desc;
	}

	public String getValue() {
		return value;
	}
	
	public static boolean containsEnum(String enumName) {
		for (AllotCustomerStatusEnums appCode : AllotCustomerStatusEnums.values()) {
			if (appCode.name().equals(enumName)) {
				return true;
			}
		}
		return false;
	}
}
