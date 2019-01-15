package com.newhope.moneyfeed.pay.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

/**
 *   支付订单表
 */
public class PayBillDataExceptionModel extends BaseModel {
    /** 支付订单号 */
    private String payOrderNo;

    /** 商城订单号 */
    private String orderNo;

    /** 商户ID */
    private String platformid;

    /** 商户账号 */
    private String merchno;

    /** 支付平台返回的流水号 */
    private String bankOrderNo;

    /** 商城交易日期 */
    private Date tradeTime;

    /** 订单金额 */
    private BigDecimal amount;

    /** 银行实际订单金额 */
    private BigDecimal bankAmount;

    /** 手续费 */
    private BigDecimal fee;

    /** 备注 */
    private String remark;

    /** 支付平台订单支付日期 */
    private Date bankTradeTime;

    /** 对账单的订单状态(成功，失败。。。) */
    private String billStatus;

    /** 支付订单的状态 */
    private String status;

    /** 原因 */
    private String reason;

    /** 原因类型 */
    private String reasonType;

    /** 是否修复 */
    private Boolean fixFlag;

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

    public String getPlatformid() {
        return platformid;
    }

    public void setPlatformid(String platformid) {
        this.platformid = platformid;
    }

    public String getMerchno() {
        return merchno;
    }

    public void setMerchno(String merchno) {
        this.merchno = merchno;
    }

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(BigDecimal bankAmount) {
        this.bankAmount = bankAmount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getBankTradeTime() {
        return bankTradeTime;
    }

    public void setBankTradeTime(Date bankTradeTime) {
        this.bankTradeTime = bankTradeTime;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReasonType() {
        return reasonType;
    }

    public void setReasonType(String reasonType) {
        this.reasonType = reasonType;
    }

    public Boolean getFixFlag() {
        return fixFlag;
    }

    public void setFixFlag(Boolean fixFlag) {
        this.fixFlag = fixFlag;
    }
}