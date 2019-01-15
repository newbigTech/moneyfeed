package com.newhope.moneyfeed.order.api.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum OrderOperationTypeEnums {
	/**
	 * 新建订单
	 */
	CREATE_ORDER("新建订单", "CREATE_ORDER"),
	/**
	 * 修改订单
	 */
	UPDATE_ORDER("修改订单", "UPDATE_ORDER"),
	/**
	 * 删除订单
	 */
	DELETE_ORDER("删除订单", "DELETE_ORDER"),
	/**
	 * EBS更改订单
	 */
	EBS_MODIFY_ORDER("EBS更改订单", "EBS_MODIFY_ORDER"),
	CANCEL_ORDER_FAIL("取消订单失败", "CANCEL_ORDER_FAIL"),
	ENTERED("EBS已输入，已创建，待支付","ENTERED"),
    EBS_ORDER_CREATE_FAIL("EBS订单创建失败","EBS_ORDER_CREATE_FAIL"),
    BOOKED("EBS已登记，已收款，待拉货","BOOKED"),
    EBS_PAY_FAIL("EBS支付失败","EBS_PAY_FAIL"),
    EBS_CANCEL("EBS主动取消","EBS_CANCEL"),
    MONEYFEED_CANCEL_SUCCESS("商城主动取消成功","MONEYFEED_CANCEL_SUCCESS"),
    MONEYFEED_CANCEL_FAIL("商城主动取消失败","MONEYFEED_CANCEL_FAIL"),
    EBS_CLOSED("EBS已关闭","EBS_CLOSED");

	private String value;

	private String desc;

	OrderOperationTypeEnums(String desc, String value) {
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
