package com.newhope.moneyfeed.order.api.dto.response.base;

import java.io.Serializable;

public class OrderSysParamDtoResult implements Serializable {


	private static final long serialVersionUID = -8175167401528241804L;

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
