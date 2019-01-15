package com.newhope.moneyfeed.integration.api.exbean.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderModel;

import java.math.BigDecimal;

/**
 * Created by yuyanlin on 2018/11/26
 */
public final class EbsOrderModelBuilder {
    private String saleOrderNo;
    private String saleOrderId;
    private String ebsOrderNo;
    private String ebsOrderId;
    private String orderType;
    private String orderCreateStatus;
    private String orderPayStatus;
    private String orderCancelStatus;
    private String orderUpdateStatus;
    private BigDecimal totalAmount;
    private BigDecimal ebsPayAmount;
    private BigDecimal cardPayAmount;
    private String ebsOrgId;
    private String ebsCustomerNo;
    private String bankPayNo;
    private String payType;
    private String dataStatus;

    private EbsOrderModelBuilder() {
    }

    public static EbsOrderModelBuilder anEbsOrderModel() {
        return new EbsOrderModelBuilder();
    }

    public EbsOrderModelBuilder saleOrderNo(String saleOrderNo) {
        this.saleOrderNo = saleOrderNo;
        return this;
    }

    public EbsOrderModelBuilder saleOrderId(String saleOrderId) {
        this.saleOrderId = saleOrderId;
        return this;
    }

    public EbsOrderModelBuilder ebsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
        return this;
    }

    public EbsOrderModelBuilder ebsOrderId(String ebsOrderId) {
        this.ebsOrderId = ebsOrderId;
        return this;
    }

    public EbsOrderModelBuilder orderType(String orderType) {
        this.orderType = orderType;
        return this;
    }

    public EbsOrderModelBuilder orderCreateStatus(String orderCreateStatus) {
        this.orderCreateStatus = orderCreateStatus;
        return this;
    }

    public EbsOrderModelBuilder orderPayStatus(String orderPayStatus) {
        this.orderPayStatus = orderPayStatus;
        return this;
    }

    public EbsOrderModelBuilder orderCancelStatus(String orderCancelStatus) {
        this.orderCancelStatus = orderCancelStatus;
        return this;
    }

    public EbsOrderModelBuilder orderUpdateStatus(String orderUpdateStatus) {
        this.orderUpdateStatus = orderUpdateStatus;
        return this;
    }

    public EbsOrderModelBuilder totalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public EbsOrderModelBuilder ebsPayAmount(BigDecimal ebsPayAmount) {
        this.ebsPayAmount = ebsPayAmount;
        return this;
    }

    public EbsOrderModelBuilder cardPayAmount(BigDecimal cardPayAmount) {
        this.cardPayAmount = cardPayAmount;
        return this;
    }

    public EbsOrderModelBuilder ebsOrgId(String ebsOrgId) {
        this.ebsOrgId = ebsOrgId;
        return this;
    }

    public EbsOrderModelBuilder ebsCustomerNo(String ebsCustomerNo) {
        this.ebsCustomerNo = ebsCustomerNo;
        return this;
    }

    public EbsOrderModelBuilder bankPayNo(String bankPayNo) {
        this.bankPayNo = bankPayNo;
        return this;
    }

    public EbsOrderModelBuilder payType(String payType) {
        this.payType = payType;
        return this;
    }

    public EbsOrderModelBuilder dataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
        return this;
    }

    public EbsOrderModel build() {
        EbsOrderModel ebsOrderModel = new EbsOrderModel();
        ebsOrderModel.setSaleOrderNo(saleOrderNo);
        ebsOrderModel.setSaleOrderId(saleOrderId);
        ebsOrderModel.setEbsOrderNo(ebsOrderNo);
        ebsOrderModel.setEbsOrderId(ebsOrderId);
        ebsOrderModel.setOrderType(orderType);
        ebsOrderModel.setOrderCreateStatus(orderCreateStatus);
        ebsOrderModel.setOrderPayStatus(orderPayStatus);
        ebsOrderModel.setOrderCancelStatus(orderCancelStatus);
        ebsOrderModel.setOrderUpdateStatus(orderUpdateStatus);
        ebsOrderModel.setTotalAmount(totalAmount);
        ebsOrderModel.setEbsPayAmount(ebsPayAmount);
        ebsOrderModel.setCardPayAmount(cardPayAmount);
        ebsOrderModel.setEbsOrgId(ebsOrgId);
        ebsOrderModel.setEbsCustomerNo(ebsCustomerNo);
        ebsOrderModel.setBankPayNo(bankPayNo);
        ebsOrderModel.setPayType(payType);
        ebsOrderModel.setDataStatus(dataStatus);
        return ebsOrderModel;
    }
}
