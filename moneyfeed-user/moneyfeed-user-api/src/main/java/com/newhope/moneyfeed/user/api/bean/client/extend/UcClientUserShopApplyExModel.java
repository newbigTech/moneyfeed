package com.newhope.moneyfeed.user.api.bean.client.extend;

import java.util.Date;

import com.newhope.moneyfeed.user.api.bean.client.UcClientUserShopApplyModel;

/**
 *   用户中心-用户申请商户开户扩展表
 */
public class UcClientUserShopApplyExModel extends UcClientUserShopApplyModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4826962740148191580L;


    /** 最后访问时间 */
    private Date lastLoginTime;
    
    /**
     * 分配商户名称
     */
    private String allotShopName;

    /**
     * 意向商户名称
     */
    private String intentionShopName;

	public Date getLastLoginTime() {
		return lastLoginTime;
	}


	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
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

}