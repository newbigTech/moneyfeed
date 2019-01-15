package com.newhope.openapi.api.vo.request.feedback;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class FeedbackStatusReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="留言记录id为空")
	@ApiModelProperty(name = "id",notes = "留言记录id")
	private Long id;
	
	@ApiModelProperty(name = "solveType",notes = "留言类型(传英文)(账目不清晰：account_not_clear,质量问题:quality_problem,服务问题:service_problem,其它:other)")
	private String solveType;
	
	@ApiModelProperty(name = "solveDesc",notes = "备注信息")
	private String solveDesc;
	
	@NotBlank(message="完结状态feedbackStatus为空")
	@ApiModelProperty(name = "feedbackStatus",notes = "商户完结：shop_closed")
	private String feedbackStatus;
	
	@NotBlank(message="是否需要推送微信消息标识为空")
	@ApiModelProperty(name = "sendWechatMsg",notes = "是否需要推送微信消息(是：true , 否：false , 默认为否)")
	private Boolean sendWechatMsg = false;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSolveType() {
		return solveType;
	}

	public void setSolveType(String solveType) {
		this.solveType = solveType;
	}

	public String getSolveDesc() {
		return solveDesc;
	}

	public void setSolveDesc(String solveDesc) {
		this.solveDesc = solveDesc;
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
