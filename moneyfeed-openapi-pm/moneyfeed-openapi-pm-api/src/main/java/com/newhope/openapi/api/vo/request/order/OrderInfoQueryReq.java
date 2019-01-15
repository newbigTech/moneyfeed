package com.newhope.openapi.api.vo.request.order;

import com.newhope.moneyfeed.api.vo.request.PageReq;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/19 10:28
 */
public class OrderInfoQueryReq  extends PageReq implements Serializable{
    private static final long serialVersionUID = -1073788381194940006L;
    @ApiModelProperty(name = "ucShopId", notes = "商户店铺ID")
    private Long ucShopId;
    @ApiModelProperty(name = "orderNo", notes = "订单号")
    private String orderNo;
    @ApiModelProperty(name = "shortNo", notes = "订单展示号")
    private String shortNo;
    @ApiModelProperty(name = "ebsorderNo", notes = "EBS订单号")
    private String ebsorderNo;
    @ApiModelProperty(name = "moneyNo", notes = "资金订单号")
    private String moneyNo;
    @ApiModelProperty(name = "ebsRefundOrderNo", notes = "EBS退款订单号")
    private String ebsRefundOrderNo;
    @ApiModelProperty(name = "bankRefundOrderNo", notes = "银行退款流水号")
    private String bankRefundOrderNo;
    @ApiModelProperty(name = "bankOrderNo", notes = "银行流水号")
    private String bankOrderNo;
    @ApiModelProperty(name = "customerId", notes = "客户ID")
    private Long customerId;
    @ApiModelProperty(name = "customerName", notes = "客户名")
    private String customerName;
    @ApiModelProperty(name = "status", notes = "订单状态")
    private String status;
    @ApiModelProperty(name = "orderBeginDate", notes = "下单开始时间yyyy/MM/dd")
    private String orderBeginDate;
    @ApiModelProperty(name = "orderEndDate", notes = "下单结束时间yyyy/MM/dd")
    private String orderEndDate;
    @ApiModelProperty(name = "completeBeginDate", notes = "完成起始时间yyyy/MM/dd")
    private String completeBeginDate;
    @ApiModelProperty(name = "completeEndDate", notes = "完成结束时间yyyy/MM/dd")
    private String completeEndDate;
    @ApiModelProperty(name = "planTransportBeginDate", notes = "预计拉料起始日期yyyy/MM/dd")
    private String planTransportBeginDate;
    @ApiModelProperty(name = "planTransportEndDate", notes = "预计拉料结束日期yyyy/MM/dd")
    private String planTransportEndDate;
    @ApiModelProperty(name = "customerLables", notes = "客户标签")
    private String customerLables;

    public Long getUcShopId() {
        return ucShopId;
    }

    public void setUcShopId(Long ucShopId) {
        this.ucShopId = ucShopId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getShortNo() {
        return shortNo;
    }

    public void setShortNo(String shortNo) {
        this.shortNo = shortNo;
    }

    public String getEbsorderNo() {
        return ebsorderNo;
    }

    public void setEbsorderNo(String ebsorderNo) {
        this.ebsorderNo = ebsorderNo;
    }

    public String getMoneyNo() {
        return moneyNo;
    }

    public void setMoneyNo(String moneyNo) {
        this.moneyNo = moneyNo;
    }

    public String getEbsRefundOrderNo() {
        return ebsRefundOrderNo;
    }

    public void setEbsRefundOrderNo(String ebsRefundOrderNo) {
        this.ebsRefundOrderNo = ebsRefundOrderNo;
    }

    public String getBankRefundOrderNo() {
        return bankRefundOrderNo;
    }

    public void setBankRefundOrderNo(String bankRefundOrderNo) {
        this.bankRefundOrderNo = bankRefundOrderNo;
    }

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderBeginDate() {
        return orderBeginDate;
    }

    public void setOrderBeginDate(String orderBeginDate) {
        this.orderBeginDate = orderBeginDate;
    }

    public String getOrderEndDate() {
        return orderEndDate;
    }

    public void setOrderEndDate(String orderEndDate) {
        this.orderEndDate = orderEndDate;
    }

    public String getCompleteBeginDate() {
        return completeBeginDate;
    }

    public void setCompleteBeginDate(String completeBeginDate) {
        this.completeBeginDate = completeBeginDate;
    }

    public String getCompleteEndDate() {
        return completeEndDate;
    }

    public void setCompleteEndDate(String completeEndDate) {
        this.completeEndDate = completeEndDate;
    }

    public String getPlanTransportBeginDate() {
        return planTransportBeginDate;
    }

    public void setPlanTransportBeginDate(String planTransportBeginDate) {
        this.planTransportBeginDate = planTransportBeginDate;
    }

    public String getPlanTransportEndDate() {
        return planTransportEndDate;
    }

    public void setPlanTransportEndDate(String planTransportEndDate) {
        this.planTransportEndDate = planTransportEndDate;
    }

    public String getCustomerLables() {
        return customerLables;
    }

    public void setCustomerLables(String customerLables) {
        this.customerLables = customerLables;
    }
}
