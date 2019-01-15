package com.newhope.openapi.api.vo.response.user;


import com.newhope.moneyfeed.api.vo.response.Result;

/**
 *   用户中心-客户管理系统-角色信息
 */
public class BmRoleResult extends Result {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2898404232546096353L;

	/** 主键ID */
    private Long id;

	/** 角色岗位code */
    private String roleCode;

    /** 角色岗位名称 */
    private String name;

	public Long getId() {
		return id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public void setName(String name) {
		this.name = name;
	}

}