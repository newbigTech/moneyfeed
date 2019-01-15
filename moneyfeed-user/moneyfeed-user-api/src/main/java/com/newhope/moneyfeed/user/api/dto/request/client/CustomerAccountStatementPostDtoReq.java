package com.newhope.moneyfeed.user.api.dto.request.client;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomerAccountStatementPostDtoReq implements Serializable {
	
	private static final long serialVersionUID = -3865676642857594591L;

	/** 用户id */
    private Long clientUserId;
    
    /** 商户id */
    private Long shopId;

    /** 流水号(支付流水号，退款流水号) */
    private String serialNumber;

    /** 金额 */
    private BigDecimal amount;

    /** 业务类型(支付、退款、充值、提现、还款) */
    private String bizType;

    /** 订单号 */
    private String orderNo;

    /** 支付订单号 */
    private String payOrderNo;

	public Long getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(Long clientUserId) {
		this.clientUserId = clientUserId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
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
    
}
