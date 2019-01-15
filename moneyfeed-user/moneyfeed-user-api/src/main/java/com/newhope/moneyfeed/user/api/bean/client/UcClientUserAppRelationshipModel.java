package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户关联APPID
 */
public class UcClientUserAppRelationshipModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7642577762976704781L;

	/** 用户ID */
    private Long userId;

    /** APPCODE */
    private String appCode;

    /** 操作端来源 */
    private String appSource;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppSource() {
        return appSource;
    }

    public void setAppSource(String appSource) {
        this.appSource = appSource;
    }
}