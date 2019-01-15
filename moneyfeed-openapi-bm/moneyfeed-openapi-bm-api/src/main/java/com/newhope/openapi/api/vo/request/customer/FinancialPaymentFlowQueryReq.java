package com.newhope.openapi.api.vo.request.customer;

import com.newhope.moneyfeed.api.vo.request.PageReq;

import io.swagger.annotations.ApiModelProperty;

public class FinancialPaymentFlowQueryReq extends PageReq{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -478734729438290129L;

	@ApiModelProperty(name="customerName", notes="客户名称", required=false)
	private String customerName;
	
	@ApiModelProperty(name="startDate", notes="开始日期", required=false)
	private String startDate;
	
	@ApiModelProperty(name="endDate", notes="结束日期", required=false)
	private String endDate;
	
	@ApiModelProperty(name="orderNo", notes="单据号", required=false)
	private String orderNo;
	
	@ApiModelProperty(name="payOrderNo", notes="支付单号", required=false)
	private String payOrderNo;
	
	@ApiModelProperty(name="bizType", notes="支付方式", required=false)
	private String bizType;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	
}
