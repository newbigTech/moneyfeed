package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   第三方账户关联APPID
 */
public class UcClientUserThirdAccountAppRelationshipModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7792740054109552174L;

	/** 用户ID */
    private Long appRelationshipId;

    /** 第三方账号ID */
    private Long thirdAccountId;

    public Long getAppRelationshipId() {
        return appRelationshipId;
    }

    public void setAppRelationshipId(Long appRelationshipId) {
        this.appRelationshipId = appRelationshipId;
    }

    public Long getThirdAccountId() {
        return thirdAccountId;
    }

    public void setThirdAccountId(Long thirdAccountId) {
        this.thirdAccountId = thirdAccountId;
    }
}