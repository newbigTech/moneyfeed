package com.newhope.moneyfeed.integration.api.dto.request.ebs.inv;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "inv:P_REQUEST_LINE_ITEM")
public class EbsItemOnhandDtoReq implements Serializable{

	private static final long serialVersionUID = -5186727010342500824L;	
	
	@ApiModelProperty(name="organizationCode", notes="库存组织编码", required=true)
	private String organizationCode;//库存组织编码
	
	@ApiModelProperty(name="itemCode", notes="物料编码", required=true)
	private String itemCode;//物料编码
	
	@XmlElement(name = "inv:VALUE1")
	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	@XmlElement(name = "inv:VALUE2")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	

}
