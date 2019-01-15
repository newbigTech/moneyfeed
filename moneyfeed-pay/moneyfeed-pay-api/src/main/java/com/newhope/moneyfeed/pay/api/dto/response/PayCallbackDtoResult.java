package com.newhope.moneyfeed.pay.api.dto.response;

import java.io.Serializable;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import io.swagger.annotations.ApiModelProperty;


/**  
* @ClassName: PayCallbackDtoResult  
* @Description: 处理回调后返回的数据
* @author luoxl
* @date 2018年12月25日 上午8:51:16  
*    
*/
public class PayCallbackDtoResult extends DtoResult implements Serializable {
 
	private static final long serialVersionUID = -7445868570662773710L;

	@ApiModelProperty(name = "payType", notes = "支付方式（余额支付/银行卡支付/订单充值/账户充值")
	private String payType;
	@ApiModelProperty(name = "orderId", notes = "商城订单ID")
    private Long orderId;
	@ApiModelProperty(name = "orderNo", notes = "商城订单No")
    private String orderNo;

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Long getOrderId() {
		return orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
