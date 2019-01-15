package com.newhope.moneyfeed.user.api.enums.platform;

public enum ManagerRoleCodeEnums {

	CLIENT("客户端管理员", "30001"), 
	PLATFORM("平台端管理员", "10001"),
	BUSINESS("商户端管理员", "10002");
	private String desc;

	private String value;

	private ManagerRoleCodeEnums(String desc, String value) {
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
