package com.newhope.moneyfeed.order.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;

/**
 *   订单详情表
 */
public class OrderDetailModel extends BaseModel {
    /** 订单ID */
    private Long orderId;

    /** 商品ID */
    private String productCode;

    /** 重量规格 */
    private String weightParam;

    /** 下单数量 */
    private Integer count;

    /** 拉料总重(千克) */
    private BigDecimal totalFeedWeight;

    /** 出厂总金额 */
    private BigDecimal totalOrginalAmount;

    /** 实际支付总金额 */
    private BigDecimal totalPayAmount;

    /** 折扣总金额 */
    private BigDecimal totalDiscountAmount;

    /** 库存组织编码 */
    private String organizationCode;

    /** 物料编号 */
    private String suppliesCode;

    /** 单位 */
    private String unit;

    /** 订单号 */
    private String orderNo;

    /** 公司简码 */
    private String companyShortCode;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getWeightParam() {
        return weightParam;
    }

    public void setWeightParam(String weightParam) {
        this.weightParam = weightParam;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotalFeedWeight() {
        return totalFeedWeight;
    }

    public void setTotalFeedWeight(BigDecimal totalFeedWeight) {
        this.totalFeedWeight = totalFeedWeight;
    }

    public BigDecimal getTotalOrginalAmount() {
        return totalOrginalAmount;
    }

    public void setTotalOrginalAmount(BigDecimal totalOrginalAmount) {
        this.totalOrginalAmount = totalOrginalAmount;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public BigDecimal getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(BigDecimal totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getSuppliesCode() {
        return suppliesCode;
    }

    public void setSuppliesCode(String suppliesCode) {
        this.suppliesCode = suppliesCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCompanyShortCode() {
        return companyShortCode;
    }

    public void setCompanyShortCode(String companyShortCode) {
        this.companyShortCode = companyShortCode;
    }
}