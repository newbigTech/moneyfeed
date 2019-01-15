package com.newhope.moneyfeed.user.api.dto.response.client;

import java.util.Date;
import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

/**
 *   用户中心-用户申请商户开户
 */
public class ClientUserShopApplyDtoResult extends Result {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5238570213194938884L;

	@ApiModelProperty("申请主键id")
	private Long applyId;

	@ApiModelProperty("分配商户id")
    private Long allotShopId;
    
	@ApiModelProperty("意向商户id")
    private Long intentionShopId;
    
	@ApiModelProperty("手机号")
    private String mobile;

	@ApiModelProperty("称呼")
    private String name;

	@ApiModelProperty("最后访问时间")
    private Date lastLoginTime;

    /** 客户端用户id */
	@ApiModelProperty("手机号")
    private Long clientUserId;

	@ApiModelProperty("申请备注")
    private String comment;

	@ApiModelProperty("用户地址")
    private String address;

	@ApiModelProperty("待分配、已分配")
    private String auditStatus;

	@ApiModelProperty("平台申请处理意见")
    private String platformDealMsg;
    
	@ApiModelProperty("商户处理意见")
    private String shopDealMsg;
    
    @ApiModelProperty("分配商户名称")
    private String allotShopName;

    @ApiModelProperty("意向商户名称")
    private String intentionShopName;
    
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

	public String getMobile() {
		return mobile;
	}

	public String getName() {
		return name;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public Long getClientUserId() {
		return clientUserId;
	}

	public String getAddress() {
		return address;
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

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public void setClientUserId(Long clientUserId) {
		this.clientUserId = clientUserId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public List<String> getShopNameList() {
		return shopNameList;
	}

	public void setShopNameList(List<String> shopNameList) {
		this.shopNameList = shopNameList;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

}