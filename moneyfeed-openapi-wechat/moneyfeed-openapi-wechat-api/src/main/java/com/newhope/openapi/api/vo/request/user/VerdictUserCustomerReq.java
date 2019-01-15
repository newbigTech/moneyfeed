package com.newhope.openapi.api.vo.request.user;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class VerdictUserCustomerReq implements Serializable {

	private static final long serialVersionUID = -2563021507824146739L;
	
	@ApiModelProperty(name="cardNo", notes="税号/身份证号", required=true)
	private String cardNo;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
}
