package com.newhope.moneyfeed.user.api.bean.platform;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户中心-商户管理系统-权限表
 */
public class UcPmResourceModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1728003041825580976L;

	/** 权限code */
    private String code;

    /** 权限名称 */
    private String name;

    /** 权限类型 */
    private String type;

    /** 来源类型（运营端/商户端/WeChat） */
    private String sourceType;

    /** 父集角色id */
    private Long parentId;

    /** 如果是菜单，菜单跳转地址 */
    private String url;

    /** 菜单排序 */
    private Integer sort;

    /** 图标 */
    private String icon;

    /** 是否可用 */
    private Boolean enable;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}