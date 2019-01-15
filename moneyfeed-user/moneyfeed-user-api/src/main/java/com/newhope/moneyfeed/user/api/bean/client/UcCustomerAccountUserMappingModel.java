package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户中心-客户账户授权表
 */
public class UcCustomerAccountUserMappingModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 238120043574508761L;

	/** 用户id */
    private Long clientUserId;

    /** 客户id */
    private Long customerId;

    /** 客户账户id */
    private Long customerAccountId;

    /** 是否可用 */
    private Boolean enable;

    public Long getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(Long clientUserId) {
        this.clientUserId = clientUserId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(Long customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}