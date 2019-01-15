package com.newhope.moneyfeed.user.api.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum ResourceTypeEnums {
	
	MENU("菜单", "MENU"),
	BUTTON("按钮", "BUTTON");
	
	private String desc;

	private String value;

	private ResourceTypeEnums(String desc, String value) {
		this.desc = desc;
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public String getValue() {
		return value;
	}
	
	public static Map<String, String> getEnumsMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (ResourceTypeEnums type : ResourceTypeEnums.values()) {
			map.put(type.getValue(), type.getDesc());
		}
		return map;
	}
}
