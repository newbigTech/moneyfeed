package com.newhope.moneyfeed.order.api.dto.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Create by yyq on 2018/11/17
 */
public class OrderDtoResult implements Serializable {
    private static final long serialVersionUID = -577753675477253317L;
    @ApiModelProperty(name = "id", notes = "订单主键id")
    private Long id;
    @ApiModelProperty(name = "shortNo", notes = "订单展示号")
    private String shortNo;
    @ApiModelProperty(name = "orderNo", notes = "订单号")
    private String orderNo;
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
    @ApiModelProperty(name = "gmtCreated", notes = "下单时间")
    private Date gmtCreated;
    @ApiModelProperty(name = "totalFeedWeight", notes = "共购料重量(千克)")
    private BigDecimal totalFeedWeight;
    @ApiModelProperty(name = "totalOrginalAmount", notes = "购买总金额")
    private BigDecimal totalOrginalAmount;
    /** 实际支付总金额 */
    @ApiModelProperty(name = "totalPayAmount", notes = "实际支付金额/待支付金额/应付总额")
    private BigDecimal totalPayAmount;
    @ApiModelProperty(name = "planTransportTime", notes = "拉料时间")
    private Date planTransportTime;
    @ApiModelProperty(name = "closeTime", notes = "关闭时间")
    private Date closeTime;
    @ApiModelProperty(name = "ucShopName", notes = "商户名")
    private String ucShopName;
    @ApiModelProperty(name = "ucShopAddress", notes = "商户地址")
    private String ucShopAddress;
    @ApiModelProperty(name = "ucShopMobile", notes = "商户电话")
    private String ucShopMobile;
    @ApiModelProperty(name = "ucShopMobile", notes = "折扣总金额")
    private BigDecimal totalDiscountAmount;
    @ApiModelProperty(name = "payLimitTime", notes = "限制时间内完成支付")
    private Date payLimitTime;
    @ApiModelProperty(name = "limitTimeType", notes = "订单支付限制时间类型")
    private String limitTimeType;
    @ApiModelProperty(name = "shopId", notes = "商户店铺ID")
    private String shopId;
    @ApiModelProperty(name = "transportTime", notes = "进厂时间")
    private Date transportTime;
    @ApiModelProperty(name = "checkinTime", notes = "开票时间")
    private Date checkinTime;
    @ApiModelProperty(name = "dealTime", notes = "出厂时间")
    private Date dealTime;
    @ApiModelProperty(name = "endTime", notes = "完成时间")
    private Date endTime;
    @ApiModelProperty(name = "feedTransId", notes = "拉料信息id")
    private String feedTransId;
    @ApiModelProperty(name = "carNo", notes = "车牌号")
    private String carNo;
    @ApiModelProperty(name = "driverName", notes = "司机姓名")
    private String driverName;
    @ApiModelProperty(name = "driverMobile", notes = "司机电话")
    private String driverMobile;
    @ApiModelProperty(name = "ebsCarNo", notes = "实际拉料车牌")
    private String ebsCarNo;
    @ApiModelProperty(name = "transportStart", notes = "拉料限制开始时间")
    private Date transportStart;
    @ApiModelProperty(name = "transportEnd", notes = "拉料限制结束时间")
    private Date transportEnd;
    @ApiModelProperty(name = "buyerId", notes = "买家用户ID")
    private Long buyerId;
    @ApiModelProperty(name = "buyerMobile", notes = "买家电话")
    private String buyerMobile;
    @ApiModelProperty(name = "buyerName", notes = "买家名")
    private String buyerName;
    @ApiModelProperty(name = "canUpdateTime", notes = "canUpdateTime-now()<0:不可修改拉料时间,canUpdateTime-now()>0:可修改拉料时间")
    private Date canUpdateTime;
    @ApiModelProperty(name = "orderChannel", notes = "下单渠道")
    private String orderChannel;
    @ApiModelProperty(name = "proxName", notes = "代下单人")
    private String proxName;
    @ApiModelProperty(name = "proxUserId", notes = "代下单人Id")
    private Long proxUserId;
    @ApiModelProperty(name = "orgId", value = "组织ID")
    private String orgId;
    @ApiModelProperty(name="payFlag",notes="是否已经支付成功")
    private Boolean payFlag;

    @ApiModelProperty(name = "totalPresent", notes = "共赠送重量(千克)")
    private BigDecimal totalPresent;
    @ApiModelProperty(name = "lableName", notes = "客户标签")
    private String lableName;

    @ApiModelProperty(name = "payType", notes = "订单支付方式")
    private String payType;
    @ApiModelProperty(name = "cusOrderType", notes = "订单表加客户订单交易类型")
    private String cusOrderType;

    
    public String getCusOrderType() {
		return cusOrderType;
	}

	public void setCusOrderType(String cusOrderType) {
		this.cusOrderType = cusOrderType;
	}

	public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    public String getUcShopAddress() {
		return ucShopAddress;
	}

	public void setUcShopAddress(String ucShopAddress) {
		this.ucShopAddress = ucShopAddress;
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

    public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getLimitTimeType() {
        return limitTimeType;
    }

    public void setLimitTimeType(String limitTimeType) {
        this.limitTimeType = limitTimeType;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public String getFeedTransId() {
        return feedTransId;
    }

    public void setFeedTransId(String feedTransId) {
        this.feedTransId = feedTransId;
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

    public String getEbsCarNo() {
        return ebsCarNo;
    }

    public void setEbsCarNo(String ebsCarNo) {
        this.ebsCarNo = ebsCarNo;
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

	public Long getBuyerId() {
		return buyerId;
	}

	public String getBuyerMobile() {
		return buyerMobile;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public void setBuyerMobile(String buyerMobile) {
		this.buyerMobile = buyerMobile;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

    public Date getPayLimitTime() {
        return payLimitTime;
    }

    public void setPayLimitTime(Date payLimitTime) {
        this.payLimitTime = payLimitTime;
    }

    public Date getCanUpdateTime() {
        return canUpdateTime;
    }

    public void setCanUpdateTime(Date canUpdateTime) {
        this.canUpdateTime = canUpdateTime;
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

    public BigDecimal getTotalPresent() {
        return totalPresent;
    }

    public void setTotalPresent(BigDecimal totalPresent) {
        this.totalPresent = totalPresent;
    }

    public String getLableName() {
        return lableName;
    }

    public void setLableName(String lableName) {
        this.lableName = lableName;
    }

	public Boolean getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(Boolean payFlag) {
		this.payFlag = payFlag;
	}
}
