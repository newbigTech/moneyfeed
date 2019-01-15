package com.newhope.moneyfeed.api.enums;


public enum AppCodeTypeEnums {

	APP1("农资商城", "APP1");

	private String desc;

	private String value;

	private AppCodeTypeEnums(String desc, String value) {
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
		for (AppCodeTypeEnums appCode : AppCodeTypeEnums.values()) {
			if (appCode.name().equals(enumName)) {
				return true;
			}
		}
		return false;
	}
}
