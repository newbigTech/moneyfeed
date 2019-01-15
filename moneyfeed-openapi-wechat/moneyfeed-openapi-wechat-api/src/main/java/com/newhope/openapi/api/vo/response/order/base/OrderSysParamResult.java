package com.newhope.openapi.api.vo.response.order.base;

import java.io.Serializable;

public class OrderSysParamResult implements Serializable {

	private static final long serialVersionUID = 8824116156965619141L;

	private String name;

	private String value;

	private String code;

	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
