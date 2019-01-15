package com.newhope.moneyfeed.pay.api.dto.req.integration;

import io.swagger.annotations.ApiModelProperty;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 通知中台的支付model
 *
 * @author bena.peng
 * @date 2018/12/18 10:05
 */


public class NotifyPaymentDtoReq implements Serializable {

    private static final long serialVersionUID = -50462415142164134L;
    
    @ApiModelProperty(name = "ebsOrderNo", value = "EBS订单编码")
    private String ebsOrderNo;

    @ApiModelProperty(name = "cusNo", value = "客户编号")
    private String cusNo;

    @ApiModelProperty(name = "totalPayAmount", value = "支付总金额")
    private BigDecimal totalPayAmount;

    @ApiModelProperty(name = "moneyfeedPayNo", value = "商城支付流水号")
    private String moneyfeedPayNo;

    @ApiModelProperty(name = "accNo", value = "银行流水号（银行卡支付）")
    private String accNo;

    @ApiModelProperty(name = "currency", value = "币种")
    private String currency;

    @ApiModelProperty(name = "payDateString", value = "支付日期字符串格式")
    private String payDateString;

    @ApiModelProperty(name = "payDate", value = "支付日期")
    private Date payDate;
    
    @ApiModelProperty(name = "orgId", value = "组织ID")
    private String orgId;
    
    @NotBlank(message = "EBS最终账户不能为空")
    @ApiModelProperty(name = "ebsRealAccount", value = "EBS最终账户")
    private String ebsRealAccount;

	public String getEbsOrderNo() {
		return ebsOrderNo;
	}

	public String getCusNo() {
		return cusNo;
	}

	public BigDecimal getTotalPayAmount() {
		return totalPayAmount;
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

	public String getPayDateString() {
		return payDateString;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setEbsOrderNo(String ebsOrderNo) {
		this.ebsOrderNo = ebsOrderNo;
	}

	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}

	public void setTotalPayAmount(BigDecimal totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
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

	public void setPayDateString(String payDateString) {
		this.payDateString = payDateString;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
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
