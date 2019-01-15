package com.newhope.moneyfeed.api.vo.response.wechat;

import com.newhope.moneyfeed.api.vo.response.Result;

import java.io.Serializable;
import java.util.Map;

public class RedirectResult extends Result implements Serializable {

	private static final long serialVersionUID = -671861635836744035L;
	
	Map<String, Object> resultMap;

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
