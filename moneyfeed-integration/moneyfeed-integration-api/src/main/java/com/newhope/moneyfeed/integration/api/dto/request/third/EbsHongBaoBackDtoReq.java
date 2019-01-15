package com.newhope.moneyfeed.integration.api.dto.request.third;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description:
 * @author: dongql
 * @date: 2018/5/25 19:18
 */
public class EbsHongBaoBackDtoReq implements Serializable{
    private static final long serialVersionUID = 1932020234735334179L;

    @ApiModelProperty(name = "amount", required = true, notes = "退还金额")
    private BigDecimal amount;

    @ApiModelProperty(name = "mobile", notes = "手机号")
    private String mobile;

    @ApiModelProperty(name = "orderNumber", notes = "订单号")
    private String orderNumber;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getVerificationNumber() {
        return verificationNumber;
    }

    public void setVerificationNumber(String verificationNumber) {
        this.verificationNumber = verificationNumber;
    }

    @ApiModelProperty(name = "verificationNumber", notes = "核销号")
    private String verificationNumber;

}
