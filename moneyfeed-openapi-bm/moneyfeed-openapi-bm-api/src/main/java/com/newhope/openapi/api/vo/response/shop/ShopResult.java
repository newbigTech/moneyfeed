package com.newhope.openapi.api.vo.response.shop;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class ShopResult extends Result {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1955759354644855004L;

	@ApiModelProperty(name = "id", notes = "主键店铺Id")
	private Long id;

	@ApiModelProperty(name = "shopCode", notes = "店铺编号,自动生成，以S开始+6行政区码+6位流水号")
    private String shopCode;

	@ApiModelProperty(name = "name", notes = "店铺名称")
    private String name;

	@ApiModelProperty(name = "contactPerson", notes = "联系人")
    private String contactPerson;

    @ApiModelProperty(name = "contactTel", notes = "联系电话")
    private String contactTel;

    @ApiModelProperty(name = "intro", notes = "店铺简介")
    private String intro;

    @ApiModelProperty(name = "enable", notes = "是否可用:true-可用，false-不可用")
    private Boolean enable;

    @ApiModelProperty(name = "ebsOrgId", notes = "ebs组织主键id")
    private Long ebsOrgId;

    @ApiModelProperty(name = "address", notes = "地址")
    private String address;

    @ApiModelProperty(name = "mapAddress", notes = "导航地址")
    private String mapAddress;

    @ApiModelProperty(name = "mapLat", notes = "纬度")
    private String mapLat;

    @ApiModelProperty(name = "mapLnt", notes = "经度")
    private String mapLnt;

	public Long getId() {
		return id;
	}

	public String getShopCode() {
		return shopCode;
	}

	public String getName() {
		return name;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public String getContactTel() {
		return contactTel;
	}

	public String getIntro() {
		return intro;
	}

	public Boolean getEnable() {
		return enable;
	}

	public Long getEbsOrgId() {
		return ebsOrgId;
	}

	public String getAddress() {
		return address;
	}

	public String getMapAddress() {
		return mapAddress;
	}

	public String getMapLat() {
		return mapLat;
	}

	public String getMapLnt() {
		return mapLnt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public void setEbsOrgId(Long ebsOrgId) {
		this.ebsOrgId = ebsOrgId;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setMapAddress(String mapAddress) {
		this.mapAddress = mapAddress;
	}

	public void setMapLat(String mapLat) {
		this.mapLat = mapLat;
	}

	public void setMapLnt(String mapLnt) {
		this.mapLnt = mapLnt;
	}
    
}
