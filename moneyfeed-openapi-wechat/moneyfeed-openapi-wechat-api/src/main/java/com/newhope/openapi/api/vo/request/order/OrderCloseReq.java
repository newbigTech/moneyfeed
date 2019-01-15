package com.newhope.openapi.api.vo.request.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Create by yyq on 2018/11/20
 */
public class OrderCloseReq implements Serializable {

    private static final long serialVersionUID = -2522862268222930609L;
    @ApiModelProperty(name = "orderId", notes = "订单主键id",required = true)
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
