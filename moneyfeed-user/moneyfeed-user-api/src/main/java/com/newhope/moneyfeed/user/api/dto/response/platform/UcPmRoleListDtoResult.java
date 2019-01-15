package com.newhope.moneyfeed.user.api.dto.response.platform;

import java.util.List;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;

public class UcPmRoleListDtoResult extends PageDtoResult {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4731019135764721925L;
	
	private List<UcPmRoleModel> roleList;


	public List<UcPmRoleModel> getRoleList() {
		return roleList;
	}


	public void setRoleList(List<UcPmRoleModel> roleList) {
		this.roleList = roleList;
	}

}