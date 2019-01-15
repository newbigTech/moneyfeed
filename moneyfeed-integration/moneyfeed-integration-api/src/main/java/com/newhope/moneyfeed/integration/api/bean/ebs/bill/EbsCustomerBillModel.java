package com.newhope.moneyfeed.integration.api.bean.ebs.bill;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *   客户账单(按月汇总)
 */
@XmlRootElement(name = "HERDER")
public class EbsCustomerBillModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -958635093727721546L;

	private String dataStatus;

    /** 组织ID */
    private String orgId;

    /** 客户编号 */
    private String customerNo;

    /** 期初待还款（元） */
    private BigDecimal beginPlanPayment;

    /** 期末待还额（元） */
    private BigDecimal endPlanPayment;

    /** 交易数(笔)    */
    private Integer payQuantity;

    /** 购买量（KG） */
    private BigDecimal buyQuantity;

    /** 赠包数（KG）
             */
    private BigDecimal giveQuantity;

    /** 应付货款（元） */
    private BigDecimal mustPayable;

    /** 实付货款（元） */
    private BigDecimal realPayable;

    /** 余额支付（元）
             */
    private BigDecimal balancePay;

    /** 银行卡支付（元） */
    private BigDecimal cardPay;

    private Integer year;

    private Integer month;

    /** 明细同步标识 */
    private String syncDetailFlag;

    public String getDataStatus() {
        return dataStatus;
    }
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    @XmlElement(name = "ORG_ID")
    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @XmlElement(name = "CUSTOMER_NO")
    public String getCustomerNo() {
        return customerNo;
    }
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    @XmlElement(name = "BEGIN_PLAN_PAYMENT")
    public BigDecimal getBeginPlanPayment() {
        return beginPlanPayment;
    }
    public void setBeginPlanPayment(BigDecimal beginPlanPayment) {
        this.beginPlanPayment = beginPlanPayment;
    }

    @XmlElement(name = "END_PLAN_PAYMENT")
    public BigDecimal getEndPlanPayment() {
        return endPlanPayment;
    }
    public void setEndPlanPayment(BigDecimal endPlanPayment) {
        this.endPlanPayment = endPlanPayment;
    }

    @XmlElement(name = "PAY_QUANTITY")
    public Integer getPayQuantity() {
        return payQuantity;
    }
    public void setPayQuantity(Integer payQuantity) {
        this.payQuantity = payQuantity;
    }

    @XmlElement(name = "BUY_QUANTITY")
    public BigDecimal getBuyQuantity() {
        return buyQuantity;
    }
    public void setBuyQuantity(BigDecimal buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    @XmlElement(name = "GIVE_QUANTITY")
    public BigDecimal getGiveQuantity() {
        return giveQuantity;
    }
    public void setGiveQuantity(BigDecimal giveQuantity) {
        this.giveQuantity = giveQuantity;
    }

    @XmlElement(name = "MUST_PAYABLE")
    public BigDecimal getMustPayable() {
        return mustPayable;
    }
    public void setMustPayable(BigDecimal mustPayable) {
        this.mustPayable = mustPayable;
    }

    @XmlElement(name = "REAL_PAYABLE")
    public BigDecimal getRealPayable() {
        return realPayable;
    }
    public void setRealPayable(BigDecimal realPayable) {
        this.realPayable = realPayable;
    }

    @XmlElement(name = "BALANCE_PAY")
    public BigDecimal getBalancePay() {
        return balancePay;
    }
    public void setBalancePay(BigDecimal balancePay) {
        this.balancePay = balancePay;
    }

    @XmlElement(name = "CARD_PAY")
    public BigDecimal getCardPay() {
        return cardPay;
    }
    public void setCardPay(BigDecimal cardPay) {
        this.cardPay = cardPay;
    }

    @XmlElement(name = "YEAR")
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }

    @XmlElement(name = "MONTH")
    public Integer getMonth() {
        return month;
    }
    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getSyncDetailFlag() {
        return syncDetailFlag;
    }
    public void setSyncDetailFlag(String syncDetailFlag) {
        this.syncDetailFlag = syncDetailFlag;
    }
}