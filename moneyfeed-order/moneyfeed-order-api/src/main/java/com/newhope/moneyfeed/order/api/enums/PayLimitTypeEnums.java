package com.newhope.moneyfeed.order.api.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum PayLimitTypeEnums {
	/**
	 * 下单后一定时间内完成支付
	 */
	LIMIT_TIME_CREATE_END("下单后一定时间内完成支付", "LIMIT_TIME_CREATE_END"),
	/**
	 * 拉料前一定时间内完成支付
	 */
	LIMIT_TIME_TRANSPORT("拉料前一定时间内完成支付", "LIMIT_TIME_TRANSPORT"),
	/** 核价后一定时间内完成支付 */
	LIMIT_TIME_PRICE("核价后一定时间内完成支付", "LIMIT_TIME_PRICE");

	private String value;

	private String desc;

	PayLimitTypeEnums(String desc, String value) {
		this.desc = desc;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static Map<String, String> getEnumsMap() {
		Map<String, String> map = new LinkedHashMap<>();
		for (OrderStatusEnum type : OrderStatusEnum.values()) {
			map.put(type.getValue(), type.getDesc());
		}
		return map;
	}
}
