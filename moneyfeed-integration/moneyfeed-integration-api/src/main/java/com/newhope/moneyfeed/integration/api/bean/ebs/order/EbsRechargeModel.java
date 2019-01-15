package com.newhope.moneyfeed.integration.api.bean.ebs.order;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

/**
 *   充值表
 */
public class EbsRechargeModel extends BaseModel {
    /** 中间件订单ID，当是订单充值的时使用 */
    private Long orderId;

    private String dataStatus;

    /** 支付方式，订单充值/账户充值 */
    private String payType;

    /** 支付总金额 */
    private BigDecimal totalPayAmount;

    /** 币钟 */
    private String currency;

    /** 是否临欠客户 */
    private Boolean tempOwe;

    /** 支付日期 */
    private Date payDate;

    /** 商城支付流水号,银行卡支付时使用 */
    private String moneyfeedPayNo;

    /** 银行流水号（银行卡支付） */
    private String accNo;

    /** 客户编号 */
    private String customerNo;

    /** 支付状态 */
    private String payStatus;

    /** 组织id */
    private String orgId;

    /** EBS最终收款账号 */
    private String ebsRealAccount;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getTempOwe() {
        return tempOwe;
    }

    public void setTempOwe(Boolean tempOwe) {
        this.tempOwe = tempOwe;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getMoneyfeedPayNo() {
        return moneyfeedPayNo;
    }

    public void setMoneyfeedPayNo(String moneyfeedPayNo) {
        this.moneyfeedPayNo = moneyfeedPayNo;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getEbsRealAccount() {
        return ebsRealAccount;
    }

    public void setEbsRealAccount(String ebsRealAccount) {
        this.ebsRealAccount = ebsRealAccount;
    }
}