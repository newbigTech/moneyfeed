package com.newhope.openapi.api.vo.response.user;


import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;


public class ClientRoleListResult extends Result {

	private static final long serialVersionUID = -4594036997864521468L;
	
	@ApiModelProperty("角色列表")
	private List<ClientRoleResult> roleList = new ArrayList<ClientRoleResult>();

	public List<ClientRoleResult> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<ClientRoleResult> roleList) {
		this.roleList = roleList;
	}
	
}