package com.newhope.moneyfeed.integration.api.bean.ebs.order;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;

public class EbsOrderFromEbsModel extends BaseModel {
    /** 中间件订单ID */
    private Long orderId;

    /** 订单总额（厂价） */
    private BigDecimal planTotalAmount;

    /** 折扣金额 */
    private BigDecimal discountAmount;

    /** 实际付款金额 */
    private BigDecimal realPayAmount;

    private String dataStatus;

    /** EBS订单JSON */
    private String ebsOrderJosn;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPlanTotalAmount() {
        return planTotalAmount;
    }

    public void setPlanTotalAmount(BigDecimal planTotalAmount) {
        this.planTotalAmount = planTotalAmount;
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

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getEbsOrderJosn() {
        return ebsOrderJosn;
    }

    public void setEbsOrderJosn(String ebsOrderJosn) {
        this.ebsOrderJosn = ebsOrderJosn;
    }
}