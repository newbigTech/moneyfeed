package com.newhope.moneyfeed.user.api.dto.response.client;

import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserRoleModel;

public class UcClientUserRoleListDtoResult extends DtoResult{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4890257943478281346L;
	List<UcClientUserRoleModel> userRoleList;

	public List<UcClientUserRoleModel> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UcClientUserRoleModel> userRoleList) {
		this.userRoleList = userRoleList;
	}
	
	

}
