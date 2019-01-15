package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

public class UcCustomerPayModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7021903927837759457L;

	/** 客户id */
    private Long customerId;

    /** 支付密码 */
    private String password;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}