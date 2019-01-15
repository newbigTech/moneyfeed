package com.newhope.moneyfeed.integration.api.dto.response.ebs.order;

import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Created by Dave Chen on 2018/11/29.
 */
@XmlRootElement(name = "LINE")
public class QueryEbsOrderMaterialInfoDtoResult {

    /** 中间订单ID */
    private Long orderId;

    /** ebs订单详情ID */
    @ApiModelProperty(name = "orderFromEbsId", value = "ebs订单详情ID")
    private Long orderFromEbsId;

    /** 明细行类型
     1.正常商品
     2.赠品 */
    @ApiModelProperty(name = "itemType", value = "明细行类型")
    private String itemType;

    /** 商品编号 */
    @ApiModelProperty(name = "materielNo", value = "商品编号")
    private String materielNo;

    /** 数量 */
    @ApiModelProperty(name = "quantity", value = "数量")
    private BigDecimal quantity;

    /** 转换率 */
    @ApiModelProperty(name = "factor", value = "转换率")
    private BigDecimal factor;

    /** 单位 */
    @ApiModelProperty(name = "unit", value = "单位")
    private String unit;

    /** 副单位 */
    @ApiModelProperty(name = "secondUnit", value = "副单位")
    private String secondUnit;

    /** 厂价 */
    @ApiModelProperty(name = "planAmount", value = "出厂单价")
    private BigDecimal planAmount;

    /** 折扣价 */
    @ApiModelProperty(name = "discountAmount", value = "折扣单价")
    private BigDecimal discountAmount;

    /** 实际付款价 */
    @ApiModelProperty(name = "realPayAmount", value = "实际付款价")
    private BigDecimal realPayAmount;

    /** 库存组织编号 */
    @ApiModelProperty(name = "organizationCode", value = "库存组织编号")
    private String organizationCode;

    private String orgId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderFromEbsId() {
        return orderFromEbsId;
    }

    public void setOrderFromEbsId(Long orderFromEbsId) {
        this.orderFromEbsId = orderFromEbsId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @XmlElement(name = "ITEM_CODE")
    public String getMaterielNo() {
        return materielNo;
    }

    public void setMaterielNo(String materielNo) {
        this.materielNo = materielNo;
    }

    @XmlElement(name = "PRODUCT_COUNT")
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    @XmlElement(name = "UNIT")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSecondUnit() {
        return secondUnit;
    }

    public void setSecondUnit(String secondUnit) {
        this.secondUnit = secondUnit;
    }

    @XmlElement(name = "UNIT_LIST_PRICE")
    public BigDecimal getPlanAmount() {
        return planAmount;
    }

    public void setPlanAmount(BigDecimal planAmount) {
        this.planAmount = planAmount;
    }

    @XmlElement(name = "UNIT_SELLING_PRICE")
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    @XmlElement(name = "EXTENDED_PRICE")
    public BigDecimal getRealPayAmount() {
        return realPayAmount;
    }

    public void setRealPayAmount(BigDecimal realPayAmount) {
        this.realPayAmount = realPayAmount;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
