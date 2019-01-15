package com.newhope.openapi.api.vo.response.order;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author     ：yyq
 * @Date       ：Created in  2018/11/19 0019 19:11
 * @Param      ：
 */
public class OrderDetailInfoResult implements Serializable{

    private static final long serialVersionUID = -5744586444502590224L;

    @ApiModelProperty(name = "id", notes = "订单主键id")
    private Long id;
    @ApiModelProperty(name = "orderNo", notes = "订单号")
    private String orderNo;
    @ApiModelProperty(name = "cusOrderType", notes = "订单表加客户订单交易类型")
    private String cusOrderType;

    @ApiModelProperty(name = "ucShopName", notes = "商户名")
    private String ucShopName;
    @ApiModelProperty(name = "status", notes = "订单状态")
    private String status;
    @ApiModelProperty(name = "ucShopMobile", notes = "商户电话")
    private String ucShopMobile;
    @ApiModelProperty(name = "gmtCreated", notes = "下单时间")
    private Date gmtCreated;

    @ApiModelProperty(name = "planTransportTime", notes = "计划拉料时间")
    private Date planTransportTime;
    @ApiModelProperty(name = "totalFeedWeight", notes = "拉料总重(千克)")
    private BigDecimal totalFeedWeight;

    @ApiModelProperty(name = "totalOrginalAmount", notes = "出厂总金额")
    private BigDecimal totalOrginalAmount;
    @ApiModelProperty(name = "totalPayAmount", notes = "实际支付总金额")
    private BigDecimal totalPayAmount;

    @ApiModelProperty(name = "totalDiscountAmount", notes = "折扣总金额")
    private BigDecimal totalDiscountAmount;

    @ApiModelProperty(name = "transportTime", notes = "进厂时间")
    private Date transportTime;

    @ApiModelProperty(name = "checkinTime", notes = "开票时间")
    private Date checkinTime;

    @ApiModelProperty(name = "dealTime", notes = "出厂时间")
    private Date dealTime;

    @ApiModelProperty(name = "endTime", notes = "完成时间")
    private Date endTime;

    @ApiModelProperty(name = "closeTime", notes = "关闭时间")
    private Date closeTime;

    @ApiModelProperty(name = "payLimitTime", notes = "剩余支付时间")
    private Long payLimitTime;

    @ApiModelProperty(name = "transportStart", notes = "拉料限制开始时间")
    private Date transportStart;
    @ApiModelProperty(name = "transportEnd", notes = "拉料限制结束时间")
    private Date transportEnd;
    @ApiModelProperty(name = "canUpdateTime", notes = "canUpdateTime-now()<0:不可修改拉料时间,canUpdateTime-now()>0:可修改拉料时间")
    private Date canUpdateTime;

    @ApiModelProperty(name = "totalPresent", notes = "共赠送重量(千克)")
    private BigDecimal totalPresent;
    
    @ApiModelProperty(name="payFlag",notes="是否已经支付成功")
    private Boolean payFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUcShopName() {
        return ucShopName;
    }

    public void setUcShopName(String ucShopName) {
        this.ucShopName = ucShopName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUcShopMobile() {
        return ucShopMobile;
    }

    public void setUcShopMobile(String ucShopMobile) {
        this.ucShopMobile = ucShopMobile;
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

    public BigDecimal getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(BigDecimal totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount;
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

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
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

    public Long getPayLimitTime() {
        return payLimitTime;
    }

    public void setPayLimitTime(Long payLimitTime) {
        this.payLimitTime = payLimitTime;
    }

    public Date getTransportStart() {
        return transportStart;
    }

    public void setTransportStart(Date transportStart) {
        this.transportStart = transportStart;
    }

    public Date getTransportEnd() {
        return transportEnd;
    }

    public void setTransportEnd(Date transportEnd) {
        this.transportEnd = transportEnd;
    }

    public Date getCanUpdateTime() {
        return canUpdateTime;
    }

    public void setCanUpdateTime(Date canUpdateTime) {
        this.canUpdateTime = canUpdateTime;
    }

    public BigDecimal getTotalPresent() {
        return totalPresent;
    }

    public void setTotalPresent(BigDecimal totalPresent) {
        this.totalPresent = totalPresent;
    }

	public Boolean getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(Boolean payFlag) {
		this.payFlag = payFlag;
	}

	public String getCusOrderType() {
		return cusOrderType;
	}

	public void setCusOrderType(String cusOrderType) {
		this.cusOrderType = cusOrderType;
	}

    
}
