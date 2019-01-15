package com.newhope.moneyfeed.integration.api.vo.request.ebs.bill;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "inv:P_REQUEST_LINE_ITEM")
public class CustomerBillDetailVoReq {

    private String orgId;
    
    private String customerNo;
    
    private Integer year;

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
