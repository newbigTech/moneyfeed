package com.newhope.moneyfeed.user.api.bean.platform;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户中心-客户管理系统-角色表
 */
public class UcPmRoleModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8830211464672986244L;

	/** 角色岗位code */
    private String code;

    /** 角色岗位名称 */
    private String name;

    /** 父集角色id */
    private Long parentId;

    /** 是否可用 */
    private Boolean enable;

    /** 来源类型（运营端/商户端/WeChat） */
    private String sourceType;

    /** 角色简介 */
    private String comment;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}