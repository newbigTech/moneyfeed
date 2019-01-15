package com.newhope.moneyfeed.user.api.bean.platform;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户中心-客户管理系统-用户角色表
 */
public class UcPmUserRoleModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5367611602164734557L;

	/** 用户id */
    private Long userId;

    /** 角色岗位表 */
    private Long roleId;

    /** 是否可用 */
    private Boolean enable;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}