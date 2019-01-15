package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户中心-客户账户-临欠账户
 */
public class UcCustomerAccountCreditModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5495746369336427860L;

	/** 客户账户id */
    private Long cutomerAccountId;

    /** ebs临欠主键id */
    private String ebsCreditId;

    public Long getCutomerAccountId() {
        return cutomerAccountId;
    }

    public void setCutomerAccountId(Long cutomerAccountId) {
        this.cutomerAccountId = cutomerAccountId;
    }

    public String getEbsCreditId() {
        return ebsCreditId;
    }

    public void setEbsCreditId(String ebsCreditId) {
        this.ebsCreditId = ebsCreditId;
    }
}