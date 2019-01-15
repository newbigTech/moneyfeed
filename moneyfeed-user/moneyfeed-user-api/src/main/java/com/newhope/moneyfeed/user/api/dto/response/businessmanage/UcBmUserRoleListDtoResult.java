package com.newhope.moneyfeed.user.api.dto.response.businessmanage;

import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.user.api.bean.businessmanage.UcBmUserRoleModel;

public class UcBmUserRoleListDtoResult extends DtoResult{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5483290846456375818L;
	List<UcBmUserRoleModel> userRoleList;

	public List<UcBmUserRoleModel> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UcBmUserRoleModel> userRoleList) {
		this.userRoleList = userRoleList;
	}
	
	

}
