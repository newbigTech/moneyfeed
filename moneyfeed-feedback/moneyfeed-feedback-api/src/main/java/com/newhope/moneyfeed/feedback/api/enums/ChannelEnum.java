package com.newhope.moneyfeed.feedback.api.enums;

import org.apache.commons.lang3.StringUtils;

public enum ChannelEnum {
	
	OFFICIAL_ACCOUNTS("公众号","official_accounts"),
	MALL("商城","mall");
	
	private String desc;
	private String code;
	
	private ChannelEnum(String desc, String code) {
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
	        for (ChannelEnum result : ChannelEnum.values()) {
	            if (StringUtils.equals(result.getCode(), code)) {
	                return result.getDesc();
	            }
	        }
	        return "";
	    }
}
