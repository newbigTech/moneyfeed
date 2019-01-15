package com.newhope.moneyfeed.integration.api.bean.ebs.order;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;

public class EbsOrderFromEbsItemsModel extends BaseModel {
    private String dataStatus;

    /** 中间订单ID */
    private Long orderId;

    /** ebs订单详情ID */
    private Long orderFromEbsId;

    /** 明细行类型
            1.正常商品
            2.赠品 */
    private String itemType;

    /** 商品编号 */
    private String materielNo;

    /** 数量 */
    private BigDecimal quantity;

    /** 转换率 */
    private BigDecimal factor;

    /** 单位 */
    private String unit;

    /** 副单位 */
    private String secondUnit;

    /** 厂价 */
    private BigDecimal planAmount;

    /** 折扣价 */
    private BigDecimal discountAmount;

    /** 实际付款价 */
    private BigDecimal realPayAmount;

    /** 库存组织编号 */
    private String organizationCode;

    private String orgId;

    /** 订单明细JSON */
    private String ebsOrderItemJson;

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

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

    public String getMaterielNo() {
        return materielNo;
    }

    public void setMaterielNo(String materielNo) {
        this.materielNo = materielNo;
    }

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

    public BigDecimal getPlanAmount() {
        return planAmount;
    }

    public void setPlanAmount(BigDecimal planAmount) {
        this.planAmount = planAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

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

    public String getEbsOrderItemJson() {
        return ebsOrderItemJson;
    }

    public void setEbsOrderItemJson(String ebsOrderItemJson) {
        this.ebsOrderItemJson = ebsOrderItemJson;
    }
}