package com.newhope.moneyfeed.integration.api.dto.request.third;

import java.io.Serializable;

public class EbsEntranceDtoReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8036550069853034511L;

	/***
	 * 操作类型
	 */
	private String opertypes;
	
	/**
	 *业务数据参数 
	 */
	private String data;

	public String getOpertypes() {
		return opertypes;
	}

	public void setOpertypes(String opertypes) {
		this.opertypes = opertypes;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
