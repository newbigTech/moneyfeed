package com.newhope.moneyfeed.order.api.dto.request.shop;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class UcShopDtoReq implements Serializable {

	private static final long serialVersionUID = 5270508086225284019L;

	@ApiModelProperty(name = "ucShopName", notes = "商户名称")
	private String ucShopName;

	@ApiModelProperty(name = "ucShopMobile", notes = "商户电话")
	private String ucShopMobile;

	@ApiModelProperty(name = "ucShopId", notes = "商户店铺ID")
	private Long ucShopId;

	@ApiModelProperty(name = "ucShopAddress", notes = "商户地址")
	private String ucShopAddress;

	@ApiModelProperty(name = "ucShopType", notes = "商户类型")
	private String ucShopType;
	
	@ApiModelProperty(name = "cusOrderType", notes = "订单金额计算规则")
	private String cusOrderType;
	
	@ApiModelProperty(name = "orgId", notes = "ebs组织id")
	private String orgId;

	
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCusOrderType() {
		return cusOrderType;
	}

	public void setCusOrderType(String cusOrderType) {
		this.cusOrderType = cusOrderType;
	}

	public String getUcShopName() {
		return ucShopName;
	}

	public void setUcShopName(String ucShopName) {
		this.ucShopName = ucShopName;
	}

	public String getUcShopMobile() {
		return ucShopMobile;
	}

	public void setUcShopMobile(String ucShopMobile) {
		this.ucShopMobile = ucShopMobile;
	}

	public Long getUcShopId() {
		return ucShopId;
	}

	public void setUcShopId(Long ucShopId) {
		this.ucShopId = ucShopId;
	}

	public String getUcShopAddress() {
		return ucShopAddress;
	}

	public void setUcShopAddress(String ucShopAddress) {
		this.ucShopAddress = ucShopAddress;
	}

	public String getUcShopType() {
		return ucShopType;
	}

	public void setUcShopType(String ucShopType) {
		this.ucShopType = ucShopType;
	}

}
