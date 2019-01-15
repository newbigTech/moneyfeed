package com.newhope.moneyfeed.user.api.dto.response.platform;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserDtoResult;

import java.util.List;

/**
 *   用户中心-商户用户集合
 */
public class UcPmUserListDtoResult extends PageDtoResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 408145876910539836L;
	
	public List<UcPmUserDtoResult> userList;

	public List<UcPmUserDtoResult> getUserList() {
		return userList;
	}

	public void setUserList(List<UcPmUserDtoResult> userList) {
		this.userList = userList;
	}
}