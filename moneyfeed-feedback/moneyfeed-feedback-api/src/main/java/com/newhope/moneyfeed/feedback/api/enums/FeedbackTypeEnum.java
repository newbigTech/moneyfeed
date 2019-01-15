package com.newhope.moneyfeed.feedback.api.enums;

import org.apache.commons.lang3.StringUtils;

public enum FeedbackTypeEnum {
	ACCOUNT_NOT_CLEAR("账目不清晰" , "account_not_clear"),
	QUALITY_PROBLEM("质量问题" , "quality_problem"),
	SERVICE_PROBLEM("服务问题" , "service_problem"),
	OTHER("其它" , "other");
	
	private String desc;
	private String code;
	
	private FeedbackTypeEnum(String desc, String code) {
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
	        for (FeedbackTypeEnum result : FeedbackTypeEnum.values()) {
	            if (StringUtils.equals(result.getCode(), code)) {
	                return result.getDesc();
	            }
	        }
	        return "";
	    }
}
