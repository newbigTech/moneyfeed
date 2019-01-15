package com.newhope.openapi.api.vo.request.feedback;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.newhope.moneyfeed.api.dto.request.PageDtoReq;

import io.swagger.annotations.ApiModelProperty;

public class FeedbackPageReq extends PageDtoReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @ApiModelProperty(name = "feedbackTimeStart",notes = "反馈起始时间")
	 private String feedbackTimeStart;
	 
	 @ApiModelProperty(name = "feedbackTimeEnd",notes = "反馈结束时间")
	 private String feedbackTimeEnd;
	 
	 @ApiModelProperty(name = "solveTypeList",notes = "商户分类")
	 private List<String> solveTypeList;
	 
	 @ApiModelProperty(name = "feedbackStatusList",notes = "状态")
	 private List<String> feedbackStatusList;
	 
	 @ApiModelProperty(name = "userId",notes = "联系人")
	 private Long userId;
	 
	 @ApiModelProperty(name = "intentionShopId",notes = "所属商户id")
	 private Long intentionShopId;
	 
	 @ApiModelProperty(name = "shopId",notes = "分配商户id")
	 private Long shopId;
	 
	 @ApiModelProperty(name = "channel",notes = "公众号:official_accounts , 商城:mall")
	 private String channel;
	 
	 @ApiModelProperty(name = "messageLabelTypeList",notes = "用户分类(商户问题:shop_type , 系统问题:system_type)")
	 private List<String> messageLabelTypeList;
	 
	public String getFeedbackTimeStart() {
		return feedbackTimeStart;
	}

	public void setFeedbackTimeStart(String feedbackTimeStart) {
		this.feedbackTimeStart = feedbackTimeStart;
	}

	public String getFeedbackTimeEnd() {
		return feedbackTimeEnd;
	}

	public void setFeedbackTimeEnd(String feedbackTimeEnd) {
		this.feedbackTimeEnd = feedbackTimeEnd;
	}

	
	public List<String> getSolveTypeList() {
		return solveTypeList;
	}

	public void setSolveTypeList(List<String> solveTypeList) {
		this.solveTypeList = solveTypeList;
	}


	public List<String> getFeedbackStatusList() {
		return feedbackStatusList;
	}

	public void setFeedbackStatusList(List<String> feedbackStatusList) {
		this.feedbackStatusList = feedbackStatusList;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getIntentionShopId() {
		return intentionShopId;
	}

	public void setIntentionShopId(Long intentionShopId) {
		this.intentionShopId = intentionShopId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public List<String> getMessageLabelTypeList() {
		return messageLabelTypeList;
	}

	public void setMessageLabelTypeList(List<String> messageLabelTypeList) {
		this.messageLabelTypeList = messageLabelTypeList;
	}
	
}
