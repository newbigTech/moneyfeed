package com.newhope.moneyfeed.integration.api.dto.request.ebs.bill;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.newhope.moneyfeed.api.dto.request.PageDtoReq;
import com.newhope.moneyfeed.api.vo.request.PageReq;

import io.swagger.annotations.ApiModelProperty;

public class EbsCustomerBillDetailPageDtoReq extends PageDtoReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2526687501399476145L;

	@ApiModelProperty(name="orgId", value="公司ID", required=true)
    private String orgId;
    
	@ApiModelProperty(name="customerNo", value="客户编码", required=true)
    private String customerNo;
    
	@ApiModelProperty(name="year", value="年份", required=true)
    private Integer year;

	@ApiModelProperty(name="month", value="月份", required=true)
    private Integer month;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
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

}
