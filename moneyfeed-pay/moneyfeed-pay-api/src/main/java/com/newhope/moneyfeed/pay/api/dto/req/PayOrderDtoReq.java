package com.newhope.moneyfeed.pay.api.dto.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**  
* @ClassName: PayOrderDtoReq  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author 查询支付订单基本列表
* @date 2019年1月3日 下午3:37:46  
*    
*/
public class PayOrderDtoReq implements Serializable{
 
	private static final long serialVersionUID = -83251764889790038L;
	
    @ApiModelProperty(name = "payOrderNo", notes = "支付订单号")
    private String payOrderNo;

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

   
}
