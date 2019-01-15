package com.newhope.moneyfeed.pay.api.dto.req.integration;

import io.swagger.annotations.ApiModelProperty;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**  
* @ClassName: NotifyRechargeDtoReq  
* @Description: 充值通知中台数据
* @author luoxl
* @date 2018年12月19日 下午4:57:41  
*    
*/
public class NotifyRechargeDtoReq implements Serializable {

	private static final long serialVersionUID = 89253635034424052L;

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
    @NotBlank(message="充值金额为空")
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

    // 充值日期
    @NotBlank(message="充值日期为空")
    @ApiModelProperty(name = "rechargeDate", value = "充值日期")
    private Date rechargeDate;
    
    @ApiModelProperty(name = "orgId", value = "组织ID")
    private String orgId;
    
    @NotBlank(message = "EBS最终账户不能为空")
    @ApiModelProperty(name = "ebsRealAccount", value = "EBS最终账户")
    private String ebsRealAccount;

	public String getEbsOrderNo() {
		return ebsOrderNo;
	}

	public String getPayType() {
		return payType;
	}

	public String getCusNo() {
		return cusNo;
	}

	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}

	public String getMoneyfeedPayNo() {
		return moneyfeedPayNo;
	}

	public String getAccNo() {
		return accNo;
	}

	public String getCurrency() {
		return currency;
	}

	public Date getRechargeDate() {
		return rechargeDate;
	}

	public void setEbsOrderNo(String ebsOrderNo) {
		this.ebsOrderNo = ebsOrderNo;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}

	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public void setMoneyfeedPayNo(String moneyfeedPayNo) {
		this.moneyfeedPayNo = moneyfeedPayNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setRechargeDate(Date rechargeDate) {
		this.rechargeDate = rechargeDate;
	}

	public String getEbsRealAccount() {
		return ebsRealAccount;
	}

	public void setEbsRealAccount(String ebsRealAccount) {
		this.ebsRealAccount = ebsRealAccount;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
    


}
