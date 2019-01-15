package com.newhope.openapi.api.vo.response.customer;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class FinancialMonthBillOverviewResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5448670406176463385L;

	@ApiModelProperty("年份")
	private Integer year;
	
	@ApiModelProperty("月份")
	private Integer month;
	
	@ApiModelProperty("客户姓名")
	private String customerName;
	
	@ApiModelProperty("预览地址")
	private String previewUrl;
	
	@ApiModelProperty("组织机构Id")
    private String orgId;
	
	@ApiModelProperty("客户编号")
    private String customerNo;
	
	@ApiModelProperty("所属商户")
    private String shopName;

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOrgId() {
		return orgId;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	
}
