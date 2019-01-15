package com.newhope.moneyfeed.user.api.enums.client;


public enum AppSourceEnums{

	WECHAT("微信端", "WECHAT"), 
	APP("APP端", "APP"),
	SERVICE("服务端", "SERVICE");

	private String desc;

	private String value;

	private AppSourceEnums(String desc, String value) {
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
		for (AppSourceEnums appCode : AppSourceEnums.values()) {
			if (appCode.name().equals(enumName)) {
				return true;
			}
		}
		return false;
	}
}
