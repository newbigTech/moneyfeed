package com.newhope.openapi.api.vo.response.order.pay;

import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.user.api.enums.client.CreditBalanceShowRuleEnums;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 用户支付账户信息
 * @author hp
 *
 */
public class PayAccountInfoResult extends Result {
    
	private static final long serialVersionUID = 4582373818744686884L;
	
	@ApiModelProperty(name="availableBalance", notes="可用额度")
	private BigDecimal availableBalance;

	@ApiModelProperty(name="whetherInOwe", notes="是否临欠(Y/N)")
	private String whetherInOwe;
	
	@ApiModelProperty(name="totalPayAmount", notes="订单实付金额")
	private BigDecimal totalPayAmount;
	
	@ApiModelProperty(name="rechargeAmount", notes="需要充值金额")
	private BigDecimal rechargeAmount;
	
	@ApiModelProperty(name="showRule", notes="余额显示规则")
	private CreditBalanceShowRuleEnums showRule;
	
	@ApiModelProperty(name="planTransportTime", notes="计划拉料时间")
	private Date planTransportTime;
	
	@ApiModelProperty(name="shopAddress", notes="商家地址")
	private String shopAddress;
	
	@ApiModelProperty(name="allowOfflineBusiness", notes="是否允许线下支付")
	private Boolean allowOfflineBusiness;
	

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getWhetherInOwe() {
		return whetherInOwe;
	}

	public void setWhetherInOwe(String whetherInOwe) {
		this.whetherInOwe = whetherInOwe;
	}

	public BigDecimal getTotalPayAmount() {
		return totalPayAmount;
	}

	public void setTotalPayAmount(BigDecimal totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}

	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public CreditBalanceShowRuleEnums getShowRule() {
		return showRule;
	}

	public void setShowRule(CreditBalanceShowRuleEnums showRule) {
		this.showRule = showRule;
	}

	public Date getPlanTransportTime() {
		return planTransportTime;
	}

	public void setPlanTransportTime(Date planTransportTime) {
		this.planTransportTime = planTransportTime;
	}

	public Boolean getAllowOfflineBusiness() {
		return allowOfflineBusiness;
	}

	public void setAllowOfflineBusiness(Boolean allowOfflineBusiness) {
		this.allowOfflineBusiness = allowOfflineBusiness;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	
}
