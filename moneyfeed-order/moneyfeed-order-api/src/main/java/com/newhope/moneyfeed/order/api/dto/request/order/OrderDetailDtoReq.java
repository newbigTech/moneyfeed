package com.newhope.moneyfeed.order.api.dto.request.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Create by yyq on 2018/11/17
 */
public class OrderDetailDtoReq implements Serializable {

    private static final long serialVersionUID = -4390554106740411069L;
    @ApiModelProperty(name = "Long", notes = "订单主键id")
    private Long orderId;
    @ApiModelProperty(name = "orderNo", notes = "订单号")
    private String orderNo;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
