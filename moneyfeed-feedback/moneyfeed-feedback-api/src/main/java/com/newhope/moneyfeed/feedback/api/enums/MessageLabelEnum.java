package com.newhope.moneyfeed.feedback.api.enums;

import org.apache.commons.lang3.StringUtils;

public enum MessageLabelEnum {
	
	//商户问题
	PRODUCT_QUALITY_PROBLEM("产品质量有问题","product_quality_problem"),
	ORDER_PROBLEM("我的订单有问题","order_problem"),
	ACCOUNT_PROBLEM("我的账目不对","account_problem"),
	NOT_UNDERSTANDING_DISCOUNT_POLICY("不明白折扣政策","not_understanding_discount_policy"),
	POOR_SERVICE("服务态度太差","poor_service"),
	
	//系统问题
	TOO_HARD("操作太难了","too_hard"),
	UNMET_DEMAND("系统太简单满足不了业务需求", "unmet_demand"),
	SYSTEM_ALWAYS_WRONG("系统经常出错","system_always_wrong");
	
	private String desc;
	private String code;
	
	private MessageLabelEnum(String desc, String code) {
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
	        for (MessageLabelEnum result : MessageLabelEnum.values()) {
	            if (StringUtils.equals(result.getCode(), code)) {
	                return result.getDesc();
	            }
	        }
	        return "";
	    }
}
