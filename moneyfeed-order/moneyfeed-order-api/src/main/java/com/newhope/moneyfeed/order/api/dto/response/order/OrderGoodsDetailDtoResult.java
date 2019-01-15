package com.newhope.moneyfeed.order.api.dto.response.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author: dongql
 * @date: 2018/12/5 14:53
 */
public class OrderGoodsDetailDtoResult implements Serializable{
    private static final long serialVersionUID = -3781230777711545511L;

    @ApiModelProperty(name = "id", notes = "订单主键id")
    private Long id;
    @ApiModelProperty(name = "shortNo", notes = "订单展示号")
    private String shortNo;
    @ApiModelProperty(name = "orderNo", notes = "订单号")
    private String orderNo;
    @ApiModelProperty(name = "customerId", notes = "客户ID")
    private Long customerId;
    @ApiModelProperty(name = "customerName", notes = "客户名")
    private String customerName;
    @ApiModelProperty(name = "productCode", notes = "商品ebs编码")
    private String productCode;
    @ApiModelProperty(name = "productName", notes = "商品名称")
    private String productName;
    @ApiModelProperty(name = "productCount", notes = "商品数量")
    private Integer productCount;
    @ApiModelProperty(name = "totalFeedWeight", notes = "商品总吨数")
    private BigDecimal totalFeedWeight;
    @ApiModelProperty(name = "gmtCreated", notes = "下单时间")
    private Date gmtCreated;
    @ApiModelProperty(name = "endTime", notes = "完成时间")
    private Date endTime;
    @ApiModelProperty(name = "status", notes = "订单状态")
    private String status;
    @ApiModelProperty(name = "ebsorderNo", notes = "EBS订单号")
    private String ebsorderNo;
    @ApiModelProperty(name = "ebsRefundOrderNo", notes = "EBS退款订单号")
    private String ebsRefundOrderNo;
    @ApiModelProperty(name = "planTransportTime", notes = "拉料日期")
    private Date planTransportTime;
    @ApiModelProperty(name = "carNo", notes = "车牌号")
    private String carNo;
    @ApiModelProperty(name = "orderChannel", notes = "下单渠道")
    private String orderChannel;
    @ApiModelProperty(name = "proxName", notes = "下单人")
    private String proxName;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getTotalFeedWeight() {
        return totalFeedWeight;
    }

    public void setTotalFeedWeight(BigDecimal totalFeedWeight) {
        this.totalFeedWeight = totalFeedWeight;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEbsorderNo() {
        return ebsorderNo;
    }

    public void setEbsorderNo(String ebsorderNo) {
        this.ebsorderNo = ebsorderNo;
    }

    public String getEbsRefundOrderNo() {
        return ebsRefundOrderNo;
    }

    public void setEbsRefundOrderNo(String ebsRefundOrderNo) {
        this.ebsRefundOrderNo = ebsRefundOrderNo;
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
}
