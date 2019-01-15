package com.newhope.moneyfeed.user.api.dto.response.platform;

import java.io.Serializable;

/**
 *   用户中心-客户管理系统
 */
public class UcPmRoleDtoResult implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 5461996729451846573L;

	/** 主键ID */
    private Long id;

	/** 角色岗位code */
    private String code;

    /** 角色岗位名称 */
    private String name;
    
    /** 角色岗位描述 */
    private String comment;

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}