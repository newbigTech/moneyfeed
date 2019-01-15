package com.newhope.moneyfeed.order.api.dto.request.integration;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**  
* @ClassName: CancelOrderForEbsDtoReq  
* @Description: 商城调用integration取消订单
* @author luoxl
* @date 2018年11月20日 下午6:59:23  
*    
*/
public class CancelOrderToEbsDtoReq implements Serializable {

	private static final long serialVersionUID = -304521166928000172L;

	@ApiModelProperty(name = "orderId", notes = "订单id")
	private Long orderId;
	
	@ApiModelProperty(name = "ebsOrderNo", notes = "EBS订单编码")
	private String ebsOrderNo;

	@ApiModelProperty(name = "orgId", value = "组织id）")
    private String orgId;
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public String getEbsOrderNo() {
		return ebsOrderNo;
	}

	public void setEbsOrderNo(String ebsOrderNo) {
		this.ebsOrderNo = ebsOrderNo;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}



}
