package com.newhope.openapi.api.vo.request.pay;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**  
* @ClassName: PayOrderCustomerDtoReq  
* @Description: 用户提交支付信息
* @author luoxl
* @date 2018年11月23日 上午10:52:36  
*    
*/
public class PayOrderCustomerReq implements Serializable {

	private static final long serialVersionUID = 359530462253058075L;

	@ApiModelProperty(name = "orderNo", notes = "订单号")
	private String orderNo;
	@ApiModelProperty(name = "amount", notes = "支付金额")
	private BigDecimal amount;
	@ApiModelProperty(name = "payType", notes = "支付类型(账户充值 ,订单充值,银行卡支付,余额支付)")
	private String payType;
	
	public String getOrderNo() {
		return orderNo;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}

}
