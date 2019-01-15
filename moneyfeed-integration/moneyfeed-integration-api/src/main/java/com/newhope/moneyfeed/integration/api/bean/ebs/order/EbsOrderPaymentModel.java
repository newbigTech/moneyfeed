package com.newhope.moneyfeed.integration.api.bean.ebs.order;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class EbsOrderPaymentModel extends BaseModel {
    /** 中间件订单ID */
    private Long orderId;

    private String dataStatus;

    /** 支付方式，余额支付/银行卡支付 */
    private String payType;

    /** 支付总金额 */
    private BigDecimal totalPayAmount;

    /** 余额支付时填写 */
    private BigDecimal balanceAmount;

    /** 本次使用折扣金额【余额支付】方式时，=MIN(EBS在客户可用余额信息接口 “保证金”金额,订单金额)； */
    private BigDecimal discountAmount;

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

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
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

    public String getEbsRealAccount() {
        return ebsRealAccount;
    }

    public void setEbsRealAccount(String ebsRealAccount) {
        this.ebsRealAccount = ebsRealAccount;
    }
}