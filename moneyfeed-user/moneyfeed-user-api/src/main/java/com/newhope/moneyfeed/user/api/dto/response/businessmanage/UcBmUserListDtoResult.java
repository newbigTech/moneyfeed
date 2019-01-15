package com.newhope.moneyfeed.user.api.dto.response.businessmanage;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;

import java.util.List;

/**
 *   用户中心-商户用户集合
 */
public class UcBmUserListDtoResult extends PageDtoResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 408145876910539836L;
	
	public List<UcBmUserDtoResult> userList;

	public List<UcBmUserDtoResult> getUserList() {
		return userList;
	}

	public void setUserList(List<UcBmUserDtoResult> userList) {
		this.userList = userList;
	}
	
}