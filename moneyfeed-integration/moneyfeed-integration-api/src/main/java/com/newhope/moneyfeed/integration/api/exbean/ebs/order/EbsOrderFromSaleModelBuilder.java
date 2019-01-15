package com.newhope.moneyfeed.integration.api.exbean.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromSaleModel;

import java.util.Date;

/**
 * Created by yuyanlin on 2018/11/24
 */
public final class EbsOrderFromSaleModelBuilder {
    private Long orderId;
    private String saleOrderJosn;
    private String dataStatus;
    private Date planPickupDate;
    private String shopId;
    private String carNo;

    private EbsOrderFromSaleModelBuilder() {
    }

    public static EbsOrderFromSaleModelBuilder anEbsOrderFromSaleModel() {
        return new EbsOrderFromSaleModelBuilder();
    }

    public EbsOrderFromSaleModelBuilder orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public EbsOrderFromSaleModelBuilder saleOrderJosn(String saleOrderJosn) {
        this.saleOrderJosn = saleOrderJosn;
        return this;
    }

    public EbsOrderFromSaleModelBuilder dataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
        return this;
    }

    public EbsOrderFromSaleModelBuilder planPickupDate(Date planPickupDate) {
        this.planPickupDate = planPickupDate;
        return this;
    }

    public EbsOrderFromSaleModelBuilder shopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public EbsOrderFromSaleModelBuilder carNo(String carNo) {
        this.carNo = carNo;
        return this;
    }

    public EbsOrderFromSaleModel build() {
        EbsOrderFromSaleModel ebsOrderFromSaleModel = new EbsOrderFromSaleModel();
        ebsOrderFromSaleModel.setOrderId(orderId);
        ebsOrderFromSaleModel.setSaleOrderJosn(saleOrderJosn);
        ebsOrderFromSaleModel.setDataStatus(dataStatus);
        ebsOrderFromSaleModel.setPlanPickupDate(planPickupDate);
        ebsOrderFromSaleModel.setShopId(shopId);
        ebsOrderFromSaleModel.setCarNo(carNo);
        return ebsOrderFromSaleModel;
    }
}
