package com.newhope.openapi.api.vo.response.order.info;

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
    @ApiModelProperty(name = "shortNo", notes = "订单展示号")
    private String shortNo;
    @ApiModelProperty(name = "orderNo", notes = "订单号")
    private String orderNo;
    @ApiModelProperty(name = "totalFeedWeight", notes = "共购料重量")
    private BigDecimal totalFeedWeight;
    @ApiModelProperty(name = "totalPayAmount", notes = "实际支付金额/待支付金额")
    private BigDecimal totalPayAmount;
    @ApiModelProperty(name = "gmtCreated", notes = "下单时间")
    private Date gmtCreated;
    @ApiModelProperty(name = "planTransportTime", notes = "拉料时间")
    private Date planTransportTime;
    @ApiModelProperty(name = "endTime", notes = "完成时间")
    private Date endTime;
    @ApiModelProperty(name = "closeTime", notes = "关闭时间")
    private Date closeTime;
    @ApiModelProperty(name = "status", notes = "订单状态")
    private String status;
    @ApiModelProperty(name = "carNo", notes = "车牌号")
    private String carNo;
    @ApiModelProperty(name = "payLimitTime", notes = "支付剩余时间")
    private Long payLimitTime;
    @ApiModelProperty(name = "feedTransId", notes = "拉料信息id")
    private String feedTransId;
    @ApiModelProperty(name = "ucShopMobile", notes = "商户电话")
    private String ucShopMobile;
    @ApiModelProperty(name="payFlag",notes="是否已经支付成功")
    private Boolean payFlag;

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

    public BigDecimal getTotalFeedWeight() {
        return totalFeedWeight;
    }

    public void setTotalFeedWeight(BigDecimal totalFeedWeight) {
        this.totalFeedWeight = totalFeedWeight;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getPlanTransportTime() {
        return planTransportTime;
    }

    public void setPlanTransportTime(Date planTransportTime) {
        this.planTransportTime = planTransportTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getPayLimitTime() {
        return payLimitTime;
    }

    public void setPayLimitTime(Long payLimitTime) {
        this.payLimitTime = payLimitTime;
    }

    public String getFeedTransId() {
        return feedTransId;
    }

    public void setFeedTransId(String feedTransId) {
        this.feedTransId = feedTransId;
    }

    public String getUcShopMobile() {
        return ucShopMobile;
    }

    public void setUcShopMobile(String ucShopMobile) {
        this.ucShopMobile = ucShopMobile;
    }

	public Boolean getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(Boolean payFlag) {
		this.payFlag = payFlag;
	}
}
