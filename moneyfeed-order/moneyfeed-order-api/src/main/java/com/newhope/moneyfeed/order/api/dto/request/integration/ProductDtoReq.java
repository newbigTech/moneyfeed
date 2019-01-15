package com.newhope.moneyfeed.order.api.dto.request.integration;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("订单关联商品信息")
public class ProductDtoReq implements Serializable {
	
	private static final long serialVersionUID = 7807714095207130976L;

	@ApiModelProperty(name="suppliesCode",notes="EBS物料编码")
	private String suppliesCode;
	
	@ApiModelProperty(name="productCount",notes="数量")
	private BigDecimal productCount;
	
	@ApiModelProperty(name="totalOrginalAmount",notes="厂价")
	private BigDecimal totalOrginalAmount;
	
	@ApiModelProperty(name="totalDiscountAmount",notes="折扣价")
	private BigDecimal totalDiscountAmount;

	@ApiModelProperty(name="totalPayAmount",notes="实付金额")
	private BigDecimal totalPayAmount;
	
	@ApiModelProperty(name="unit",notes="单位")
	private String unit;
	
	@ApiModelProperty(name="organizationCode",notes="库存组织编码")
	private String organizationCode;

	public String getSuppliesCode() {
		return suppliesCode;
	}

	public void setSuppliesCode(String suppliesCode) {
		this.suppliesCode = suppliesCode;
	}

	public BigDecimal getProductCount() {
		return productCount;
	}

	public void setProductCount(BigDecimal productCount) {
		this.productCount = productCount;
	}

	public BigDecimal getTotalOrginalAmount() {
		return totalOrginalAmount;
	}

	public void setTotalOrginalAmount(BigDecimal totalOrginalAmount) {
		this.totalOrginalAmount = totalOrginalAmount;
	}

	public BigDecimal getTotalDiscountAmount() {
		return totalDiscountAmount;
	}

	public void setTotalDiscountAmount(BigDecimal totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}

	public BigDecimal getTotalPayAmount() {
		return totalPayAmount;
	}

	public void setTotalPayAmount(BigDecimal totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	
}
