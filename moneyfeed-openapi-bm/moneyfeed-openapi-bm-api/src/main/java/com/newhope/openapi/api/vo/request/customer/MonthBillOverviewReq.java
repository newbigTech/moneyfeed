package com.newhope.openapi.api.vo.request.customer;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class MonthBillOverviewReq {
	
	@NotNull(message="商户id为空")
	@ApiModelProperty(name="shopId", notes="商户id", required=true)
	private Long shopId;
	
	@ApiModelProperty(name="year", notes="年份", required=false)
	private Integer year;
	
	@ApiModelProperty(name="month", notes="月份", required=false)
	private Integer month;
	
	@NotBlank(message="客户编码")
	@ApiModelProperty(name="ebsCustomerNum", notes="客户编码", required=true)
	private String ebsCustomerNum;
	
	@NotBlank(message="客户姓名")
	@ApiModelProperty(name="ebsCustomerNum", notes="客户编码", required=true)
	private String customerName;

	public Integer getYear() {
		return year;
	}

	public Integer getMonth() {
		return month;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getEbsCustomerNum() {
		return ebsCustomerNum;
	}

	public void setEbsCustomerNum(String ebsCustomerNum) {
		this.ebsCustomerNum = ebsCustomerNum;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


}
