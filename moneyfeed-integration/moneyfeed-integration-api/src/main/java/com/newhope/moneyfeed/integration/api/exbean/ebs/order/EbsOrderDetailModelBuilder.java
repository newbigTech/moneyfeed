package com.newhope.moneyfeed.integration.api.exbean.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderDetailModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yuyanlin on 2018/12/12
 */
public final class EbsOrderDetailModelBuilder {
    private String dataStatus;
    private Date orderCreateDate;
    private Long orderId;
    private String ebsOrderStatus;
    private Date carInTime;
    private Date closeOrderTime;
    private Date invoiceOrderTime;
    private Date cancelOrderTime;
    private Date carOutTime;
    private String carNo;
    private BigDecimal inCarWeight;
    private BigDecimal outCarWeight;
    private Boolean stockCanUse;
    private Date planTransportTime;
    private String weightNum;
    private Boolean materialChanged;

    private EbsOrderDetailModelBuilder() {
    }

    public static EbsOrderDetailModelBuilder anEbsOrderDetailModel() {
        return new EbsOrderDetailModelBuilder();
    }

    public EbsOrderDetailModelBuilder dataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
        return this;
    }

    public EbsOrderDetailModelBuilder orderCreateDate(Date orderCreateDate) {
        this.orderCreateDate = orderCreateDate;
        return this;
    }

    public EbsOrderDetailModelBuilder orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public EbsOrderDetailModelBuilder ebsOrderStatus(String ebsOrderStatus) {
        this.ebsOrderStatus = ebsOrderStatus;
        return this;
    }

    public EbsOrderDetailModelBuilder carInTime(Date carInTime) {
        this.carInTime = carInTime;
        return this;
    }

    public EbsOrderDetailModelBuilder closeOrderTime(Date closeOrderTime) {
        this.closeOrderTime = closeOrderTime;
        return this;
    }

    public EbsOrderDetailModelBuilder invoiceOrderTime(Date invoiceOrderTime) {
        this.invoiceOrderTime = invoiceOrderTime;
        return this;
    }

    public EbsOrderDetailModelBuilder cancelOrderTime(Date cancelOrderTime) {
        this.cancelOrderTime = cancelOrderTime;
        return this;
    }

    public EbsOrderDetailModelBuilder carOutTime(Date carOutTime) {
        this.carOutTime = carOutTime;
        return this;
    }

    public EbsOrderDetailModelBuilder carNo(String carNo) {
        this.carNo = carNo;
        return this;
    }

    public EbsOrderDetailModelBuilder inCarWeight(BigDecimal inCarWeight) {
        this.inCarWeight = inCarWeight;
        return this;
    }

    public EbsOrderDetailModelBuilder outCarWeight(BigDecimal outCarWeight) {
        this.outCarWeight = outCarWeight;
        return this;
    }

    public EbsOrderDetailModelBuilder stockCanUse(Boolean stockCanUse) {
        this.stockCanUse = stockCanUse;
        return this;
    }

    public EbsOrderDetailModelBuilder planTransportTime(Date planTransportTime) {
        this.planTransportTime = planTransportTime;
        return this;
    }

    public EbsOrderDetailModelBuilder weightNum(String weightNum) {
        this.weightNum = weightNum;
        return this;
    }

    public EbsOrderDetailModelBuilder materialChanged(Boolean materialChanged) {
        this.materialChanged = materialChanged;
        return this;
    }

    public EbsOrderDetailModel build() {
        EbsOrderDetailModel ebsOrderDetailModel = new EbsOrderDetailModel();
        ebsOrderDetailModel.setDataStatus(dataStatus);
        ebsOrderDetailModel.setOrderCreateDate(orderCreateDate);
        ebsOrderDetailModel.setOrderId(orderId);
        ebsOrderDetailModel.setEbsOrderStatus(ebsOrderStatus);
        ebsOrderDetailModel.setCarInTime(carInTime);
        ebsOrderDetailModel.setCloseOrderTime(closeOrderTime);
        ebsOrderDetailModel.setInvoiceOrderTime(invoiceOrderTime);
        ebsOrderDetailModel.setCancelOrderTime(cancelOrderTime);
        ebsOrderDetailModel.setCarOutTime(carOutTime);
        ebsOrderDetailModel.setCarNo(carNo);
        ebsOrderDetailModel.setInCarWeight(inCarWeight);
        ebsOrderDetailModel.setOutCarWeight(outCarWeight);
        ebsOrderDetailModel.setStockCanUse(stockCanUse);
        ebsOrderDetailModel.setPlanTransportTime(planTransportTime);
        ebsOrderDetailModel.setWeightNum(weightNum);
        ebsOrderDetailModel.setMaterialChanged(materialChanged);
        return ebsOrderDetailModel;
    }
}
