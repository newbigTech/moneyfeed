package com.newhope.openapi.api.vo.response.user;

import java.io.Serializable;
import java.math.BigDecimal;

import com.newhope.moneyfeed.api.vo.response.PageResult;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class FinancialPaymentFlowResult extends PageResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7324573094959750220L;
	@ApiModelProperty(value = "哪一天：例如13号")
	private Integer day;
	@ApiModelProperty(value = "周几")
	private Integer dayOfTheWeek;
	@ApiModelProperty(value = "支付方式：BANK:银行卡、CREDIT:余额")
	private String type;
	@ApiModelProperty(value = "支付流水号")
	private String number;
	@ApiModelProperty(value = "商品订单号")
	private String orderCode;
	@ApiModelProperty(value = "支付订单号")
	private String payOrderCode;
	@ApiModelProperty(value = "支付金额")
	private BigDecimal payment;
	@ApiModelProperty(value = "创建时间")
	private String created;
	@ApiModelProperty(value = "状态")
	private String status;
	@ApiModelProperty(value = "交易卡号")
	private String card;
	@ApiModelProperty(value = "付款银行")
	private String issuingBank;
	
	private String groupbyDate;
	
	public String getGroupbyDate() {
		return groupbyDate;
	}

	public void setGroupbyDate(String groupbyDate) {
		this.groupbyDate = groupbyDate;
	}

	public String getPayOrderCode() {
		return payOrderCode;
	}

	public void setPayOrderCode(String payOrderCode) {
		this.payOrderCode = payOrderCode;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public void setDayOfTheWeek(Integer dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getIssuingBank() {
		return issuingBank;
	}

	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}
}
