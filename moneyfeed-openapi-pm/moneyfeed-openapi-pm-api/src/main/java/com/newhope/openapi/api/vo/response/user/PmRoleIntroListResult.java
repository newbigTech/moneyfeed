package com.newhope.openapi.api.vo.response.user;


import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.vo.response.PageResult;

public class PmRoleIntroListResult extends PageResult {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1312607299728933447L;
	
	private List<PmRoleIntroResult> roles = new ArrayList<PmRoleIntroResult>();;

	public List<PmRoleIntroResult> getRoles() {
		return roles;
	}

	public void setRoles(List<PmRoleIntroResult> roles) {
		this.roles = roles;
	}
	
}