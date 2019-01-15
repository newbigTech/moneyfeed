package com.newhope.moneyfeed.goods.api.dto.request;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class SkuInitReq {

	@ApiModelProperty(name = "fromDate", notes = "初始化开始时间")
	private Date fromDate;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

}
