package com.newhope.moneyfeed.order.api.dto.response.base;

import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

public class OrderSysParamListDtoResult extends DtoResult {


	private static final long serialVersionUID = -8736402131417076042L;
	
	private List<OrderSysParamDtoResult> params;

	public List<OrderSysParamDtoResult> getParams() {
		return params;
	}

	public void setParams(List<OrderSysParamDtoResult> params) {
		this.params = params;
	}

}
