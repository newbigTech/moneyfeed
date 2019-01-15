package com.newhope.moneyfeed.order.api.dto.request.order;

import java.io.Serializable;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/19 16:38
 */
public class OrderRuleQueryDtoReq implements Serializable{
    private static final long serialVersionUID = 5941259670965253396L;

    /** 商户ID */
    private Long ucShopId;

    public Long getUcShopId() {
        return ucShopId;
    }

    public void setUcShopId(Long ucShopId) {
        this.ucShopId = ucShopId;
    }
}
