package com.newhope.moneyfeed.integration.api.dto.request.ebs.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liuyc on 2018/12/18
 */
public class MoneyfeedToEbsOrderRechargeDtoReq implements Serializable {

    private static final long serialVersionUID = 2395847690950458525L;

    // EBS订单编码
    @ApiModelProperty(name = "ebsOrderNo", value = "EBS订单编码")
    private String ebsOrderNo;
    
    //支付方式（余额支付/银行卡支付/订单充值/账户充值）
    @NotBlank(message="支付方式（订单充值/账户充值）为空")
    @ApiModelProperty(name = "payType", value = "支付方式（订单充值/账户充值）")
    private String payType;

    // 客户编号
    @NotBlank(message="客户编号为空")
    @ApiModelProperty(name = "cusNo", value = "客户编号")
    private String cusNo;

    // 充值金额
//    @NotBlank(message="充值金额为空")
    @ApiModelProperty(name = "rechargeAmount", value = "充值金额")
    private BigDecimal rechargeAmount;

    // 商城充值流水号
    @NotBlank(message="商城充值流水号为空")
    @ApiModelProperty(name = "moneyfeedPayNo", value = "商城充值流水号")
    private String moneyfeedPayNo;

    // 银行流水号（银行卡支付）
    @NotBlank(message="银行流水号（银行卡支付）为空")
    @ApiModelProperty(name = "accNo", value = "银行流水号（银行卡支付）")
    private String accNo;

    // 币种CNY
    @NotBlank(message="币种CNY为空")
    @ApiModelProperty(name = "currency", value = "币种CNY")
    private String currency;

    // 是否临欠客户()
    @ApiModelProperty(name = "tempOwe", value = "是否临欠客户()")
    private Boolean tempOwe;

    // 充值日期
//    @NotBlank(message="充值日期为空")
    @ApiModelProperty(name = "rechargeDate", value = "充值日期")
    private Date rechargeDate;
    
    // EBS需要ou code这个字段
    @NotBlank(message="组织ID为空")
    @ApiModelProperty(name = "orgId", value = "组织ID")
    private String orgId;
    
    @NotBlank(message = "EBS最终账户不能为空")
    @ApiModelProperty(name = "ebsRealAccount", value = "EBS最终账户")
    private String ebsRealAccount;

	public String getEbsOrderNo() {
		return ebsOrderNo;
	}

	public void setEbsOrderNo(String ebsOrderNo) {
		this.ebsOrderNo = ebsOrderNo;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCusNo() {
		return cusNo;
	}

	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}

	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public String getMoneyfeedPayNo() {
		return moneyfeedPayNo;
	}

	public void setMoneyfeedPayNo(String moneyfeedPayNo) {
		this.moneyfeedPayNo = moneyfeedPayNo;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Boolean getTempOwe() {
		return tempOwe;
	}

	public void setTempOwe(Boolean tempOwe) {
		this.tempOwe = tempOwe;
	}

	public Date getRechargeDate() {
		return rechargeDate;
	}

	public void setRechargeDate(Date rechargeDate) {
		this.rechargeDate = rechargeDate;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getEbsRealAccount() {
		return ebsRealAccount;
	}

	public void setEbsRealAccount(String ebsRealAccount) {
		this.ebsRealAccount = ebsRealAccount;
	}
	
}
