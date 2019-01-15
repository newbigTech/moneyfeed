package com.newhope.moneyfeed.user.api.dto.request.client;

import java.util.List;

import com.newhope.moneyfeed.api.dto.request.PageDtoReq;

public class CustomerAccountStatementQueryDtoReq extends PageDtoReq {

	private static final long serialVersionUID = -8338085395904928314L;

	//客户id
	private Long customerId;

	//开始日期
	private String beginDate;
	
	//结束日期
    private String endDate;

    //支付类型
    private String bizType;
    
    //单据号
	private String orderNo;
	
	//支付单号
	private String payOrderNo;
	
	//客户id集合
	private List<Long> customerIds;
	
	//部门id集合
	private List<Long> shopIds;
	
	public List<Long> getShopIds() {
		return shopIds;
	}

	public void setShopIds(List<Long> shopIds) {
		this.shopIds = shopIds;
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

	public List<Long> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<Long> customerIds) {
		this.customerIds = customerIds;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	

}
