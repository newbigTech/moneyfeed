package com.newhope.moneyfeed.api.dto.base;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class BaseDtoResponse<T> implements Serializable {

	
	private static final long serialVersionUID = -474902108348140835L;
	
	@ApiModelProperty(required=true)
	private boolean success;
	@ApiModelProperty(required=false)
	private String msg;
	@ApiModelProperty(required=false)
	private T data;
	@ApiModelProperty(required=true)
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
