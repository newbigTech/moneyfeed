package com.newhope.moneyfeed.mq.adapter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Message implements Serializable {
	private static final long serialVersionUID = -2696446453686579784L;
	
	private String code;
	private String name;
	private String content;
	private String appCode;
	private Map<String, String> paramMap;
	private List<String> paramList;
	private Set<String> paramSet;
	
	public Set<String> getParamSet() {
		return paramSet;
	}

	public void setParamSet(Set<String> paramSet) {
		this.paramSet = paramSet;
	}

	public List<String> getParamList() {
		return paramList;
	}

	public void setParamList(List<String> paramList) {
		this.paramList = paramList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, String> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
}
