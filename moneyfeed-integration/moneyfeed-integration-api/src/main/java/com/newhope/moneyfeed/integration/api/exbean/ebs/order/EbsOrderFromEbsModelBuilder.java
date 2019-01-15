package com.newhope.moneyfeed.integration.api.exbean.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromEbsModel;

import java.math.BigDecimal;

/**
 * Created by yuyanlin on 2018/11/22
 */
public final class EbsOrderFromEbsModelBuilder {
    private Long orderId;
    private BigDecimal planTotalAmount;
    private BigDecimal discountAmount;
    private BigDecimal realPayAmount;
    private String dataStatus;
    private String ebsOrderJosn;

    private EbsOrderFromEbsModelBuilder() {
    }

    public static EbsOrderFromEbsModelBuilder anEbsOrderFromEbsModel() {
        return new EbsOrderFromEbsModelBuilder();
    }

    public EbsOrderFromEbsModelBuilder orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public EbsOrderFromEbsModelBuilder planTotalAmount(BigDecimal planTotalAmount) {
        this.planTotalAmount = planTotalAmount;
        return this;
    }

    public EbsOrderFromEbsModelBuilder discountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    public EbsOrderFromEbsModelBuilder realPayAmount(BigDecimal realPayAmount) {
        this.realPayAmount = realPayAmount;
        return this;
    }

    public EbsOrderFromEbsModelBuilder dataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
        return this;
    }

    public EbsOrderFromEbsModelBuilder ebsOrderJosn(String ebsOrderJosn) {
        this.ebsOrderJosn = ebsOrderJosn;
        return this;
    }

    public EbsOrderFromEbsModel build() {
        EbsOrderFromEbsModel ebsOrderFromEbsModel = new EbsOrderFromEbsModel();
        ebsOrderFromEbsModel.setOrderId(orderId);
        ebsOrderFromEbsModel.setPlanTotalAmount(planTotalAmount);
        ebsOrderFromEbsModel.setDiscountAmount(discountAmount);
        ebsOrderFromEbsModel.setRealPayAmount(realPayAmount);
        ebsOrderFromEbsModel.setDataStatus(dataStatus);
        ebsOrderFromEbsModel.setEbsOrderJosn(ebsOrderJosn);
        return ebsOrderFromEbsModel;
    }
}
