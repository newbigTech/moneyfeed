package com.newhope.moneyfeed.goods.api.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/12/19 14:31
 */
public class BrandQueryDtoReq extends PageDtoReq implements Serializable {
    private static final long serialVersionUID = -8383359945172178763L;

    /** 品牌id */
    private Long brandId;

    /** 品牌名称 */
    private String brandName;

    /**
     * 店铺id
     */
    private Long shopId;

    @ApiModelProperty(name = "customerNum", notes = "客户编码")
    private String customerNum;

    @ApiModelProperty(name = "saleCateId", notes = "目录id")
    private String saleCateId;

    @ApiModelProperty(name = "saleCateLevel", notes = "目录层级")
    private Integer saleCateLevel;

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getSaleCateId() {
        return saleCateId;
    }

    public void setSaleCateId(String saleCateId) {
        this.saleCateId = saleCateId;
    }

    public Integer getSaleCateLevel() {
        return saleCateLevel;
    }

    public void setSaleCateLevel(Integer saleCateLevel) {
        this.saleCateLevel = saleCateLevel;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
