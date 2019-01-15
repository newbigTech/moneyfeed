package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户中心-商户表
 */
public class UcShopModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7729789062040389173L;

	/** 店铺编号,自动生成，以S开始+6行政区码+6位流水号 */
    private String code;

    /** 店铺名称 */
    private String name;

    /** 联系人 */
    private String contactPerson;

    /** 联系电话 */
    private String contactTel;

    /** 店铺简介 */
    private String intro;

    /** 是否可用 */
    private Boolean enable;

    /** ebs组织主键id */
    private String ebsOrgId;

    /** 详细地址 */
    private String address;

    /** 导航地址 */
    private String mapAddress;

    /** 纬度 */
    private String mapLat;

    /** 经度 */
    private String mapLnt;

    /** 商户地址-省 */
    private String province;

    /** 商户地址-市 */
    private String city;

    /** 商户地址-县 */
    private String county;

    /** 店铺状态 */
    private String status;

    /** 管理员id */
    private String manageUserId;

    /** 客服电话 */
    private String hotline;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getEbsOrgId() {
        return ebsOrgId;
    }

    public void setEbsOrgId(String ebsOrgId) {
        this.ebsOrgId = ebsOrgId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMapAddress() {
        return mapAddress;
    }

    public void setMapAddress(String mapAddress) {
        this.mapAddress = mapAddress;
    }

    public String getMapLat() {
        return mapLat;
    }

    public void setMapLat(String mapLat) {
        this.mapLat = mapLat;
    }

    public String getMapLnt() {
        return mapLnt;
    }

    public void setMapLnt(String mapLnt) {
        this.mapLnt = mapLnt;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManageUserId() {
        return manageUserId;
    }

    public void setManageUserId(String manageUserId) {
        this.manageUserId = manageUserId;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }
}