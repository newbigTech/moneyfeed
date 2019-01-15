package com.newhope.openapi.api.vo.request.customer;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import com.newhope.moneyfeed.api.vo.request.PageReq;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class FinancialBillQueryReq extends PageReq{

	private static final long serialVersionUID = -5616652032725168941L;
	
	@NotNull(message="店铺Id为空")
	@ApiModelProperty(name="shopId", notes="店铺Id", required=true)
	public Long shopId;
	
	@NotBlank(message="客户编号为空")
	@ApiModelProperty(name="customerNum", notes="客户编号", required=true)
	public String customerNum;
	
	@NotBlank(message="客户名称为空")
	@ApiModelProperty(name="customerName", notes="客户名称", required=true)
	public String customerName;
	
	@NotNull(message="年份为空")
	@ApiModelProperty(name="year", notes="年份", required=true)
	public Integer year;
	
	@NotNull(message="月份为空")
	@ApiModelProperty(name="month", notes="月份", required=true)
	public Integer month;

	public Long getShopId() {
		return shopId;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public void setYear(Integer year) {
		this.year = year;
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
	
}
