package com.newhope.moneyfeed.integration.api.enums.third;


public enum ThirdServiceCodeEnums{
	SYN_MEMBER("同步会员", "SYN_MEMBER"),
	SYN_PIGFARM("同步猪场", "SYN_PIGFARM"),
	SYN_SELLPLAN("同步上市计划", "SYN_SELLPLAN"),
	SYN_PRODUCTPRICE("同步定价信息", "SYN_PRODUCTPRICE"),
	SYN_EBS_USED_HONGBAO("同步EBS使用的红包", "SYN_EBS_USED_HONGBAO"),
	SYN_DIEPLAN("同步死淘计划", "SYN_DIEPLAN"); 
	private String desc;

	private String value;

	private ThirdServiceCodeEnums(String desc, String value) {
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
