package com.newhope.openapi.api.vo.response.feedback;

import java.io.Serializable;

import com.newhope.moneyfeed.common.util.excel.annotation.ExcelField;

import io.swagger.annotations.ApiModelProperty;

public class FeedbackItemResult implements Serializable{

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
	
	@ApiModelProperty(name = "messageLabelType",notes = "反馈分类(商户问题:shop_type , 系统问题:system_type)")
	private String messageLabelType;
	
	@ApiModelProperty(name = "messageLabelTypeName",notes = "反馈分类")
	private String messageLabelTypeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ExcelField(title = "留言编号", align = 2, sort = 1)
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
	
	@ExcelField(title = "状态", align = 2, sort = 2)
	public String getFeedbackStatusName() {
		return feedbackStatusName;
	}

	public void setFeedbackStatusName(String feedbackStatusName) {
		this.feedbackStatusName = feedbackStatusName;
	}
	
	public String getMessageLabelType() {
		return messageLabelType;
	}

	public void setMessageLabelType(String messageLabelType) {
		this.messageLabelType = messageLabelType;
	}
	
	@ExcelField(title = "反馈分类", align = 2, sort = 3)
	public String getMessageLabelTypeName() {
		return messageLabelTypeName;
	}

	public void setMessageLabelTypeName(String messageLabelTypeName) {
		this.messageLabelTypeName = messageLabelTypeName;
	}

	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@ExcelField(title = "联系人", align = 2, sort = 4)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@ExcelField(title = "联系人手机", align = 2, sort = 5)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@ExcelField(title = "反馈时间", align = 2, sort = 6)
	public String getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(String feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	@ExcelField(title = "反馈渠道", align = 2, sort = 7)
	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	@ExcelField(title = "所属客户", align = 2, sort = 8)
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public Long getIntentionShopId() {
		return intentionShopId;
	}

	public void setIntentionShopId(Long intentionShopId) {
		this.intentionShopId = intentionShopId;
	}
	
	@ExcelField(title = "针对商户", align = 2, sort = 9)
	public String getIntentionShopName() {
		return intentionShopName;
	}

	public void setIntentionShopName(String intentionShopName) {
		this.intentionShopName = intentionShopName;
	}
	
	@ExcelField(title = "分配商户", align = 2, sort = 10)
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	public String getSolveType() {
		return solveType;
	}

	public void setSolveType(String solveType) {
		this.solveType = solveType;
	}
	
	@ExcelField(title = "商户分类", align = 2, sort = 11)
	public String getSolveTypeName() {
		return solveTypeName;
	}

	public void setSolveTypeName(String solveTypeName) {
		this.solveTypeName = solveTypeName;
	}
	
	public String getFeedbackDesc() {
		return feedbackDesc;
	}

	public void setFeedbackDesc(String feedbackDesc) {
		this.feedbackDesc = feedbackDesc;
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


}
