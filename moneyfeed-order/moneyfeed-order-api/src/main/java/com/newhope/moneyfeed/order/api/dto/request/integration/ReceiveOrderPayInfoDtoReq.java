package com.newhope.moneyfeed.order.api.dto.request.integration;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("更新订单支付信息")
public class ReceiveOrderPayInfoDtoReq implements Serializable {
	
	private static final long serialVersionUID = -5074680387672101321L;

	@ApiModelProperty(name="ebsorderNo",notes="EBS订单编码")
	private String ebsorderNo;
	
	@ApiModelProperty(name="result",notes="支付处理是否成功")
	private boolean result;
	
	@ApiModelProperty(name="remark",notes="支付结果（失败原因）")
	private String remark;

	public String getEbsorderNo() {
		return ebsorderNo;
	}

	public void setEbsorderNo(String ebsorderNo) {
		this.ebsorderNo = ebsorderNo;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
