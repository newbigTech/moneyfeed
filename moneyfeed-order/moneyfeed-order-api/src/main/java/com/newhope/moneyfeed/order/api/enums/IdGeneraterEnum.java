package com.newhope.moneyfeed.order.api.enums;

/**
 * ID生成器分类定义,见SystemParameter
 *
 */
public enum IdGeneraterEnum {
	ORDER_NUMBER("ORDER_NUMBER", "11", "100100"),
	PAY_NUMBER("PAY_NUMBER", "12", "100100"),;
	
	private String value;
	private String code;
	private String initValue;
	
	IdGeneraterEnum(String code, String value, String initValue) {
		this.code = code;
		this.value = value;
		this.initValue = initValue;
	}
	
	public String getCode() {
		return this.code;
	}
	public String getValue() {
		return this.value;
	}
	public String getInitValue() {
		return this.initValue;
	}
}
