package com.newhope.moneyfeed.integration.api.vo.request.third;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class SynPigFarmReq implements Serializable {

	private static final long serialVersionUID = 1827457593591253353L;

	@NotBlank(message="thirdId为必传参数")
	@ApiModelProperty(name = "thirdId", notes = "业务系统主键id", required = true)
	private String thirdId;
	
	@NotBlank(message="addressProvCode为必传参数")
	@ApiModelProperty(name = "addressProvCode", notes = "猪场地址所在省id", required = true)
	private String addressProvCode;
	
	@NotBlank(message="addressCityCode为必传参数")
	@ApiModelProperty(name = "addressCityCode", notes = "猪场地址所在市id", required = true)
	private String addressCityCode;
	
	@ApiModelProperty(name = "addressCountyCode", notes = "猪场地址所在区县id", required = true)
	private String addressCountyCode;
	@ApiModelProperty(name = "addressTownCode", notes = "猪场地址所在乡镇id")
	private String addressTownCode;
	@ApiModelProperty(name = "addressVillageCode", notes = "猪场地址所在村id")
	private String addressVillageCode;
	@ApiModelProperty(name = "addressGroupCode", notes = "猪场地址所在组id")
	private String addressGroupCode;
	@ApiModelProperty(name = "address", notes = "猪场详细地址")
	private String address;
	@ApiModelProperty(name = "remarks", notes = "备注")
	private String remarks;
	@ApiModelProperty(name = "mapAddress", notes = "地图详细地址")
	private String mapAddress;
	@ApiModelProperty(name = "lat", notes = "纬度")
	private String lat;
	@ApiModelProperty(name = "lng", notes = "经度")
	private String lng;
	@ApiModelProperty(name = "trifficInfo", notes = "交通情况")
	private String trifficInfo;
	
	@NotBlank(message="name为必传参数")
	@ApiModelProperty(name = "name", notes = "猪场名称", required = true)
	
	private String name;
	@ApiModelProperty(name = "limitHeight", notes = "限高")
	private String limitHeight;
	@ApiModelProperty(name = "limitLength", notes = "限长")
	private String limitLength;
	
	@ApiModelProperty(name = "mobile", notes = "联系手机")
	private String mobile;
	
	@ApiModelProperty(name = "contactPerson", notes = "猪场联系人")
	private String contactPerson;
	
	@NotBlank(message="orgId为必传参数")
	@ApiModelProperty(name = "orgId", notes = "组织id", required = true)
	private String orgId;

	public String getThirdId() {
		return thirdId;
	}

	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}

	public String getAddressProvCode() {
		return addressProvCode;
	}

	public void setAddressProvCode(String addressProvCode) {
		this.addressProvCode = addressProvCode;
	}

	public String getAddressCityCode() {
		return addressCityCode;
	}

	public void setAddressCityCode(String addressCityCode) {
		this.addressCityCode = addressCityCode;
	}

	public String getAddressCountyCode() {
		return addressCountyCode;
	}

	public void setAddressCountyCode(String addressCountyCode) {
		this.addressCountyCode = addressCountyCode;
	}

	public String getAddressTownCode() {
		return addressTownCode;
	}

	public void setAddressTownCode(String addressTownCode) {
		this.addressTownCode = addressTownCode;
	}

	public String getAddressVillageCode() {
		return addressVillageCode;
	}

	public void setAddressVillageCode(String addressVillageCode) {
		this.addressVillageCode = addressVillageCode;
	}

	public String getAddressGroupCode() {
		return addressGroupCode;
	}

	public void setAddressGroupCode(String addressGroupCode) {
		this.addressGroupCode = addressGroupCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMapAddress() {
		return mapAddress;
	}

	public void setMapAddress(String mapAddress) {
		this.mapAddress = mapAddress;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getTrifficInfo() {
		return trifficInfo;
	}

	public void setTrifficInfo(String trifficInfo) {
		this.trifficInfo = trifficInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLimitHeight() {
		return limitHeight;
	}

	public void setLimitHeight(String limitHeight) {
		this.limitHeight = limitHeight;
	}

	public String getLimitLength() {
		return limitLength;
	}

	public void setLimitLength(String limitLength) {
		this.limitLength = limitLength;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}
