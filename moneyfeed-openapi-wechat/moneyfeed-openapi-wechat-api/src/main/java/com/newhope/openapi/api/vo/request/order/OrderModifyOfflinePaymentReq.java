package com.newhope.openapi.api.vo.request.order;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author bena.peng
 * @date 2019/1/10 10:08
 */


public class OrderModifyOfflinePaymentReq implements Serializable {

    private static final long serialVersionUID = 4561357595112059343L;
    @NotBlank(message = "订单号不能为空")
    @ApiModelProperty(name = "orderNo", notes = "订单号", required = true)
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
