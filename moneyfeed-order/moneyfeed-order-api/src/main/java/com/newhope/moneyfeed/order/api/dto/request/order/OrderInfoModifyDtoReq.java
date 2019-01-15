package com.newhope.moneyfeed.order.api.dto.request.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Create by yyq on 2018/11/19
 */
public class OrderInfoModifyDtoReq implements Serializable {
	private static final long serialVersionUID = -314908296336261770L;
	@ApiModelProperty(name = "orderId", notes = "订单主键id")
	private Long orderId;
	@ApiModelProperty(name = "status", notes = "订单状态")
	private String status;
	@ApiModelProperty(name = "validStatus", notes = "验证订单状态")
	private String validStatus;
	@ApiModelProperty(name = "oldStatus", notes = "订单以前的状态：幂等用")
	private String oldStatus;
	@ApiModelProperty(name = "planTransportTime", notes = "计划拉料时间")
	private Date planTransportTime;
	@ApiModelProperty(name = "orderNo", notes = "订单编号")
	private String orderNo;
	@ApiModelProperty(name="payTime",notes="支付时间")
    private Date payTime;
	@ApiModelProperty(name="payFlag",notes="是否已经支付成功")
    private Boolean payFlag;
	@ApiModelProperty(name="payType",notes="商城订单支付类型")
    private String payType;
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}

	public Date getPlanTransportTime() {
		return planTransportTime;
	}

	public void setPlanTransportTime(Date planTransportTime) {
		this.planTransportTime = planTransportTime;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Boolean getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(Boolean payFlag) {
		this.payFlag = payFlag;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
}
