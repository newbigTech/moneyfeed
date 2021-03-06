package com.newhope.openapi.api.vo.response.shop;

import com.newhope.moneyfeed.api.vo.response.Result;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Create by yyq on 2018/12/27
 */
public class UcBmShopResult extends Result {
    private static final long serialVersionUID = 3908259198934813144L;

    @ApiModelProperty(name="id", notes="店铺主键id")
    private Long id;
    @ApiModelProperty(name="shopName", notes="店铺名称", required=false)
    private String shopName;
    @ApiModelProperty(name="orgName", notes="公司名称", required=false)
    private String orgName;
    @ApiModelProperty(name="shortCode", notes="公司编码", required=false)
    private String shortCode;

    // todo 公司简称

    @ApiModelProperty(name="intro", notes="店铺简介", required=false)
    private String intro;
    @ApiModelProperty(name="contactPerson", notes="联系人", required=false)
    private String contactPerson;
    @ApiModelProperty(name="contactTel", notes="联系电话", required=false)
    private String contactTel;
    @ApiModelProperty(name="adminName", notes="管理员名称", required=false)
    private String adminName;
    @ApiModelProperty(name="targetType", notes="管理员电话", required=false)
    private String mobile;
    @ApiModelProperty(name="hotline", notes="客服电话", required=false)
    private String hotline;
    @ApiModelProperty(name="address", notes="详细地址", required=false)
    private String address;
    @ApiModelProperty(name="mapAddress", notes="导航地址", required=false)
    private String mapAddress;
    @ApiModelProperty(name="mapLat", notes="纬度", required=false)
    private String mapLat;
    @ApiModelProperty(name="mapLnt", notes="经度", required=false)
    private String mapLnt;
    @ApiModelProperty(name="province", notes="商户地址-省", required=false)
    private String province;
    @ApiModelProperty(name="city", notes="商户地址-市", required=false)
    private String city;
    @ApiModelProperty(name="county", notes="商户地址-县", required=false)
    private String county;
    @ApiModelProperty(name="status", notes="店铺状态", required=false)
    private String status;
    @ApiModelProperty(name="gmtCreated", notes="创建日期", required=false)
    private Date gmtCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
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

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
}
