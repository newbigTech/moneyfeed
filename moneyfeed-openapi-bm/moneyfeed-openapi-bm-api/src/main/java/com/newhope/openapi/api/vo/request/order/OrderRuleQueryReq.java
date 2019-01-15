package com.newhope.openapi.api.vo.request.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/21 15:08
 */
public class OrderRuleQueryReq implements Serializable {
    private static final long serialVersionUID = -4050461844554439016L;

    @ApiModelProperty(name = "ucShopId", notes = "商户店铺ID")
    private Long ucShopId;

    public Long getUcShopId() {
        return ucShopId;
    }

    public void setUcShopId(Long ucShopId) {
        this.ucShopId = ucShopId;
    }
}
