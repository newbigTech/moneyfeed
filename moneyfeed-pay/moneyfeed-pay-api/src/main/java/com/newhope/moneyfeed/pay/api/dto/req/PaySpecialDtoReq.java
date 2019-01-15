package com.newhope.moneyfeed.pay.api.dto.req;

import java.io.Serializable;

public class PaySpecialDtoReq implements Serializable {

	private static final long serialVersionUID = 4087999220630904817L;

	private String type;
	private String orderNo;
	private String payNo;
	private String code;
	private String payType;
	public String getType() {
		return type;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getPayNo() {
		return payNo;
	}

	public String getCode() {
		return code;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

}
