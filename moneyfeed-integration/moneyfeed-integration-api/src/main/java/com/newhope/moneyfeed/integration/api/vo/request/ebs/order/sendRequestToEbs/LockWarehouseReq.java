package com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "inv:P_REQUEST_LINE_ITEM")
public class LockWarehouseReq implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7937397828746147213L;
	
	@ApiModelProperty(name = "orgId", value = "公司Id(EBS系统中)")
    private String orgId;
	
	@ApiModelProperty(name = "requestDate", value = "需求日期")
	private String requestDate;

	@XmlElement(name = "inv:VALUE1")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@XmlElement(name = "inv:VALUE2")
	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
		
}
