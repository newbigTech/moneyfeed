package com.newhope.moneyfeed.user.web.controller;

import org.apache.commons.lang.StringUtils;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;

public abstract class AbstractController {
	
	protected <T> BaseResponse<T> buildJson(int code, String msg, T data) {
		BaseResponse<T> baseRes = new BaseResponse<T>();
		boolean ret = ResultCode.SUCCESS.getCode()==code;
		baseRes.setSuccess(ret);
		baseRes.setCode(code);
		baseRes.setMsg(msg);
		baseRes.setSuccess(ret);
		if (ret) {
			baseRes.setData(data);
		}
		return baseRes;
	}
	
	protected <T> BaseResponse<T> buildJsonWithData(int code, String msg, T data) {
		BaseResponse<T> baseRes = new BaseResponse<T>();
		boolean ret = ResultCode.SUCCESS.getCode()==code;
		baseRes.setSuccess(ret);
		baseRes.setCode(code);
		baseRes.setMsg(msg);
		baseRes.setSuccess(ret);
		baseRes.setData(data);
		return baseRes;
	}
	protected <T> BaseResponse<T> buildJson(ResultCode resultCode) {
		BaseResponse<T> baseRes = new BaseResponse<T>();
		boolean ret = ResultCode.SUCCESS.name().equals(resultCode.name());
		baseRes.setSuccess(ret);
		baseRes.setCode(resultCode.getCode());
		baseRes.setMsg(resultCode.getDesc());
		return baseRes;
	}
	
	protected <T> BaseResponse<T> buildJson(ResultCode resultCode,String msg) {
		BaseResponse<T> baseRes = new BaseResponse<T>();
		boolean ret = ResultCode.SUCCESS.name().equals(resultCode.name());
		baseRes.setSuccess(ret);
		baseRes.setCode(resultCode.getCode());
		baseRes.setMsg(StringUtils.isBlank(msg)?resultCode.getDesc():msg);
		return baseRes;
	}

	protected <T> BaseResponse<T> buildJson(boolean isSuccess, int code, String msg) {
		BaseResponse<T> baseRes = new BaseResponse<T>();
		baseRes.setSuccess(isSuccess);
		baseRes.setCode(code);
		baseRes.setMsg(msg);
		return baseRes;
	}
	
	protected <T> BaseResponse<T> buildJson(boolean isSuccess, int code, String msg, T data) {
		BaseResponse<T> baseRes = new BaseResponse<T>();
		baseRes.setSuccess(isSuccess);
		baseRes.setCode(code);
		baseRes.setMsg(msg);
		baseRes.setData(data);
		return baseRes;
	}
}
