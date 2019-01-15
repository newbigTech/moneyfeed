package com.newhope.moneyfeed.integration.api.vo.response.ebs.order.sendRequestToEbsForResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Created by yuyanlin on 2018/11/23
 */
@XmlRootElement(name = "HEADER")
public class EbsResponseCreateResult {

    // 公司编码（EBS编码）
    private String companyShortCode;

    // 库存组织（EBS编码）
    private String organizationCode;

    // 商城订单ID
    private String moneyfeedOrderId;

    // EBS订单编号
    private String orderNumber;

    // 订单总额（厂价）
    private BigDecimal totalOrginalAmount;

    // 实付金额（折扣价）
    private BigDecimal totalPayAmount;

    // 折扣金额
    private BigDecimal totalDiscountAmount;

    // 客户
    private String suppliesCode;

    // 物料编码
    private String itemCode;

    // 数量
    private BigDecimal productCount;

    // 单位
    private String unit;

    // 厂价
    private BigDecimal unitListPrice;

    // 折扣价
    private BigDecimal unitSellingPrice;

    // 实付金额
    private BigDecimal extendedPrice;

    // 订单状态
    private String status;

    // 创建结果（成功、失败）
    private String returnCode;

    // 创建结果（失败原因）
    private String returnMsg;

    @XmlElement(name = "COMPANY_SHORT_CODE")
    public String getCompanyShortCode() {
        return companyShortCode;
    }

    public void setCompanyShortCode(String companyShortCode) {
        this.companyShortCode = companyShortCode;
    }

    @XmlElement(name = "ORGANIZATION_CODE")
    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    @XmlElement(name = "MONEYFEED_ORDER_ID")
    public String getMoneyfeedOrderId() {
        return moneyfeedOrderId;
    }

    public void setMoneyfeedOrderId(String moneyfeedOrderId) {
        this.moneyfeedOrderId = moneyfeedOrderId;
    }

    @XmlElement(name = "ORDER_NUMBER")
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @XmlElement(name = "TOTAL_ORGINAL_AMOUNT")
    public BigDecimal getTotalOrginalAmount() {
        return totalOrginalAmount;
    }

    public void setTotalOrginalAmount(BigDecimal totalOrginalAmount) {
        this.totalOrginalAmount = totalOrginalAmount;
    }

    @XmlElement(name = "TOTAL_PAY_AMOUNT")
    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    @XmlElement(name = "TOTAL_DISCOUNT_AMOUNT")
    public BigDecimal getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(BigDecimal totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount;
    }

    @XmlElement(name = "SUPPLIES_CODE")
    public String getSuppliesCode() {
        return suppliesCode;
    }

    public void setSuppliesCode(String suppliesCode) {
        this.suppliesCode = suppliesCode;
    }

    @XmlElement(name = "ITEM_CODE")
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @XmlElement(name = "PRODUCT_COUNT")
    public BigDecimal getProductCount() {
        return productCount;
    }

    public void setProductCount(BigDecimal productCount) {
        this.productCount = productCount;
    }

    @XmlElement(name = "UNIT")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @XmlElement(name = "UNIT_LIST_PRICE")
    public BigDecimal getUnitListPrice() {
        return unitListPrice;
    }

    public void setUnitListPrice(BigDecimal unitListPrice) {
        this.unitListPrice = unitListPrice;
    }

    @XmlElement(name = "UNIT_SELLING_PRICE")
    public BigDecimal getUnitSellingPrice() {
        return unitSellingPrice;
    }

    public void setUnitSellingPrice(BigDecimal unitSellingPrice) {
        this.unitSellingPrice = unitSellingPrice;
    }

    @XmlElement(name = "EXTENDED_PRICE")
    public BigDecimal getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(BigDecimal extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    @XmlElement(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "RETURN_CODE")
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @XmlElement(name = "RETURN_MESG")
    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}
