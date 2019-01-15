package com.newhope.openapi.api.vo.response.feedback;

import java.io.Serializable;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import io.swagger.annotations.ApiModelProperty;

public class FeedbackDetailResult extends DtoResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(name = "feedbackNumber",notes = "留言编号")
	private String feedbackNumber;
	
	@ApiModelProperty(name = "feedbackStatus",notes = "待处理,pending, 处理完成：finished, 关闭留言：closed")
	private String feedbackStatus;
	
	@ApiModelProperty(name = "feedbackStatusName",notes = "状态名称")
	private String feedbackStatusName;
	
	@ApiModelProperty(name = "solveType",notes = "处理类型")
	private String solveType;
	
	@ApiModelProperty(name = "solveTypeName",notes = "处理类型名称")
	private String solveTypeName;
	
	@ApiModelProperty(name = "createTime",notes = "反馈时间")
	private String createTime;
	
	@ApiModelProperty(name = "userId",notes = "反馈人id")
	private Long userId;
	
	@ApiModelProperty(name = "userName",notes = "反馈人")
	private String userName;
	
	@ApiModelProperty(name = "mobile",notes = "反馈人手机")
	private String mobile;
	
	@ApiModelProperty(name = "companyName",notes = "所属公司")
	private String companyName;
	
	@ApiModelProperty(name = "feedbackDesc",notes = "反馈内容")
	private String feedbackDesc;
	
	@ApiModelProperty(name = "messageLabel",notes = "留言标签内容")
	private String messageLabel;
	
	@ApiModelProperty(name = "solveDesc",notes = "处理内容")
	private String solveDesc;
	
	@ApiModelProperty(name = "finishTime",notes = "完成时间")
	private String finishTime;
	
	@ApiModelProperty(name = "shopId",notes = "所属商户id")
	private Long intentionShopId;
	
	@ApiModelProperty(name = "intentionShopName",notes = "所属商户名称")
	private String intentionShopName;
	
	@ApiModelProperty(name = "shopId",notes = "分配商户id")
	private Long shopId;
	
	@ApiModelProperty(name = "shopName",notes = "分配商户名称")
	private String shopName;
	
	@ApiModelProperty(name = "distributedDesc",notes = "平台备注")
	private String distributedDesc;
	
	@ApiModelProperty(name = "messageLabelType",notes = "留言分类(商户问题:shop_type , 系统问题:system_type)")
	private String messageLabelType;
	
	@ApiModelProperty(name = "messageLabelTypeName",notes = "留言分类")
	private String messageLabelTypeName;
	
	@ApiModelProperty(name = "channel",notes = "反馈渠道")
	private String channel;
	
	@ApiModelProperty(name = "channelName",notes = "反馈渠道名称")
	private String channelName;
	

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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFeedbackDesc() {
		return feedbackDesc;
	}

	public void setFeedbackDesc(String feedbackDesc) {
		this.feedbackDesc = feedbackDesc;
	}

	public String getSolveDesc() {
		return solveDesc;
	}

	public void setSolveDesc(String solveDesc) {
		this.solveDesc = solveDesc;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
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

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getDistributedDesc() {
		return distributedDesc;
	}

	public void setDistributedDesc(String distributedDesc) {
		this.distributedDesc = distributedDesc;
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
		
}
