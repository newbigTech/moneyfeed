package com.newhope.openapi.api.vo.response.customer;


import java.util.Date;
import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;


public class IntentionCustomerResult extends Result {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5603726333113810929L;

	@ApiModelProperty("申请主键id")
	private Long applyId;

	@ApiModelProperty("分配商户id")
    private Long allotShopId;
	
	@ApiModelProperty("分配商户名称")
    private String allotShopName;
    
    @ApiModelProperty("意向商户id")
    private Long intentionShopId;
    
    @ApiModelProperty("意向商户名称")
    private String intentionShopName;
    
	@ApiModelProperty("姓名")
    private String name;
	
	@ApiModelProperty("手机号")
	private String mobile;
	
	@ApiModelProperty("最近一次登录时间")
	private Date lastLoginTime;
	
	@ApiModelProperty("地址")
	private String address;
	
	@ApiModelProperty("留言，申请备注")
	private String comment;
	
	@ApiModelProperty("客户端用户id")
    private Long clientUserId;

	@ApiModelProperty("平台处理:待分配、已分配")
    private String auditStatus;

	@ApiModelProperty("平台申请处理意见")
    private String platformDealMsg;
    
	@ApiModelProperty("商户处理意见")
    private String shopDealMsg;
	
	@ApiModelProperty("创建时间")
    private Date gmtCreated;
	
	@ApiModelProperty("所属商户")
	private List<String> shopNameList;

	public Long getApplyId() {
		return applyId;
	}

	public Long getAllotShopId() {
		return allotShopId;
	}

	public Long getIntentionShopId() {
		return intentionShopId;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public String getAddress() {
		return address;
	}

	public Long getClientUserId() {
		return clientUserId;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public String getPlatformDealMsg() {
		return platformDealMsg;
	}

	public String getShopDealMsg() {
		return shopDealMsg;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public void setAllotShopId(Long allotShopId) {
		this.allotShopId = allotShopId;
	}

	public void setIntentionShopId(Long intentionShopId) {
		this.intentionShopId = intentionShopId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setClientUserId(Long clientUserId) {
		this.clientUserId = clientUserId;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public void setPlatformDealMsg(String platformDealMsg) {
		this.platformDealMsg = platformDealMsg;
	}

	public void setShopDealMsg(String shopDealMsg) {
		this.shopDealMsg = shopDealMsg;
	}

	public String getAllotShopName() {
		return allotShopName;
	}

	public String getIntentionShopName() {
		return intentionShopName;
	}

	public void setAllotShopName(String allotShopName) {
		this.allotShopName = allotShopName;
	}

	public void setIntentionShopName(String intentionShopName) {
		this.intentionShopName = intentionShopName;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public List<String> getShopNameList() {
		return shopNameList;
	}

	public void setShopNameList(List<String> shopNameList) {
		this.shopNameList = shopNameList;
	}
    
}