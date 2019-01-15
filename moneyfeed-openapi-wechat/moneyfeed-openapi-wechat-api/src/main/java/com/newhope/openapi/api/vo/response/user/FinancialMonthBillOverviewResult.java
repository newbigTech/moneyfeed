package com.newhope.openapi.api.vo.response.user;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class FinancialMonthBillOverviewResult implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1602632800590677831L;

	@ApiModelProperty("年份")
	private Integer year;
	
	@ApiModelProperty("月份")
	private Integer month;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
	
}
