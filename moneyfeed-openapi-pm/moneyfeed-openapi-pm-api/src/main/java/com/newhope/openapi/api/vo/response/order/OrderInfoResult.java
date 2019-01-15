package com.newhope.openapi.api.vo.response.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/19 9:47
 */
public class OrderInfoResult implements Serializable{

    private static final long serialVersionUID = -5744586444502590224L;
    @ApiModelProperty(name = "id", notes = "订单主键id")
    private Long id;
    /** 订单展示号 */
    @ApiModelProperty(name = "shortNo", notes = "订单号")
    private String shortNo;
    @ApiModelProperty(name = "orderNo", notes = "订单号")
    private String orderNo;
    @ApiModelProperty(name = "ebsorderNo", notes = "EBS订单号")
    private String ebsorderNo;
    @ApiModelProperty(name = "customerId", notes = "客户ID")
    private Long customerId;
    @ApiModelProperty(name = "customerName", notes = "客户名")
    private String customerName;
    /** 拉料总重(千克) */
    @ApiModelProperty(name = "totalFeedWeight", notes = "购买总量")
    private BigDecimal totalFeedWeight;
    /** 出厂总金额 */
    @ApiModelProperty(name = "totalOrginalAmount", notes = "购买总金额")
    private BigDecimal totalOrginalAmount;
    /** 实际支付总金额 */
    @ApiModelProperty(name = "totalPayAmount", notes = "实际支付金额/待支付金额/应付总额")
    private BigDecimal totalPayAmount;
    @ApiModelProperty(name = "moneyNo", notes = "资金订单号")
    private String moneyNo;
    @ApiModelProperty(name = "bankOrderNo", notes = "银行流水号")
    private String bankOrderNo;
    @ApiModelProperty(name = "gmtCreated", notes = "下单时间")
    private Date gmtCreated;
    @ApiModelProperty(name = "transportTime", notes = "进厂时间")
    private Date transportTime;
    @ApiModelProperty(name = "checkinTime", notes = "开票时间")
    private Date checkinTime;
    @ApiModelProperty(name = "dealTime", notes = "出厂时间")
    private Date dealTime;
    @ApiModelProperty(name = "endTime", notes = "完成时间")
    private Date endTime;
    @ApiModelProperty(name = "status", notes = "订单状态")
    private String status;
    @ApiModelProperty(name = "ebsRefundOrderNo", notes = "EBS退款订单号")
    private String ebsRefundOrderNo;
    @ApiModelProperty(name = "bankRefundOrderNo", notes = "银行退款流水号")
    private String bankRefundOrderNo;
    @ApiModelProperty(name = "shopId", notes = "商户店铺ID")
    private String shopId;
    @ApiModelProperty(name = "shopName", notes = "商户名")
    private String ucShopName;
    @ApiModelProperty(name = "planTransportTime", notes = "拉料时间")
    private Date planTransportTime;
    @ApiModelProperty(name = "carNo", notes = "车牌号")
    private String carNo;
    @ApiModelProperty(name = "driverName", notes = "司机姓名")
    private String driverName;
    @ApiModelProperty(name = "driverMobile", notes = "司机电话")
    private String driverMobile;
    @ApiModelProperty(name = "orderChannel", notes = "下单渠道")
    private String orderChannel;
    @ApiModelProperty(name = "proxName", notes = "代下单人")
    private String proxName;
    @ApiModelProperty(name = "proxUserId", notes = "代下单人Id")
    private Long proxUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getTotalFeedWeight() {
        return totalFeedWeight;
    }

    public void setTotalFeedWeight(BigDecimal totalFeedWeight) {
        this.totalFeedWeight = totalFeedWeight;
    }

    public BigDecimal getTotalOrginalAmount() {
        return totalOrginalAmount;
    }

    public void setTotalOrginalAmount(BigDecimal totalOrginalAmount) {
        this.totalOrginalAmount = totalOrginalAmount;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public String getMoneyNo() {
        return moneyNo;
    }

    public void setMoneyNo(String moneyNo) {
        this.moneyNo = moneyNo;
    }

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getUcShopName() {
        return ucShopName;
    }

    public void setUcShopName(String ucShopName) {
        this.ucShopName = ucShopName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getTransportTime() {
        return transportTime;
    }

    public void setTransportTime(Date transportTime) {
        this.transportTime = transportTime;
    }

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getPlanTransportTime() {
        return planTransportTime;
    }

    public void setPlanTransportTime(Date planTransportTime) {
        this.planTransportTime = planTransportTime;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverMobile() {
        return driverMobile;
    }

    public void setDriverMobile(String driverMobile) {
        this.driverMobile = driverMobile;
    }

    public String getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(String orderChannel) {
        this.orderChannel = orderChannel;
    }

    public String getProxName() {
        return proxName;
    }

    public void setProxName(String proxName) {
        this.proxName = proxName;
    }

    public Long getProxUserId() {
        return proxUserId;
    }

    public void setProxUserId(Long proxUserId) {
        this.proxUserId = proxUserId;
    }
}
