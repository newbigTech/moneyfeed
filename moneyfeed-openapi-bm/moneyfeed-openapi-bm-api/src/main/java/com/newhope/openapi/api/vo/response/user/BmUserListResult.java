package com.newhope.openapi.api.vo.response.user;

import com.newhope.moneyfeed.api.vo.response.PageResult;

import java.util.List;

/**
 *   用户中心-商户用户集合
 */
public class BmUserListResult extends PageResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 408145876910539836L;
	
	public List<BmUserResult> userList;

	public List<BmUserResult> getUserList() {
		return userList;
	}

	public void setUserList(List<BmUserResult> userList) {
		this.userList = userList;
	}
}