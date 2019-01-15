package com.newhope.moneyfeed.pay.api.dto.req.bank;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查询银行支付订单
 * @author heping  
 * @date 2019年1月11日
 */
public class BankOrderQueryDtoReq implements Serializable {
	
	private static final long serialVersionUID = -7146303724368302141L;
	
	@ApiModelProperty(name = "orderNo", notes = "订单号")
	private String orderNo;
	@ApiModelProperty(name = "tradeDate", notes = "交易日期,例如:20190103")
	private Date tradeDate;
	@ApiModelProperty(name = "amt", notes = "支付金额")
	private BigDecimal amt;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	
}
