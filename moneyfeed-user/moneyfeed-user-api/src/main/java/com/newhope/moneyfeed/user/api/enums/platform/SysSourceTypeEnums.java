package com.newhope.moneyfeed.user.api.enums.platform;

public enum SysSourceTypeEnums {

	CLIENT("客户端", "CLIENT"), 
	PLATFORM("平台端", "PLATFORM"),
	BUSINESS("商户端", "BUSINESS");
	private String desc;

	private String value;

	private SysSourceTypeEnums(String desc, String value) {
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
