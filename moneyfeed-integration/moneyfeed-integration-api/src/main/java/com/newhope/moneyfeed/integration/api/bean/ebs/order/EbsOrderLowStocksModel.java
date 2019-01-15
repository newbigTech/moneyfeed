package com.newhope.moneyfeed.integration.api.bean.ebs.order;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;

/**
 *   订单库存不足物料
 */
public class EbsOrderLowStocksModel extends BaseModel {
    private String dataStatus;

    /** 中间订单ID */
    private Long orderId;

    /** 中间订单详情ID */
    private Long orderDetailId;

    /** 商品编号 */
    private String materielNo;

    /** 库存数量 */
    private BigDecimal stockQuantity;

    /** 当前使用数量 */
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

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getMaterielNo() {
        return materielNo;
    }

    public void setMaterielNo(String materielNo) {
        this.materielNo = materielNo;
    }

    public BigDecimal getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(BigDecimal stockQuantity) {
        this.stockQuantity = stockQuantity;
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
}