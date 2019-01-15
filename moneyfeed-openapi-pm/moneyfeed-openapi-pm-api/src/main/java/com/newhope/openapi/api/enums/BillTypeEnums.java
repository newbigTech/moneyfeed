package com.newhope.openapi.api.enums;

public enum BillTypeEnums {

	ONLINE("线上", "online"),
	OFFLINE("线下", "offline");

	private String desc;

	private String value;

	private BillTypeEnums(String desc, String value) {
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
		for (BillTypeEnums appCode : BillTypeEnums.values()) {
			if (appCode.name().equals(enumName)) {
				return true;
			}
		}
		return false;
	}
}
