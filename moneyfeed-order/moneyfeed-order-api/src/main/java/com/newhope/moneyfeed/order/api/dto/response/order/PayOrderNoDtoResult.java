package com.newhope.moneyfeed.order.api.dto.response.order;

import java.io.Serializable;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;

public class PayOrderNoDtoResult extends PageDtoResult implements Serializable{
 
	private static final long serialVersionUID = -7662695373914339589L;
	
	private String payOrderNo;

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}
}
