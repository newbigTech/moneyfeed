package com.newhope.moneyfeed.user.api.enums.businessmanage;


public enum BmRoleCodeEnums{

	ADMIN("管理员", "10001"),
	SHOP_ADMIN("管理员", "10002"),
	QUALITY_CONTROL ("品管", "10003"),
	INSIDE_JOB("内勤", "10004"),
	WAREHOUSE("库管", "10005"),
	ACCOUNTANT("会计", "10006");

	private String desc;

	private String value;

	private BmRoleCodeEnums(String desc, String value) {
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
		for (BmRoleCodeEnums appCode : BmRoleCodeEnums.values()) {
			if (appCode.name().equals(enumName)) {
				return true;
			}
		}
		return false;
	}
}
