package com.newhope.moneyfeed.api.abscontroller;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;


public abstract class AbstractController {
	
	protected <T extends Result> BaseResponse<T> buildJson(T result) {
		BaseResponse<T> baseRes = new BaseResponse<T>();
		boolean ret = ResultCode.SUCCESS.getCode().equals(result.getCode());
		baseRes.setSuccess(ret);
		baseRes.setCode(result.getCode());
		baseRes.setMsg(result.getMsg());
		if(result.getClass()!=(Result.class)) {
			if (ret) {
				baseRes.setData(result);
			}
		}
		return baseRes;
	}

	protected <T extends DtoResult> BaseResponse<T> buildJson(T result) {
		BaseResponse<T> baseRes = new BaseResponse<T>();
		boolean ret = ResultCode.SUCCESS.getCode().equals(result.getCode());
		baseRes.setSuccess(ret);
		baseRes.setCode(result.getCode());
		baseRes.setMsg(result.getMsg());
			if (ret) {
				baseRes.setData(result);
			}
		return baseRes;
	}


	protected <T> BaseResponse<T> buildJson(Integer code,String msg) {
		BaseResponse baseRes = new BaseResponse();
		boolean ret = ResultCode.SUCCESS.getCode().equals(code);
		baseRes.setSuccess(ret);
		baseRes.setCode(code);
		baseRes.setMsg(msg);
		return baseRes;
	}


	protected <T> BaseResponse<T> buildJson(Integer code,String msg,T data) {
		BaseResponse<T> baseRes = new BaseResponse<T>();
		boolean ret = ResultCode.SUCCESS.getCode().equals(code);
		baseRes.setSuccess(ret);
		baseRes.setCode(code);
		baseRes.setMsg(msg);
		baseRes.setData(data);
		return baseRes;
	}

	protected BaseResponse buildSuccessJson(){
		BaseResponse baseRes = new BaseResponse();
		baseRes.setSuccess(true);
		baseRes.setCode(ResultCode.SUCCESS.getCode());
		baseRes.setMsg(ResultCode.SUCCESS.getDesc());
		return baseRes;
	};

	protected  <T extends Result> BaseResponse<T> bulidJsonWithData(T result){
		BaseResponse<T> baseRes = new BaseResponse<T>();
		boolean ret = ResultCode.SUCCESS.getCode().equals(result.getCode());
		baseRes.setSuccess(ret);
		baseRes.setCode(result.getCode());
		baseRes.setMsg(result.getMsg());
		if(result.getClass()!=(Result.class)) {
				baseRes.setData(result);
		}
		return baseRes;
	}


	protected  <T extends DtoResult> BaseResponse<T> bulidJsonWithData(T result){
		BaseResponse<T> baseRes = new BaseResponse<T>();
		boolean ret = ResultCode.SUCCESS.getCode().equals(result.getCode());
		baseRes.setSuccess(ret);
		baseRes.setCode(result.getCode());
		baseRes.setMsg(result.getMsg());
		baseRes.setData(result);
		return baseRes;
	}
}
