package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户中心-ebs公司组织表
 */
public class UcEbsOrganizesModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1238267540726012992L;

	/** 组织ID */
    private String orgId;

    /** 公司编码 */
    private String shortCode;

    /** 公司名称 */
    private String orgName;

    /** 公司状态 */
    private Boolean enable;

    /** 公司地址 */
    private String orgAddress;

    /** 联系电话 */
    private String mobile;

    /** 导航地址 */
    private String mapAddress;

    /** 纬度 */
    private String mapLat;

    /** 经度 */
    private String mapLnt;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
}