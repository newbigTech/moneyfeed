package com.newhope.moneyfeed.integration.api.bean.ebs.order;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;

public class EbsOrderModel extends BaseModel {
    private String saleOrderNo;

    private String saleOrderId;

    private String ebsOrderNo;

    private String ebsOrderId;

    /** 订单类型
            1.直接订单，系统自动算
            2.需审核订单，由后勤计算价格 */
    private String orderType;

    /** 1.未提交
            2.处理中
            3.业务处理失败
            4.业务处理成功
            5.调用EBS接口网络不通 （失败）
            6.等待EBS处理结果超时（成功） */
    private String orderCreateStatus;

    /** 1.未提交
            2.处理中
            3.业务处理失败
            4.业务处理成功
            5.调用EBS接口网络不通 （失败）
            6.等待EBS处理结果超时（成功） */
    private String orderPayStatus;

    /** 1.未提交
            2.处理中
            3.业务处理失败
            4.业务处理成功
            5.调用EBS接口网络不通 （失败）
            6.等待EBS处理结果超时（成功） */
    private String orderCancelStatus;

    /** 1.未提交
            2.处理中
            3.业务处理失败
            4.业务处理成功
            5.调用EBS接口网络不通 （失败）
            6.等待EBS处理结果超时（成功） */
    private String orderUpdateStatus;

    private BigDecimal totalAmount;

    private BigDecimal ebsPayAmount;

    private BigDecimal cardPayAmount;

    private String ebsOrgId;

    private String ebsCustomerNo;

    private String bankPayNo;

    /** 支付方式
            1.余额支付
            2.银行卡支付
            3.混合支付 */
    private String payType;

    private String dataStatus;

    public String getSaleOrderNo() {
        return saleOrderNo;
    }

    public void setSaleOrderNo(String saleOrderNo) {
        this.saleOrderNo = saleOrderNo;
    }

    public String getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(String saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }

    public String getEbsOrderId() {
        return ebsOrderId;
    }

    public void setEbsOrderId(String ebsOrderId) {
        this.ebsOrderId = ebsOrderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderCreateStatus() {
        return orderCreateStatus;
    }

    public void setOrderCreateStatus(String orderCreateStatus) {
        this.orderCreateStatus = orderCreateStatus;
    }

    public String getOrderPayStatus() {
        return orderPayStatus;
    }

    public void setOrderPayStatus(String orderPayStatus) {
        this.orderPayStatus = orderPayStatus;
    }

    public String getOrderCancelStatus() {
        return orderCancelStatus;
    }

    public void setOrderCancelStatus(String orderCancelStatus) {
        this.orderCancelStatus = orderCancelStatus;
    }

    public String getOrderUpdateStatus() {
        return orderUpdateStatus;
    }

    public void setOrderUpdateStatus(String orderUpdateStatus) {
        this.orderUpdateStatus = orderUpdateStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getEbsPayAmount() {
        return ebsPayAmount;
    }

    public void setEbsPayAmount(BigDecimal ebsPayAmount) {
        this.ebsPayAmount = ebsPayAmount;
    }

    public BigDecimal getCardPayAmount() {
        return cardPayAmount;
    }

    public void setCardPayAmount(BigDecimal cardPayAmount) {
        this.cardPayAmount = cardPayAmount;
    }

    public String getEbsOrgId() {
        return ebsOrgId;
    }

    public void setEbsOrgId(String ebsOrgId) {
        this.ebsOrgId = ebsOrgId;
    }

    public String getEbsCustomerNo() {
        return ebsCustomerNo;
    }

    public void setEbsCustomerNo(String ebsCustomerNo) {
        this.ebsCustomerNo = ebsCustomerNo;
    }

    public String getBankPayNo() {
        return bankPayNo;
    }

    public void setBankPayNo(String bankPayNo) {
        this.bankPayNo = bankPayNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }
}