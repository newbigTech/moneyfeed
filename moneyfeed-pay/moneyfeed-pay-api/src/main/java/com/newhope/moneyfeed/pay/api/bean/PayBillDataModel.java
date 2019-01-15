package com.newhope.moneyfeed.pay.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;
import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.common.util.excel.annotation.MyCell;

import java.math.BigDecimal;
import java.util.Date;

/**
 *   支付订单表
 */
public class PayBillDataModel extends BaseModel {
    /** 支付订单号 */
    @MyCell(index = 0)
    private String payOrderNo;

    /** 支付平台返回的流水号 */
    @MyCell(index = 1)
    private String bankOrderNo;

    /** 银行实际订单金额 */
    @MyCell(index = 2)
    private BigDecimal bankAmount;

    /** 手续费 */
    @MyCell(index = 3)
    private BigDecimal fee;

    /** 交易类型 */
    @MyCell(index = 4)
    private String tradeType;

    /** 订单状态(成功，失败) */
    @MyCell(index = 5)
    private String status;

    /** 支付平台订单支付日期 */
    @MyCell(index = 6,format = DateUtil.YYYY_MM_DD_HH_MM_SS)
    private Date bankTradeTime;

    /** 交易摘要 */
    @MyCell(index = 7)
    private String remark;

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
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

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBankTradeTime() {
        return bankTradeTime;
    }

    public void setBankTradeTime(Date bankTradeTime) {
        this.bankTradeTime = bankTradeTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}