package com.newhope.moneyfeed.user.api.dto.request.client;

import java.io.Serializable;

public class HandleEbsCustomerVisitDtoReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5340944195006986386L;

	//ebs客户手机号
	private String mobile;
	
	//ebs客户编码
	private String customerNum;

	//访问状态
	private String type;
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	
}
