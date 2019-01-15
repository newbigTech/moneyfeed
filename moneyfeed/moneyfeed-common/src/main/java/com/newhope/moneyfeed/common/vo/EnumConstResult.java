package com.newhope.moneyfeed.common.vo;


import java.io.Serializable;

public class EnumConstResult implements Serializable {

	private static final long serialVersionUID = -5830478717971358638L;
	
	private String name;
	private String value;
	private String desc;
	
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
