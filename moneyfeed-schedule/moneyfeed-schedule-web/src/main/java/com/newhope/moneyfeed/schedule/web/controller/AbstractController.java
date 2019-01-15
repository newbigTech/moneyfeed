package com.newhope.moneyfeed.schedule.web.controller;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import org.apache.commons.lang.StringUtils;


public abstract class AbstractController {
	
	protected <T> BaseResponse<T> buildJson(Integer code, String msg, T data) {
		BaseResponse<T> baseRes = new BaseResponse<T>();
		boolean ret = ResultCode.SUCCESS.name().equals(code);
		baseRes.setSuccess(ret);
		baseRes.setCode(code);
		baseRes.setMsg(msg);
		baseRes.setSuccess(ret);
		if (ret) {
			baseRes.setData(data);
		}
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

	protected <T> BaseResponse<T> buildJson(boolean isSuccess, Integer code, String msg) {
		BaseResponse<T> baseRes = new BaseResponse<T>();
		baseRes.setSuccess(isSuccess);
		baseRes.setCode(code);
		baseRes.setMsg(msg);
		return baseRes;
	}
	
	protected <T> BaseResponse<T> buildJson(boolean isSuccess, Integer code, String msg, T data) {
		BaseResponse<T> baseRes = new BaseResponse<T>();
		baseRes.setSuccess(isSuccess);
		baseRes.setCode(code);
		baseRes.setMsg(msg);
		baseRes.setData(data);
		return baseRes;
	}
}
