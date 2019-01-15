package com.newhope.moneyfeed.order.api.dto.request.user;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户客户对象")
public class CustomerUserDtoReq implements Serializable {

	private static final long serialVersionUID = 3353193402169596270L;

	@ApiModelProperty(name = "buyerId", notes = "买家用户id")
	private Long buyerId;

	@ApiModelProperty(name = "buyerMobile", notes = "买家用户电话")
	private String buyerMobile;

	@ApiModelProperty(name = "buyerName", notes = "买家用户名称")
	private String buyerName;

	@ApiModelProperty(name = "customerId", notes = "买家客户id")
	private Long customerId;

	@ApiModelProperty(name = "customerMobile", notes = "买家客户电话")
	private String customerMobile;

	@ApiModelProperty(name = "customerName", notes = "买家客户名称")
	private String customerName;

	@ApiModelProperty(name = "customerEbsNo", notes = "买家客户ebs编号")
	private String customerNum;

	@ApiModelProperty(name = "customerEbsType", notes = "买家客户ebs类型")
	private String customerEbsType;

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public String getCustomerEbsType() {
		return customerEbsType;
	}

	public void setCustomerEbsType(String customerEbsType) {
		this.customerEbsType = customerEbsType;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerMobile() {
		return buyerMobile;
	}

	public void setBuyerMobile(String buyerMobile) {
		this.buyerMobile = buyerMobile;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
