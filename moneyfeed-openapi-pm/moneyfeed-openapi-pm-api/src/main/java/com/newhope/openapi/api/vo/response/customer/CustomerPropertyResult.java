package com.newhope.openapi.api.vo.response.customer;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class CustomerPropertyResult extends Result {

	private static final long serialVersionUID = -7463899980640105975L;
	
	@ApiModelProperty(notes = "关联主键Id")
	private Long mappingId;
	
	@ApiModelProperty(notes = "允许线上交易")
    private String  allowOnlineBusiness;
    
	@ApiModelProperty(notes = "可用额度显示规则")
    private String  creditBalanceShowRule;
	
	@ApiModelProperty(notes = "账单显示设置规则")
    private String  billShowRule; 
	
	@ApiModelProperty(notes = "线上流水显示设置规则")
	private String  daybookOnlineShowRule; 
	
	@ApiModelProperty(notes = "月度对账单显示设置规则")
	private String  monthBillShowRule; 
	
	@ApiModelProperty(notes = "订单金额计算规则")
	private String  ordeCalculateRule;

	public String getAllowOnlineBusiness() {
		return allowOnlineBusiness;
	}

	public String getCreditBalanceShowRule() {
		return creditBalanceShowRule;
	}

	public String getBillShowRule() {
		return billShowRule;
	}

	public String getDaybookOnlineShowRule() {
		return daybookOnlineShowRule;
	}

	public String getMonthBillShowRule() {
		return monthBillShowRule;
	}

	public String getOrdeCalculateRule() {
		return ordeCalculateRule;
	}

	public void setAllowOnlineBusiness(String allowOnlineBusiness) {
		this.allowOnlineBusiness = allowOnlineBusiness;
	}

	public void setCreditBalanceShowRule(String creditBalanceShowRule) {
		this.creditBalanceShowRule = creditBalanceShowRule;
	}

	public void setBillShowRule(String billShowRule) {
		this.billShowRule = billShowRule;
	}

	public void setDaybookOnlineShowRule(String daybookOnlineShowRule) {
		this.daybookOnlineShowRule = daybookOnlineShowRule;
	}

	public void setMonthBillShowRule(String monthBillShowRule) {
		this.monthBillShowRule = monthBillShowRule;
	}

	public void setOrdeCalculateRule(String ordeCalculateRule) {
		this.ordeCalculateRule = ordeCalculateRule;
	}

	public Long getMappingId() {
		return mappingId;
	}

	public void setMappingId(Long mappingId) {
		this.mappingId = mappingId;
	} 
	

}
