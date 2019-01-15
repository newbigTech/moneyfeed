package com.newhope.moneyfeed.integration.api.bean.ebs.order;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;

public class EbsOrderFromSaleItemsModel extends BaseModel {
    private String dataStatus;

    /** 中间订单ID */
    private Long orderId;

    /** 中间商城订单详情ID */
    private Long orderFromSaleId;

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

    /** 库存组织编号 */
    private String organizationCode;

    private String orgId;

    /** 订单明细JSON */
    private String saleOrderItemJson;

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

    public Long getOrderFromSaleId() {
        return orderFromSaleId;
    }

    public void setOrderFromSaleId(Long orderFromSaleId) {
        this.orderFromSaleId = orderFromSaleId;
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

    public String getSaleOrderItemJson() {
        return saleOrderItemJson;
    }

    public void setSaleOrderItemJson(String saleOrderItemJson) {
        this.saleOrderItemJson = saleOrderItemJson;
    }
}