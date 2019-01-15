package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户中心-客户账户-返点账户
 */
public class UcCustomerAccountRebateModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8927353334604045519L;

	/** 客户账户id */
    private Long cutomerAccountId;

    /** ebs返点主键id */
    private String ebsRebateId;

    public Long getCutomerAccountId() {
        return cutomerAccountId;
    }

    public void setCutomerAccountId(Long cutomerAccountId) {
        this.cutomerAccountId = cutomerAccountId;
    }

    public String getEbsRebateId() {
        return ebsRebateId;
    }

    public void setEbsRebateId(String ebsRebateId) {
        this.ebsRebateId = ebsRebateId;
    }
}