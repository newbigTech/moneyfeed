package com.newhope.moneyfeed.goods.api.dto.response;

import java.io.Serializable;

public class RegularProductDtoResult implements Serializable {

    /** 店铺id */
    private Long shopId;

    /** 客户id */
    private Long customerId;

    /** 客户 编码 */
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }
}
