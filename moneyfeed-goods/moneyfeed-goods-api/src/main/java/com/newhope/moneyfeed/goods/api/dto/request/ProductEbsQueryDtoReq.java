package com.newhope.moneyfeed.goods.api.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/29 10:39
 */
public class ProductEbsQueryDtoReq {

    @ApiModelProperty(name = "status", notes = "状态")
    private String status;

    @ApiModelProperty(name = "shopId", notes = "店铺id")
    private Long shopId;

    @ApiModelProperty(name = "suppliesCodes", notes = "物料编号")
    private List<String> suppliesCodes;

    @ApiModelProperty(name = "suppliesIds", notes = "物料ids")
    private List<String> suppliesIds;

    @ApiModelProperty(name = "orgId", notes = "组织ID")
    private String orgId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<String> getSuppliesCodes() {
        return suppliesCodes;
    }

    public void setSuppliesCodes(List<String> suppliesCodes) {
        this.suppliesCodes = suppliesCodes;
    }

    public List<String> getSuppliesIds() {
        return suppliesIds;
    }

    public void setSuppliesIds(List<String> suppliesIds) {
        this.suppliesIds = suppliesIds;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
