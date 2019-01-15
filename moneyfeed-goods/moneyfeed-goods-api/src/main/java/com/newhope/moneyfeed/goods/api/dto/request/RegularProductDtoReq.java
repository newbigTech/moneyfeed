package com.newhope.moneyfeed.goods.api.dto.request;

import java.io.Serializable;

public class RegularProductDtoReq implements Serializable {

    /** 店铺id */
    private Long shopId;


    /** 客户 编码 */
    private String customerNo;

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
}
