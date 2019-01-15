package com.newhope.openapi.api.vo.request.shop;

import com.newhope.moneyfeed.api.vo.request.PageReq;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Create by yyq on 2018/12/25
 */
public class PmShopPageReq extends PageReq implements Serializable {

    private static final long serialVersionUID = -3763081727148042311L;
    @ApiModelProperty(name="shopName", notes="店铺名称", required=false)
    private String shopName;
    @ApiModelProperty(name="orgName", notes="公司名称", required=false)
    private String orgName;

    @ApiModelProperty(name="status", notes="店铺状态", required=false)
    private String status;
    @ApiModelProperty(name="startDate", notes="开始日期", required=false)
    private String startDate;
    @ApiModelProperty(name="endDate", notes="结束日期", required=false)
    private String endDate;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
