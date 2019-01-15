package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户中心-用户申请商户开户
 */
public class UcClientUserShopApplyModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4855297160106107723L;

	/** 意向商户id */
    private Long intentionShopId;

    /** 分配商户id */
    private Long allotShopId;

    /** 客户端用户id */
    private Long clientUserId;

    /** 申请备注 */
    private String comment;

    /** 用户地址 */
    private String address;

    /** 申请状态:待审核/审核通过/审核失败 */
    private String status;

    /** 申请处理意见 */
    private String platformDealMsg;

    /** 商户处理意见 */
    private String shopDealMsg;

    /** 是否可用 */
    private Boolean enable;

    /** 称呼 */
    private String name;

    /** 电话号码 */
    private String mobile;

    public Long getIntentionShopId() {
        return intentionShopId;
    }

    public void setIntentionShopId(Long intentionShopId) {
        this.intentionShopId = intentionShopId;
    }

    public Long getAllotShopId() {
        return allotShopId;
    }

    public void setAllotShopId(Long allotShopId) {
        this.allotShopId = allotShopId;
    }

    public Long getClientUserId() {
        return clientUserId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlatformDealMsg() {
        return platformDealMsg;
    }

    public void setPlatformDealMsg(String platformDealMsg) {
        this.platformDealMsg = platformDealMsg;
    }

    public String getShopDealMsg() {
        return shopDealMsg;
    }

    public void setShopDealMsg(String shopDealMsg) {
        this.shopDealMsg = shopDealMsg;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}