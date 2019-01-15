package com.newhope.moneyfeed.user.api.dto.response.platform;

import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmUserRoleModel;

public class UcPmUserRoleListDtoResult extends DtoResult{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4826682602990168220L;
	List<UcPmUserRoleModel> userRoleList;

	public List<UcPmUserRoleModel> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UcPmUserRoleModel> userRoleList) {
		this.userRoleList = userRoleList;
	}
	
	

}
