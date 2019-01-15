package com.newhope.moneyfeed.user.api.bean.platform;

import com.newhope.moneyfeed.api.bean.BaseModel;

public class UcPmRoleResourceModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6204942431536134657L;

	/** 角色岗位id */
    private Long roleId;

    /** 系统权限id */
    private Long resourceId;

    /** 是否可用 */
    private Boolean enable;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}