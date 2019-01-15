package com.newhope.openapi.api.vo.response.user;

import com.newhope.moneyfeed.api.vo.response.PageResult;

import java.util.List;

/**
 *   用户中心-商户用户集合
 */
public class PmUserListResult extends PageResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 408145876910539836L;
	
	public List<PmUserResult> userList;

	public List<PmUserResult> getUserList() {
		return userList;
	}

	public void setUserList(List<PmUserResult> userList) {
		this.userList = userList;
	}
}