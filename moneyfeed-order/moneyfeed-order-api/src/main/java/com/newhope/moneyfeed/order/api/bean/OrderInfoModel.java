package com.newhope.moneyfeed.order.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

/**
 *   订单信息表
 */
public class OrderInfoModel extends BaseModel {
    /**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/  
	private static final long serialVersionUID = 1131280395858703761L;

	/** 订单号 */
    private String orderNo;

    /** 订单展示号 */
    private String shortNo;

    /** 商户名 */
    private String ucShopName;

    /** 商户电话 */
    private String ucShopMobile;

    /** 商户店铺ID */
    private Long ucShopId;

    /** 商户地址 */
    private String ucShopAddress;

    /** 商户类型 */
    private String ucShopType;

    /** 银行支付金额 */
    private BigDecimal bankPayAmount;

    /** 买家用户ID */
    private Long buyerId;

    /** 买家电话 */
    private String buyerMobile;

    /** 买家名 */
    private String buyerName;

    /** 客户ID */
    private Long customerId;

    /** 客户电话 */
    private String customerMobile;

    /** 客户名 */
    private String customerName;

    /** 计划拉料时间 */
    private Date planTransportTime;

    /** 旧计划拉料时间 */
    private Date oldPlanTransportTime;

    /** 订单状态(创建中，待支付，付款中，已支付，EBS同步失败，待拉料，已进厂，已完成，已退款，客户撤销，商户撤销，银行支付失败,ebs支付失败) */
    private String status;

    /** 支付时间 */
    private Date payTime;

    /** 出厂时间 */
    private Date dealTime;

    /** 进厂时间 */
    private Date transportTime;

    /** 开票时间 */
    private Date checkinTime;

    /** 完成时间 */
    private Date endTime;

    /** 关闭时间 */
    private Date closeTime;

    /** EBS支付金额 */
    private BigDecimal ebsPayAmount;

    /** 拉料总重(千克) */
    private BigDecimal totalFeedWeight;

    /** 出厂总金额 */
    private BigDecimal totalOrginalAmount;

    /** 实际支付总金额 */
    private BigDecimal totalPayAmount;

    /** 折扣总金额 */
    private BigDecimal totalDiscountAmount;

    /** EBS订单号 */
    private String ebsOrderNo;

    /** EBS退款单号 */
    private String ebsRefundOrderNo;

    /** 客户编码 */
    private String customerNum;

    /** 备注 */
    private String remark;

    /**  限制时间内完成支付 */
    private Date payLimitTime;

    /** 可编辑时间 */
    private Date canModifyTime;

    /** 订单支付限制时间类型 */
    private String limitTimeType;

    /** 订单表加客户订单交易类型 */
    private String cusOrderType;

    /** 公司简码 */
    private String companyShortCode;

    /** 支付备注 */
    private String payRemark;

    /** 机构id */
    private String orgId;

    /** 通知手机号码 */
    private String notifyMobile;

    /** 支付类型 */
    private String payType;

    /** 下单渠道 */
    private String orderChannel;

    /** 代下单人姓名 */
    private String proxName;

    /** 代下单人ID */
    private Long proxUserId;

    /** 是否已经支付成功 */
    private Boolean payFlag;

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

    public String getUcShopName() {
        return ucShopName;
    }

    public void setUcShopName(String ucShopName) {
        this.ucShopName = ucShopName;
    }

    public String getUcShopMobile() {
        return ucShopMobile;
    }

    public void setUcShopMobile(String ucShopMobile) {
        this.ucShopMobile = ucShopMobile;
    }

    public Long getUcShopId() {
        return ucShopId;
    }

    public void setUcShopId(Long ucShopId) {
        this.ucShopId = ucShopId;
    }

    public String getUcShopAddress() {
        return ucShopAddress;
    }

    public void setUcShopAddress(String ucShopAddress) {
        this.ucShopAddress = ucShopAddress;
    }

    public String getUcShopType() {
        return ucShopType;
    }

    public void setUcShopType(String ucShopType) {
        this.ucShopType = ucShopType;
    }

    public BigDecimal getBankPayAmount() {
        return bankPayAmount;
    }

    public void setBankPayAmount(BigDecimal bankPayAmount) {
        this.bankPayAmount = bankPayAmount;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerMobile() {
        return buyerMobile;
    }

    public void setBuyerMobile(String buyerMobile) {
        this.buyerMobile = buyerMobile;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getPlanTransportTime() {
        return planTransportTime;
    }

    public void setPlanTransportTime(Date planTransportTime) {
        this.planTransportTime = planTransportTime;
    }

    public Date getOldPlanTransportTime() {
        return oldPlanTransportTime;
    }

    public void setOldPlanTransportTime(Date oldPlanTransportTime) {
        this.oldPlanTransportTime = oldPlanTransportTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
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

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public BigDecimal getEbsPayAmount() {
        return ebsPayAmount;
    }

    public void setEbsPayAmount(BigDecimal ebsPayAmount) {
        this.ebsPayAmount = ebsPayAmount;
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

    public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }

    public String getEbsRefundOrderNo() {
        return ebsRefundOrderNo;
    }

    public void setEbsRefundOrderNo(String ebsRefundOrderNo) {
        this.ebsRefundOrderNo = ebsRefundOrderNo;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getPayLimitTime() {
        return payLimitTime;
    }

    public void setPayLimitTime(Date payLimitTime) {
        this.payLimitTime = payLimitTime;
    }

    public Date getCanModifyTime() {
        return canModifyTime;
    }

    public void setCanModifyTime(Date canModifyTime) {
        this.canModifyTime = canModifyTime;
    }

    public String getLimitTimeType() {
        return limitTimeType;
    }

    public void setLimitTimeType(String limitTimeType) {
        this.limitTimeType = limitTimeType;
    }

    public String getCusOrderType() {
        return cusOrderType;
    }

    public void setCusOrderType(String cusOrderType) {
        this.cusOrderType = cusOrderType;
    }

    public String getCompanyShortCode() {
        return companyShortCode;
    }

    public void setCompanyShortCode(String companyShortCode) {
        this.companyShortCode = companyShortCode;
    }

    public String getPayRemark() {
        return payRemark;
    }

    public void setPayRemark(String payRemark) {
        this.payRemark = payRemark;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getNotifyMobile() {
        return notifyMobile;
    }

    public void setNotifyMobile(String notifyMobile) {
        this.notifyMobile = notifyMobile;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    public Boolean getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(Boolean payFlag) {
        this.payFlag = payFlag;
    }
}