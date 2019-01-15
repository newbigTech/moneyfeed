package com.newhope.moneyfeed.order.api.dto.request.order.pay;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

/**
 * ebs支付请求
 * @author hp
 *
 */
public class OrderPayByEBSDtoReq implements Serializable {
	
	private static final long serialVersionUID = 4975943118371152603L;
	
	@ApiModelProperty(name = "orderId", notes = "订单主键id",required = true)
	@NotNull
    private Long orderId;
	
	@ApiModelProperty(name="deposit", notes="保证金", required=true)
	@NotNull
	private BigDecimal deposit;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
}
