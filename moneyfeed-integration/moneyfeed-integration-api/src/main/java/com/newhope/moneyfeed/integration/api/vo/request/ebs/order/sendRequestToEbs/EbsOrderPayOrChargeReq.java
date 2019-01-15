package com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yuyanlin on 2018/12/18
 */
@XmlRootElement(name = "inv:P_REQUEST_LINE_ITEM")
public class EbsOrderPayOrChargeReq implements Serializable {

    private static final long serialVersionUID = 5331357924576004627L;

    // 商城订单ID
    private String moneyfeedOrderId;

    // EBS订单编号
    private String ebsOrderNo;

    // 币种
    private String currency;

    // 客户编码
    private String customerNo;

    // 支付或者充值类型
    private String payOrChargeType;

    // 支付日期字符串格式(YYYY-MM-DD)
    private String payDateString;

    // 余额支付金额
    private BigDecimal totalBalanceAmount;

    // 折扣支付金额
    private BigDecimal discountAmount;

    // 银行卡支付金额
    private BigDecimal totalBankAmount;

    // 商城支付号
    private String moneyfeedPayNo;

    // 银行支付流水号
    private String accNo;

    // 收款方法，预留字段，后期可能需要
    private String collectionMethod;

    // EBS需要org_id这个字段
    private String orgId;

    @XmlElement(name = "inv:VALUE1")
    public String getMoneyfeedOrderId() {
        return moneyfeedOrderId;
    }

    public void setMoneyfeedOrderId(String moneyfeedOrderId) {
        this.moneyfeedOrderId = moneyfeedOrderId;
    }

    @XmlElement(name = "inv:VALUE2")
    public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }

    @XmlElement(name = "inv:VALUE3")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @XmlElement(name = "inv:VALUE4")
    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    @XmlElement(name = "inv:VALUE5")
    public String getPayOrChargeType() {
        return payOrChargeType;
    }

    public void setPayOrChargeType(String payOrChargeType) {
        this.payOrChargeType = payOrChargeType;
    }

    @XmlElement(name = "inv:VALUE6")
    public String getPayDateString() {
        return payDateString;
    }

    public void setPayDateString(String payDateString) {
        this.payDateString = payDateString;
    }

    @XmlElement(name = "inv:VALUE7")
    public BigDecimal getTotalBalanceAmount() {
        return totalBalanceAmount;
    }

    public void setTotalBalanceAmount(BigDecimal totalBalanceAmount) {
        this.totalBalanceAmount = totalBalanceAmount;
    }

    @XmlElement(name = "inv:VALUE8")
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    @XmlElement(name = "inv:VALUE9")
    public BigDecimal getTotalBankAmount() {
        return totalBankAmount;
    }

    public void setTotalBankAmount(BigDecimal totalBankAmount) {
        this.totalBankAmount = totalBankAmount;
    }

    @XmlElement(name = "inv:VALUE10")
    public String getMoneyfeedPayNo() {
        return moneyfeedPayNo;
    }

    public void setMoneyfeedPayNo(String moneyfeedPayNo) {
        this.moneyfeedPayNo = moneyfeedPayNo;
    }

    @XmlElement(name = "inv:VALUE11")
    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    @XmlElement(name = "inv:VALUE12")
    public String getCollectionMethod() {
        return collectionMethod;
    }

    public void setCollectionMethod(String collectionMethod) {
        this.collectionMethod = collectionMethod;
    }

    @XmlElement(name = "inv:VALUE13")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
