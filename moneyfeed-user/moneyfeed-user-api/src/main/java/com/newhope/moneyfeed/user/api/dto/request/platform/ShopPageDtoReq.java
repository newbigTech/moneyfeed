package com.newhope.moneyfeed.user.api.dto.request.platform;

import com.newhope.moneyfeed.api.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Create by yyq on 2018/12/20
 */
public class ShopPageDtoReq extends PageDtoReq implements Serializable {
    private static final long serialVersionUID = -7061437456686540720L;

    @ApiModelProperty(name="shopName", notes="店铺名称", required=false)
    private String shopName;
    @ApiModelProperty(name="orgName", notes="公司名称", required=false)
    private String orgName;

    @ApiModelProperty(name="status", notes="店铺状态", required=false)
    private String status;
    @ApiModelProperty(name="startDate", notes="开始日期", required=false)
    private Date startDate;
    @ApiModelProperty(name="endDate", notes="结束日期", required=false)
    private Date endDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
