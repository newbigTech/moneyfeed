package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;

/**
 *   用户中心-客户账户流水明细表
 */
public class UcCuntomerAccountStatementModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5403550543125377103L;

	/** 用户id */
    private Long clientUserId;

    /** 客户id */
    private Long customerId;

    /** 客户账户id */
    private Long customerAccountId;

    /** 子账户类型 */
    private String subAccountType;

    /** 子账户id */
    private Long subAccountId;

    /** 发生业务类型 */
    private String bizType;

    /** 发生金额 */
    private BigDecimal amount;

    /** 发生业务主键id */
    private Long bizId;

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

    public String getSubAccountType() {
        return subAccountType;
    }

    public void setSubAccountType(String subAccountType) {
        this.subAccountType = subAccountType;
    }

    public Long getSubAccountId() {
        return subAccountId;
    }

    public void setSubAccountId(Long subAccountId) {
        this.subAccountId = subAccountId;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }
}