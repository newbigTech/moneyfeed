package com.newhope.moneyfeed.pay.api.dto.req;

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
public class PayOrderCustomerDtoReq implements Serializable {

	private static final long serialVersionUID = 359530462253058075L;

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

}
