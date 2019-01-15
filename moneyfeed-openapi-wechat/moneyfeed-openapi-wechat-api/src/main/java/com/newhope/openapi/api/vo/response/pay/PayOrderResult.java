package com.newhope.openapi.api.vo.response.pay;

import java.util.Map;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class PayOrderResult extends Result {

	private static final long serialVersionUID = 1506763763917599920L;
	
	@ApiModelProperty(name = "paramsMap", notes = "需要页面组装的参数")
    public Map<String, String> paramsMap;
	@ApiModelProperty(name = "payUrl", notes = "支付跳转页面")
    public String payUrl;
	public Map<String, String> getParamsMap() {
		return paramsMap;
	}
	public String getPayUrl() {
		return payUrl;
	}
	public void setParamsMap(Map<String, String> paramsMap) {
		this.paramsMap = paramsMap;
	}
	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}
	
}
