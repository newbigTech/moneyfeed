package com.newhope.moneyfeed.user.api.bean.platform;

import com.newhope.moneyfeed.api.bean.BaseModel;

public class UcPmLabelCustomerMappingModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7499741305015622809L;

	private Long labelId;

    private Long customerId;

    /** 是否可用 */
    private Boolean enable;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
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