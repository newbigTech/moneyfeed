package com.newhope.moneyfeed.feedback.api.enums;

/**
 * 数据状态枚举
 * normal正常,disable禁用,delete删除
 * @author Dave Chen
 *
 */
public enum DatastatusCodeEnums {

	normal("正常", "normal"),
	disable("禁用", "disable"),
	delete("删除", "delete");
	
	private String desc;
	private String code;
	
	public String getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}

	DatastatusCodeEnums(String desc, String code) {
		this.desc = desc;
		this.code = code;
	}

	public static String getDescByCode(String code) {
		if (code == null)
			return null;
		for (DatastatusCodeEnums se : DatastatusCodeEnums.values()) {
			if (se.code.equals(code)) {
				return se.desc;
			}
		}
		return null;
	}
}
