package com.newhope.moneyfeed.order.api.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 商城、中台回调支付类型
 * @author hp
 *
 */
public enum PayTypeEnums {
	
	BAL_PAY("余额支付", "BAL_PAY"),
	CARD_PAY("银行卡支付", "CARD_PAY"),
	SO_PAY("订单充值", "SO_PAY"),
	ACC_PAY("账户充值", "ACC_PAY"),
	;

	private String value;

	private String desc;

	PayTypeEnums(String desc, String value) {
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
