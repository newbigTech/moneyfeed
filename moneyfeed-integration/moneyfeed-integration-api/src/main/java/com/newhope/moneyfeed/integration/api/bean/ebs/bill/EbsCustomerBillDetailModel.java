package com.newhope.moneyfeed.integration.api.bean.ebs.bill;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *   客户账单明细
 */
@XmlRootElement(name = "HERDER")
public class EbsCustomerBillDetailModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6151663052733578594L;

	private String dataStatus;

    /** 组织ID */
    private String orgId;

    /** 客户编号 */
    private String customerCode;

    /** 年份 */
    private Integer year;

    /** 月份 */
    private Integer month;

    /** 提货日期 */
    private Date pickingDate;

    /** 账单类型(1.线上 online;2.线下 offline) */
    private String billType;

    /** 单据号 */
    private String docmentNum;

    /** 车牌号 */
    private String carNum;

    /** 物料编码 */
    private String materielNum;

    /** 物料名称(含赠包信息) */
    private String materielName;

    /** 购买量（KG） */
    private BigDecimal quantity;

    /** 单位 */
    private String uomCode;

    /** 厂价单价 */
    private BigDecimal basePrice;

    /** 销售单价 */
    private BigDecimal salePrice;

    /** 吨均现折 */
    private BigDecimal tAreFold;

    /** 单个饲料应收款 */
    private BigDecimal accountReceivable;

    /** 单个饲料实收款 */
    private BigDecimal fundsReceived;

    /** 余额 */
    private BigDecimal balance;

    /** 订单余额支付金额 */
    private BigDecimal balancePay;

    /** 订单银行卡支付金额 */
    private BigDecimal cardPay;

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

    @XmlElement(name = "CUSTOMER_CODE")
    public String getCustomerCode() {
        return customerCode;
    }
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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

    @XmlElement(name = "PICKING_DATE")
    public Date getPickingDate() {
        return pickingDate;
    }
    public void setPickingDate(Date pickingDate) {
        this.pickingDate = pickingDate;
    }

    @XmlElement(name = "BILL_TYPE")
    public String getBillType() {
        return billType;
    }
    public void setBillType(String billType) {
        this.billType = billType;
    }

    @XmlElement(name = "DOCMENT_NUM")
    public String getDocmentNum() {
        return docmentNum;
    }
    public void setDocmentNum(String docmentNum) {
        this.docmentNum = docmentNum;
    }

    @XmlElement(name = "CAR_NUM")
    public String getCarNum() {
        return carNum;
    }
    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    @XmlElement(name = "MATERIEL_NUM")
    public String getMaterielNum() {
        return materielNum;
    }
    public void setMaterielNum(String materielNum) {
        this.materielNum = materielNum;
    }

    @XmlElement(name = "MATERIEL_NAME")
    public String getMaterielName() {
        return materielName;
    }
    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    @XmlElement(name = "QUANTITY")
    public BigDecimal getQuantity() {
        return quantity;
    }
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @XmlElement(name = "UOM_CODE")
    public String getUomCode() {
        return uomCode;
    }
    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    @XmlElement(name = "BASE_PRICE")
    public BigDecimal getBasePrice() {
        return basePrice;
    }
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    @XmlElement(name = "SALE_PRICE")
    public BigDecimal getSalePrice() {
        return salePrice;
    }
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    @XmlElement(name = "T_ART_FOLD")
    public BigDecimal gettAreFold() {
        return tAreFold;
    }
    public void settAreFold(BigDecimal tAreFold) {
        this.tAreFold = tAreFold;
    }

    @XmlElement(name = "ACCOUNT_RECEIVABLE")
    public BigDecimal getAccountReceivable() {
        return accountReceivable;
    }
    public void setAccountReceivable(BigDecimal accountReceivable) {
        this.accountReceivable = accountReceivable;
    }

    @XmlElement(name = "FUNDS_RECEIVED")
    public BigDecimal getFundsReceived() {
        return fundsReceived;
    }
    public void setFundsReceived(BigDecimal fundsReceived) {
        this.fundsReceived = fundsReceived;
    }

    @XmlElement(name = "BALANCE")
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
}