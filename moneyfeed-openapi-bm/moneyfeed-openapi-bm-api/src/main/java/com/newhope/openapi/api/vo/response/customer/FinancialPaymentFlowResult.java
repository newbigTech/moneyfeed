package com.newhope.openapi.api.vo.response.customer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class FinancialPaymentFlowResult implements Serializable {
	
	private static final long serialVersionUID = 7339802383345380525L;

	@ApiModelProperty(value = "创建日期")
    private Date createDate;
	
	@ApiModelProperty(value = "客户名称")
	private String customerName;
	
	@ApiModelProperty(value = "单据号")
	private String orderNo;
	
	@ApiModelProperty(value = "支付流水号")
	private String payOrderNo;
	
	@ApiModelProperty(value = "支付方式")
	private String bizType;
	
	@ApiModelProperty(value = "金额")
	private BigDecimal amount;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
