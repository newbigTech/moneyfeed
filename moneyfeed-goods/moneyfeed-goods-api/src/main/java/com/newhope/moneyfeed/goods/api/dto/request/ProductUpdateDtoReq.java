package com.newhope.moneyfeed.goods.api.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/21 09:33
 */
public class ProductUpdateDtoReq implements Serializable {
    private static final long serialVersionUID = -6862966819288871910L;

    @ApiModelProperty(name = "companyShortCode", notes = "公司code")
    private String companyShortCode;

    @ApiModelProperty(name = "shopId", notes = "店铺id")
    private Long shopId;

    @ApiModelProperty(name = "customerId", notes = "客户id")
    private Long customerId;

    @ApiModelProperty(name = "suppliesCode", notes = "商品code")
    private String suppliesCode;

    public String getCompanyShortCode() {
        return companyShortCode;
    }

    public void setCompanyShortCode(String companyShortCode) {
        this.companyShortCode = companyShortCode;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getSuppliesCode() {
        return suppliesCode;
    }

    public void setSuppliesCode(String suppliesCode) {
        this.suppliesCode = suppliesCode;
    }
}
