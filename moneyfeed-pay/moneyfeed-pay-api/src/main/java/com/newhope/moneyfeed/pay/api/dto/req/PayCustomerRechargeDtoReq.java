package com.newhope.moneyfeed.pay.api.dto.req;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**  
* @ClassName: PayCustomerRechargeDtoReq  
* @Description: 客户充值
* @author luoxl
* @date 2018年11月26日 上午10:47:44  
*    
*/
public class PayCustomerRechargeDtoReq implements Serializable {
 
	private static final long serialVersionUID = 8934196317652287946L;
	
	@ApiModelProperty(name = "orderId", notes = "订单主键id")
	private Long orderId;
	@ApiModelProperty(name = "orderNo", notes = "订单号")
	private String orderNo;
	@ApiModelProperty(name = "userId", notes = "用户id")
	private Long userId;
	@ApiModelProperty(name = "shopId", notes = "店铺id")
	private Long shopId;
	@ApiModelProperty(name = "amount", notes = "支付金额")
	private BigDecimal amount;
	@ApiModelProperty(name = "remark", notes = "备注")
	private String remark;
	@ApiModelProperty(name = "customerBankAccount", notes = "客户银行账户")
	private String customerBankAccount;
	@ApiModelProperty(name = "customerBankCardNo", notes = "客户银行卡号")
	private String customerBankCardNo;
	@ApiModelProperty(name = "shopBankAccount", notes = "商户银行账号")
	private String shopBankAccount;
	@ApiModelProperty(name = "shopBankCardNo", notes = "商户银行卡号")
	private String shopBankCardNo;
	
	public Long getOrderId() {
		return orderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public Long getUserId() {
		return userId;
	}
	public Long getShopId() {
		return shopId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCustomerBankAccount() {
		return customerBankAccount;
	}
	public String getCustomerBankCardNo() {
		return customerBankCardNo;
	}
	public String getShopBankAccount() {
		return shopBankAccount;
	}
	public String getShopBankCardNo() {
		return shopBankCardNo;
	}
	public void setCustomerBankAccount(String customerBankAccount) {
		this.customerBankAccount = customerBankAccount;
	}
	public void setCustomerBankCardNo(String customerBankCardNo) {
		this.customerBankCardNo = customerBankCardNo;
	}
	public void setShopBankAccount(String shopBankAccount) {
		this.shopBankAccount = shopBankAccount;
	}
	public void setShopBankCardNo(String shopBankCardNo) {
		this.shopBankCardNo = shopBankCardNo;
	}

}
