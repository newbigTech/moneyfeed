package com.newhope.moneyfeed.integration.api.dto.request.ebs.balance;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "inv:P_REQUEST_LINE_ITEM")
public class EbsBalanceDtoReq implements Serializable{
	
	private static final long serialVersionUID = 3962807423384223498L;
	
	@ApiModelProperty(name="orgCode", notes="公司编码", required=true)
	private String orgCode;//公司ID
	
	@ApiModelProperty(name="customerCode", notes="客户编码", required=true)
	private String customerCode;//客户编码
	
	@XmlElement(name = "inv:VALUE1")
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	@XmlElement(name = "inv:VALUE2")
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
}
