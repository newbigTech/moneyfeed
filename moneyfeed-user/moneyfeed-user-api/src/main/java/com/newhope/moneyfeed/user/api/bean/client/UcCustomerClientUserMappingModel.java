package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户中心-客户用户关系表
 */
public class UcCustomerClientUserMappingModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2634209122086442717L;

	/** 客户id */
    private Long customerId;

    /** 用户手机号 */
    private String clientUserMobile;

    /** 用户名称 */
    private String clientUserName;

    /** 客户端用户id */
    private Long clientUserId;

    /** 是否可用 */
    private Boolean enable;

    /** 系统来源 */
    private String source;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getClientUserMobile() {
        return clientUserMobile;
    }

    public void setClientUserMobile(String clientUserMobile) {
        this.clientUserMobile = clientUserMobile;
    }

    public String getClientUserName() {
        return clientUserName;
    }

    public void setClientUserName(String clientUserName) {
        this.clientUserName = clientUserName;
    }

    public Long getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(Long clientUserId) {
        this.clientUserId = clientUserId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}