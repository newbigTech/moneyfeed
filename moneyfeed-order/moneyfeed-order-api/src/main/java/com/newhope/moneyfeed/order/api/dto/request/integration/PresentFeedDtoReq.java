package com.newhope.moneyfeed.order.api.dto.request.integration;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("订单关联赠料信息")
public class PresentFeedDtoReq implements Serializable {
	
	private static final long serialVersionUID = -7343834736072609050L;

	@ApiModelProperty(name="suppliesCode",notes="EBS物料编码")
	private String suppliesCode;
	
	@ApiModelProperty(name="presentFeedCount",notes="数量")
	private Integer presentFeedCount;
	
	@ApiModelProperty(name="unit",notes="单位")
	private String unit;

	public String getSuppliesCode() {
		return suppliesCode;
	}

	public void setSuppliesCode(String suppliesCode) {
		this.suppliesCode = suppliesCode;
	}

	public Integer getPresentFeedCount() {
		return presentFeedCount;
	}

	public void setPresentFeedCount(Integer presentFeedCount) {
		this.presentFeedCount = presentFeedCount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
