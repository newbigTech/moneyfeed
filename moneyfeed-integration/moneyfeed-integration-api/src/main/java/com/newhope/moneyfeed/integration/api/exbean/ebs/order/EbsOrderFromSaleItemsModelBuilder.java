package com.newhope.moneyfeed.integration.api.exbean.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromSaleItemsModel;

import java.math.BigDecimal;

/**
 * Created by yuyanlin on 2018/11/22
 */
public final class EbsOrderFromSaleItemsModelBuilder {
    private String dataStatus;
    private Long orderId;
    private Long orderFromSaleId;
    private String materielNo;
    private BigDecimal quantity;
    private BigDecimal factor;
    private String unit;
    private String secondUnit;
    private String organizationCode;
    private String orgId;
    private String saleOrderItemJson;

    private EbsOrderFromSaleItemsModelBuilder() {
    }

    public static EbsOrderFromSaleItemsModelBuilder anEbsOrderFromSaleItemsModel() {
        return new EbsOrderFromSaleItemsModelBuilder();
    }

    public EbsOrderFromSaleItemsModelBuilder dataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
        return this;
    }

    public EbsOrderFromSaleItemsModelBuilder orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public EbsOrderFromSaleItemsModelBuilder orderFromSaleId(Long orderFromSaleId) {
        this.orderFromSaleId = orderFromSaleId;
        return this;
    }

    public EbsOrderFromSaleItemsModelBuilder materielNo(String materielNo) {
        this.materielNo = materielNo;
        return this;
    }

    public EbsOrderFromSaleItemsModelBuilder quantity(BigDecimal quantity) {
        this.quantity = quantity;
        return this;
    }

    public EbsOrderFromSaleItemsModelBuilder factor(BigDecimal factor) {
        this.factor = factor;
        return this;
    }

    public EbsOrderFromSaleItemsModelBuilder unit(String unit) {
        this.unit = unit;
        return this;
    }

    public EbsOrderFromSaleItemsModelBuilder secondUnit(String secondUnit) {
        this.secondUnit = secondUnit;
        return this;
    }

    public EbsOrderFromSaleItemsModelBuilder organizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
        return this;
    }

    public EbsOrderFromSaleItemsModelBuilder orgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    public EbsOrderFromSaleItemsModelBuilder saleOrderItemJson(String saleOrderItemJson) {
        this.saleOrderItemJson = saleOrderItemJson;
        return this;
    }

    public EbsOrderFromSaleItemsModel build() {
        EbsOrderFromSaleItemsModel ebsOrderFromSaleItemsModel = new EbsOrderFromSaleItemsModel();
        ebsOrderFromSaleItemsModel.setDataStatus(dataStatus);
        ebsOrderFromSaleItemsModel.setOrderId(orderId);
        ebsOrderFromSaleItemsModel.setOrderFromSaleId(orderFromSaleId);
        ebsOrderFromSaleItemsModel.setMaterielNo(materielNo);
        ebsOrderFromSaleItemsModel.setQuantity(quantity);
        ebsOrderFromSaleItemsModel.setFactor(factor);
        ebsOrderFromSaleItemsModel.setUnit(unit);
        ebsOrderFromSaleItemsModel.setSecondUnit(secondUnit);
        ebsOrderFromSaleItemsModel.setOrganizationCode(organizationCode);
        ebsOrderFromSaleItemsModel.setOrgId(orgId);
        ebsOrderFromSaleItemsModel.setSaleOrderItemJson(saleOrderItemJson);
        return ebsOrderFromSaleItemsModel;
    }
}
