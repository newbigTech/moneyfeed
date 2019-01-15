package com.newhope.moneyfeed.integration.api.bean.ebs.order;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.util.Date;

public class EbsOrderFromSaleModel extends BaseModel {
    /** 中间件订单ID */
    private Long orderId;

    /** EBS订单JSON */
    private String saleOrderJosn;

    private String dataStatus;

    /** 计划拉货时间 */
    private Date planPickupDate;

    /** 商场ID */
    private String shopId;

    /** 车牌号 */
    private String carNo;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSaleOrderJosn() {
        return saleOrderJosn;
    }

    public void setSaleOrderJosn(String saleOrderJosn) {
        this.saleOrderJosn = saleOrderJosn;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Date getPlanPickupDate() {
        return planPickupDate;
    }

    public void setPlanPickupDate(Date planPickupDate) {
        this.planPickupDate = planPickupDate;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }
}