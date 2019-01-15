package com.newhope.openapi.api.vo.request.order.pay;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * ebs支付请求
 * @author hp
 *
 */
public class OrderPayByEBSReq implements Serializable {
	
	private static final long serialVersionUID = 3642132367831784837L;
	
	@ApiModelProperty(name = "orderId", notes = "订单主键id",required = true)
	@NotNull
    private Long orderId;
	
    @NotEmpty
    @ApiModelProperty(name = "password", notes = "支付密码",required = true)
    private String password;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
