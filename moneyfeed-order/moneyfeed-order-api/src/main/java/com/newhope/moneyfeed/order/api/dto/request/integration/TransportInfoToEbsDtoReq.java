package com.newhope.moneyfeed.order.api.dto.request.integration;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**  
* @ClassName: TransportInfoToEbsDtoReq  
* @Description: integration调用商城更新 拉料信息
* @author luoxl
* @date 2018年11月21日 下午3:05:14  
*    
*/
public class TransportInfoToEbsDtoReq implements Serializable {
  
	private static final long serialVersionUID = 2957600855188797843L;

	@ApiModelProperty(name = "planTransportTime", value = "计划拉料日期")
	private Date planTransportTime;
	
	@ApiModelProperty(name = "carNo", notes = "商城填写的车牌号")
	private String carNo;
	
	@ApiModelProperty(name = "orderFeedTransportId", notes = "拉料信息ID")
	private Long orderFeedTransportId;
	
	@ApiModelProperty(name = "orderId", notes = "订单ID")
	private Long orderId;
	
	@ApiModelProperty(name = "whetherLock", value = "是否锁库")
    private Boolean whetherLock;

	public Date getPlanTransportTime() {
		return planTransportTime;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setPlanTransportTime(Date planTransportTime) {
		this.planTransportTime = planTransportTime;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public Long getOrderFeedTransportId() {
		return orderFeedTransportId;
	}

	public void setOrderFeedTransportId(Long orderFeedTransportId) {
		this.orderFeedTransportId = orderFeedTransportId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Boolean getWhetherLock() {
		return whetherLock;
	}

	public void setWhetherLock(Boolean whetherLock) {
		this.whetherLock = whetherLock;
	}


}
