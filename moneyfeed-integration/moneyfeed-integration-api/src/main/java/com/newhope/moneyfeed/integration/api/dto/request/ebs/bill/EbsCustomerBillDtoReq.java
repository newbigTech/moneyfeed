package com.newhope.moneyfeed.integration.api.dto.request.ebs.bill;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class EbsCustomerBillDtoReq implements Serializable{

	private static final long serialVersionUID = 4869550036384430425L;

	@ApiModelProperty(name="orgId", value="公司ID", required=true)
    private String orgId;
    
	@ApiModelProperty(name="customerNo", value="客户编码", required=true)
    private String customerNo;
    
	@ApiModelProperty(name="year", value="年份", required=true)
    private Integer year;

	@ApiModelProperty(name="month", value="月份", required=true)
    private Integer month;

	@XmlElement(name = "inv:VALUE1")
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@XmlElement(name = "inv:VALUE2")
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	@XmlElement(name = "inv:VALUE3")
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}

	@XmlElement(name = "inv:VALUE4")
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	
	
}
