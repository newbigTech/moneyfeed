package com.newhope.moneyfeed.integration.api.vo.request.ebs.bill;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "inv:P_REQUEST_LINE_ITEM")
public class CustomerBillVoReq {

	private String orgId;

    private String beginTime;

    private String endTime;

    @XmlElement(name = "inv:VALUE1")
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@XmlElement(name = "inv:VALUE2")
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	@XmlElement(name = "inv:VALUE3")
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
    
    
    
}
