package com.newhope.moneyfeed.feedback.api.dto.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class FeedbackItemDtoResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id",notes = "id")
	private Long id;
	
	@ApiModelProperty(name = "feedbackNumber",notes = "留言编号")
	private String feedbackNumber;
	
	@ApiModelProperty(name = "feedbackStatus",notes = "待处理,pending, 处理完成：finished, 关闭留言：closed")
	private String feedbackStatus;
	
	@ApiModelProperty(name = "feedbackStatusName",notes = "状态名称")
	private String feedbackStatusName;
	
	@ApiModelProperty(name = "solveType",notes = "商户分类")
	private String solveType;
	
	@ApiModelProperty(name = "solveTypeName",notes = "商户分类名称")
	private String solveTypeName;
	
	@ApiModelProperty(name = "feedbackTime",notes = "反馈时间")
	private String feedbackTime;
	
	@ApiModelProperty(name = "channel",notes = "反馈渠道")
	private String channel;
	
	@ApiModelProperty(name = "channelName",notes = "反馈渠道名称")
	private String channelName;
	
	@ApiModelProperty(name = "userId",notes = "反馈人id")
	private Long userId;
	
	@ApiModelProperty(name = "userName",notes = "反馈人")
	private String userName;
	
	@ApiModelProperty(name = "mobile",notes = "反馈人手机")
	private String mobile;
	
	@ApiModelProperty(name = "customerName",notes = "所属客户")
	private String customerName;
	
	@ApiModelProperty(name = "shopId",notes = "针对商户id")
	private Long intentionShopId;
	
	@ApiModelProperty(name = "intentionShopName",notes = "针对商户名称")
	private String intentionShopName;
	
	@ApiModelProperty(name = "shopId",notes = "分配商户id")
	private Long shopId;
	
	@ApiModelProperty(name = "shopName",notes = "分配商户名称")
	private String shopName;
	
	@ApiModelProperty(name = "feedbackDesc",notes = "反馈内容")
	private String feedbackDesc;
	
	@ApiModelProperty(name = "messageLabel",notes = "留言标签内容")
	private String messageLabel;
	
	@ApiModelProperty(name = "messageLabelType",notes = "用户分类(商户问题:shop_type , 系统问题:system_type)")
	private String messageLabelType;
	
	@ApiModelProperty(name = "messageLabelTypeName",notes = "用户分类")
	private String messageLabelTypeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFeedbackNumber() {
		return feedbackNumber;
	}

	public void setFeedbackNumber(String feedbackNumber) {
		this.feedbackNumber = feedbackNumber;
	}

	public String getFeedbackStatus() {
		return feedbackStatus;
	}

	public void setFeedbackStatus(String feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

	public String getFeedbackStatusName() {
		return feedbackStatusName;
	}

	public void setFeedbackStatusName(String feedbackStatusName) {
		this.feedbackStatusName = feedbackStatusName;
	}

	public String getSolveType() {
		return solveType;
	}

	public void setSolveType(String solveType) {
		this.solveType = solveType;
	}

	public String getSolveTypeName() {
		return solveTypeName;
	}

	public void setSolveTypeName(String solveTypeName) {
		this.solveTypeName = solveTypeName;
	}

	public String getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(String feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getFeedbackDesc() {
		return feedbackDesc;
	}

	public void setFeedbackDesc(String feedbackDesc) {
		this.feedbackDesc = feedbackDesc;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Long getIntentionShopId() {
		return intentionShopId;
	}

	public void setIntentionShopId(Long intentionShopId) {
		this.intentionShopId = intentionShopId;
	}

	public String getIntentionShopName() {
		return intentionShopName;
	}

	public void setIntentionShopName(String intentionShopName) {
		this.intentionShopName = intentionShopName;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getMessageLabel() {
		return messageLabel;
	}

	public void setMessageLabel(String messageLabel) {
		this.messageLabel = messageLabel;
	}

	public String getMessageLabelType() {
		return messageLabelType;
	}

	public void setMessageLabelType(String messageLabelType) {
		this.messageLabelType = messageLabelType;
	}

	public String getMessageLabelTypeName() {
		return messageLabelTypeName;
	}

	public void setMessageLabelTypeName(String messageLabelTypeName) {
		this.messageLabelTypeName = messageLabelTypeName;
	}
	
}
