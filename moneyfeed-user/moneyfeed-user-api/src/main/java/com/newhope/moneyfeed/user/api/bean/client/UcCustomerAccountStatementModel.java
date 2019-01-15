package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;

/**
 *   用户中心-客户账户流水明细表
 */
public class UcCustomerAccountStatementModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5959581306069870623L;

	/** 用户id */
    private Long clientUserId;

    /** 客户id */
    private Long customerId;

    /** 客户账户id */
    private Long customerAccountId;

    /** 流水号(支付流水号，退款流水号) */
    private String serialNumber;

    /** 金额 */
    private BigDecimal amount;

    /** 业务类型(支付、退款、充值、提现、还款) */
    private String bizType;

    /** 支付订单号 */
    private String payOrderNo;

    /** 商品订单号 */
    private String orderNo;

    /** 商户id */
    private Long shopId;

    public Long getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(Long clientUserId) {
        this.clientUserId = clientUserId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(Long customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}