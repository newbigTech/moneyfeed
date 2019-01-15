package com.newhope.moneyfeed.integration.api.dto.response.ebs.inv;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "HERDER")
public class EbsItemOnhandDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3326867095018840223L;

	@ApiModelProperty(name="organizationCode", notes="库存组织编码", required=true)
	private String organizationCode;
	
	@ApiModelProperty(name="itemCode", notes="物料编码", required=true)
	private String itemCode;
	
	@ApiModelProperty(name="itemDesc", notes="物料描述", required=true)
	private String itemDesc;

	@ApiModelProperty(name="reservableQty", notes="可保留量", required=true)
	private BigDecimal reservableQty;//可保留量
	
	
	@XmlElement(name = "ORGANIZATION_CODE")
	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	@XmlElement(name = "ITEM_CODE")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@XmlElement(name = "ITEM_DES")
	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	@XmlElement(name = "RESERVABLE_QTY")
	public BigDecimal getReservableQty() {
		return reservableQty;
	}

	public void setReservableQty(BigDecimal reservableQty) {
		this.reservableQty = reservableQty;
	}
	
}
