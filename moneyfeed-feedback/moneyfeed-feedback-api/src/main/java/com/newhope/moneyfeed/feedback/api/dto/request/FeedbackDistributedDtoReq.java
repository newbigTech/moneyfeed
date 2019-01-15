package com.newhope.moneyfeed.feedback.api.dto.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class FeedbackDistributedDtoReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name = "id",notes = "留言记录id")
	private Long id;
	
	@ApiModelProperty(name = "shopId",notes = "分配商户Id")
	private Long shopId;
	
	@ApiModelProperty(name = "distributedDesc",notes = "分配留言")
	private String distributedDesc;
	
	@ApiModelProperty(name = "feedbackStatus",notes = "分配：distributed, 平台完结：platform_closed")
	private String feedbackStatus;
	
	@ApiModelProperty(name = "sendWechatMsg",notes = "是否需要推送微信消息(是：true , 否：false , 默认为false)，分配商户传false")
	private Boolean sendWechatMsg = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getDistributedDesc() {
		return distributedDesc;
	}

	public void setDistributedDesc(String distributedDesc) {
		this.distributedDesc = distributedDesc;
	}

	public String getFeedbackStatus() {
		return feedbackStatus;
	}

	public void setFeedbackStatus(String feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

	public Boolean getSendWechatMsg() {
		return sendWechatMsg;
	}

	public void setSendWechatMsg(Boolean sendWechatMsg) {
		this.sendWechatMsg = sendWechatMsg;
	}
	
}
