package com.newhope.moneyfeed.pay.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

/**
 *   支付订单表
 */
public class PayOrderInfoModel extends BaseModel {
 
	private static final long serialVersionUID = -1341422566673353973L;

	/** 支付订单号 */
    private String payOrderNo;

    /** 商城订单ID */
    private Long orderId;

    /** 商城订单号 */
    private String orderNo;

    /** 用户ID */
    private Long userId;

    /** 客户号 */
    private String cusno;

    /** 店铺ID */
    private Long shopId;

    /** 商户ID */
    private String platformid;

    /** 商户账号 */
    private String merchno;

    /** 支付平台返回的流水号 */
    private String bankOrderNo;

    /** 商城交易日期 */
    private Date tradeTime;

    /** 订单金额 */
    private BigDecimal amount;

    /** 备注 */
    private String remark;

    /** 支付平台订单支付日期 */
    private Date bankTradeTime;

    /** 订单状态(已创建，进行中，成功，失败) */
    private String status;

    /** 支付类型(充值 ,支付) */
    private String payType;

    /** 支付订单EBS回调状态(进行中，成功，失败) */
    private String ebsStatus;

    /** 支付订单对账状态(待对账,已核对,核对有误) */
    private String checkStatus;

    /** 手续费 */
    private BigDecimal fee;

    /** 银行实际订单金额 */
    private BigDecimal bankAmount;

    /** 原因 */
    private String reason;

    /** 商户账号 */
    private String shopBankAcco;

    /** 机构id */
    private String orgId;

    /** 完成支付所选择通道代码 */
    private String channelcode;

    /** 完成支付所选择通道子商户账户号 */
    private String submerchid;

    /** 通道交易流水号 */
    private String waterno;

    /** 银行名称 */
    private String bankname;

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCusno() {
        return cusno;
    }

    public void setCusno(String cusno) {
        this.cusno = cusno;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPlatformid() {
        return platformid;
    }

    public void setPlatformid(String platformid) {
        this.platformid = platformid;
    }

    public String getMerchno() {
        return merchno;
    }

    public void setMerchno(String merchno) {
        this.merchno = merchno;
    }

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getBankTradeTime() {
        return bankTradeTime;
    }

    public void setBankTradeTime(Date bankTradeTime) {
        this.bankTradeTime = bankTradeTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getEbsStatus() {
        return ebsStatus;
    }

    public void setEbsStatus(String ebsStatus) {
        this.ebsStatus = ebsStatus;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(BigDecimal bankAmount) {
        this.bankAmount = bankAmount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getShopBankAcco() {
        return shopBankAcco;
    }

    public void setShopBankAcco(String shopBankAcco) {
        this.shopBankAcco = shopBankAcco;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getChannelcode() {
        return channelcode;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode;
    }

    public String getSubmerchid() {
        return submerchid;
    }

    public void setSubmerchid(String submerchid) {
        this.submerchid = submerchid;
    }

    public String getWaterno() {
        return waterno;
    }

    public void setWaterno(String waterno) {
        this.waterno = waterno;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
}