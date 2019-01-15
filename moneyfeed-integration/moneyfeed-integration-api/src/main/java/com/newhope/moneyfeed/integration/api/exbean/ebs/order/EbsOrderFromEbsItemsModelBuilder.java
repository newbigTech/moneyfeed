package com.newhope.moneyfeed.integration.api.exbean.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromEbsItemsModel;

import java.math.BigDecimal;

/**
 * Created by yuyanlin on 2018/11/22
 */
public final class EbsOrderFromEbsItemsModelBuilder {
    private String dataStatus;
    private Long orderId;
    private Long orderFromEbsId;
    private String itemType;
    private String materielNo;
    private BigDecimal quantity;
    private BigDecimal factor;
    private String unit;
    private String secondUnit;
    private BigDecimal planAmount;
    private BigDecimal discountAmount;
    private BigDecimal realPayAmount;
    private String organizationCode;
    private String orgId;
    private String ebsOrderItemJson;

    private EbsOrderFromEbsItemsModelBuilder() {
    }

    public static EbsOrderFromEbsItemsModelBuilder anEbsOrderFromEbsItemsModel() {
        return new EbsOrderFromEbsItemsModelBuilder();
    }

    public EbsOrderFromEbsItemsModelBuilder dataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
        return this;
    }

    public EbsOrderFromEbsItemsModelBuilder orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public EbsOrderFromEbsItemsModelBuilder orderFromEbsId(Long orderFromEbsId) {
        this.orderFromEbsId = orderFromEbsId;
        return this;
    }

    public EbsOrderFromEbsItemsModelBuilder itemType(String itemType) {
        this.itemType = itemType;
        return this;
    }

    public EbsOrderFromEbsItemsModelBuilder materielNo(String materielNo) {
        this.materielNo = materielNo;
        return this;
    }

    public EbsOrderFromEbsItemsModelBuilder quantity(BigDecimal quantity) {
        this.quantity = quantity;
        return this;
    }

    public EbsOrderFromEbsItemsModelBuilder factor(BigDecimal factor) {
        this.factor = factor;
        return this;
    }

    public EbsOrderFromEbsItemsModelBuilder unit(String unit) {
        this.unit = unit;
        return this;
    }

    public EbsOrderFromEbsItemsModelBuilder secondUnit(String secondUnit) {
        this.secondUnit = secondUnit;
        return this;
    }

    public EbsOrderFromEbsItemsModelBuilder planAmount(BigDecimal planAmount) {
        this.planAmount = planAmount;
        return this;
    }

    public EbsOrderFromEbsItemsModelBuilder discountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    public EbsOrderFromEbsItemsModelBuilder realPayAmount(BigDecimal realPayAmount) {
        this.realPayAmount = realPayAmount;
        return this;
    }

    public EbsOrderFromEbsItemsModelBuilder organizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
        return this;
    }

    public EbsOrderFromEbsItemsModelBuilder orgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    public EbsOrderFromEbsItemsModelBuilder ebsOrderItemJson(String ebsOrderItemJson) {
        this.ebsOrderItemJson = ebsOrderItemJson;
        return this;
    }

    public EbsOrderFromEbsItemsModel build() {
        EbsOrderFromEbsItemsModel ebsOrderFromEbsItemsModel = new EbsOrderFromEbsItemsModel();
        ebsOrderFromEbsItemsModel.setDataStatus(dataStatus);
        ebsOrderFromEbsItemsModel.setOrderId(orderId);
        ebsOrderFromEbsItemsModel.setOrderFromEbsId(orderFromEbsId);
        ebsOrderFromEbsItemsModel.setItemType(itemType);
        ebsOrderFromEbsItemsModel.setMaterielNo(materielNo);
        ebsOrderFromEbsItemsModel.setQuantity(quantity);
        ebsOrderFromEbsItemsModel.setFactor(factor);
        ebsOrderFromEbsItemsModel.setUnit(unit);
        ebsOrderFromEbsItemsModel.setSecondUnit(secondUnit);
        ebsOrderFromEbsItemsModel.setPlanAmount(planAmount);
        ebsOrderFromEbsItemsModel.setDiscountAmount(discountAmount);
        ebsOrderFromEbsItemsModel.setRealPayAmount(realPayAmount);
        ebsOrderFromEbsItemsModel.setOrganizationCode(organizationCode);
        ebsOrderFromEbsItemsModel.setOrgId(orgId);
        ebsOrderFromEbsItemsModel.setEbsOrderItemJson(ebsOrderItemJson);
        return ebsOrderFromEbsItemsModel;
    }
}
