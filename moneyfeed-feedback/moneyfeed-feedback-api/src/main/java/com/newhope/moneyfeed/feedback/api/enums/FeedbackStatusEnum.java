package com.newhope.moneyfeed.feedback.api.enums;

import org.apache.commons.lang3.StringUtils;

public enum FeedbackStatusEnum {
	
	DISTRIBUTED("待分配","distributed"),
	PENDING("待处理","pending"),
	PLATFORM_CLOSED("平台完结","platform_closed"),
	SHOP_CLOSED("商户完结","shop_closed");
	
	private String desc;
	private String code;
	
	private FeedbackStatusEnum(String desc, String code) {
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
	        for (FeedbackStatusEnum result : FeedbackStatusEnum.values()) {
	            if (StringUtils.equals(result.getCode(), code)) {
	                return result.getDesc();
	            }
	        }
	        return "";
	    }
}
