package com.newhope.moneyfeed.feedback.api.dto.request;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class FeedbackDtoReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(name = "feedbackDesc",notes = "留言内容（非必填，250个字符）")
    private String feedbackDesc;
    
    @ApiModelProperty(name = "messageLabel",notes = "留言标签")
    private String messageLabel;
    
    @ApiModelProperty(name = "messageLabelType",notes = "留言分类(商户问题:shop_type , 系统问题:system_type)")
    private String messageLabelType;
    
    @ApiModelProperty(name = "orderId",notes = "订单id(没有就不传)")
    private Long orderId;
    
    @ApiModelProperty(name = "feedbackTime",notes = "日期")
    private Date feedbackTime;
    
    @ApiModelProperty(name = "officialAccountsMobile",notes = "公众号手机号码输入(必传)")
    private String officialAccountsMobile;
    
    @ApiModelProperty(name = "officialAccountsName",notes = "公众号账户名称")
    private String officialAccountsName;
    
    @ApiModelProperty(name = "intentionShopId",notes = "公众号商户Id")
    private Long intentionShopId;
    
    @ApiModelProperty(name = "channel",notes = "公众号:official_accounts , 商城:mall")
    private String channel;

	public String getFeedbackDesc() {
		return feedbackDesc;
	}

	public void setFeedbackDesc(String feedbackDesc) {
		this.feedbackDesc = feedbackDesc;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getOfficialAccountsMobile() {
		return officialAccountsMobile;
	}

	public void setOfficialAccountsMobile(String officialAccountsMobile) {
		this.officialAccountsMobile = officialAccountsMobile;
	}

	public String getOfficialAccountsName() {
		return officialAccountsName;
	}

	public void setOfficialAccountsName(String officialAccountsName) {
		this.officialAccountsName = officialAccountsName;
	}

	public Long getIntentionShopId() {
		return intentionShopId;
	}

	public void setIntentionShopId(Long intentionShopId) {
		this.intentionShopId = intentionShopId;
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
    
}
