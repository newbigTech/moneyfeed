package com.newhope.moneyfeed.goods.api.bean;

/**
 *    常购清单表
 */
public class RegularProductModel extends BaseModel {
    /** 店铺id */
    private Long shopId;

    /** ebs 客户编码 */
    private String customerNo;

    private Long skuId;

    /** 排序 */
    private Integer sort;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}