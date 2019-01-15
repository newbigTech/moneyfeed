package com.newhope.moneyfeed.integration.api.enums.third;

public enum EbsOperTypeEnums {

	HONGBAO_AMOUNT("查询客户红包信息","HONGBAO_AMOUNT"),
	USED_HONGBAO("使用客户红包","USED_HONGBAO"),

	BACK_HONGBAO("退还红包","BACK_HONGBAO");


	String desc;
	
	String value;
	
	private EbsOperTypeEnums(String desc, String value) {
		this.desc = desc;
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public String getValue() {
		return value;
	}
	public static EbsOperTypeEnums getByName(String value) {
		for (EbsOperTypeEnums ebsOperTypeEnums : EbsOperTypeEnums.values()) {
			if (ebsOperTypeEnums.getValue().equals(value)) {
				return ebsOperTypeEnums;
			}
		}
		return null;
	}
}
