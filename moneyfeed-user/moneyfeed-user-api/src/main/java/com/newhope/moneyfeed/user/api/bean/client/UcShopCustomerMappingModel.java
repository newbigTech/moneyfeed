package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户中心-商户客户关系表
 */
public class UcShopCustomerMappingModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -109955680632902685L;

	private Long shopId;

    private Long customerId;

    /** 是否可用 */
    private Boolean enable;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}