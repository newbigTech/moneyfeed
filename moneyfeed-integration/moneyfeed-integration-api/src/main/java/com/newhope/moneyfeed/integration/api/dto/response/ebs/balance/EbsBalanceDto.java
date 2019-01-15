package com.newhope.moneyfeed.integration.api.dto.response.ebs.balance;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "HERDER")
public class EbsBalanceDto implements Serializable{

	private static final long serialVersionUID = 7743571419966458464L;
	
	@ApiModelProperty(name="orgCode", notes="公司编码", required=true)
	private String orgCode;//公司ID
	
	@ApiModelProperty(name="customerCode", notes="客户编码", required=true)
	private  String customerCode;//客户编码	
	
	@ApiModelProperty(name="availableBalance", notes="可用额度", required=true)
	private BigDecimal availableBalance;//可用额度
	
	@ApiModelProperty(name="availableCredit", notes="临欠额度", required=true)
	private BigDecimal availableCredit;//临欠额度
	
	@ApiModelProperty(name="deposit", notes="保证金", required=true)
	private BigDecimal deposit;//保证金
	
	@ApiModelProperty(name="balance", notes="余额", required=true)
	private BigDecimal balance;//余额
	
	@ApiModelProperty(name="whetherInOwe", notes="是否临欠(Y/N)", required=true)
	private String whetherInOwe;//是否临欠(Y/N)

	@XmlElement(name = "ORG_ID")
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@XmlElement(name = "CUSTOMER_CODE")
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	@XmlElement(name = "AVAILABLE_BALANCE")
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	@XmlElement(name = "AVAILABLE_CREDIT")
	public BigDecimal getAvailableCredit() {
		return availableCredit;
	}
	public void setAvailableCredit(BigDecimal availableCredit) {
		this.availableCredit = availableCredit;
	}

	@XmlElement(name = "DEPOSIT")
	public BigDecimal getDeposit() {
		return deposit;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	@XmlElement(name = "BALANCE")
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@XmlElement(name = "WHETHER_IN_OWE")
	public String getWhetherInOwe() {
		return whetherInOwe;
	}
	public void setWhetherInOwe(String whetherInOwe) {
		this.whetherInOwe = whetherInOwe;
	}	
	
}
