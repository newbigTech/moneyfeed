package com.newhope.moneyfeed.feedback.api.enums;

import org.apache.commons.lang3.StringUtils;

public enum MessageLabelTypeEnum {
	
	SHOP_TYPE("商户问题","shop_type"),
	SYSTEM_TYPE("系统问题","system_type");
	
	private String desc;
	private String code;
	
	private MessageLabelTypeEnum(String desc, String code) {
		this.desc = desc;
		this.code = code;
	}


	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	 public static String getDescByCode(String code) {
	        for (MessageLabelTypeEnum result : MessageLabelTypeEnum.values()) {
	            if (StringUtils.equals(result.getCode(), code)) {
	                return result.getDesc();
	            }
	        }
	        return "";
	    }
}