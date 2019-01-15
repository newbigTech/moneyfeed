package com.newhope.moneyfeed.user.api.dto.response.client;

import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;

/**
 *   用户申请列表
 */
public class ClientUserShopApplyDtoListResult extends PageDtoResult  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8757471604734030955L;
	
	private List<ClientUserShopApplyDtoResult> userShopApplyList = new ArrayList<>();

	public List<ClientUserShopApplyDtoResult> getUserShopApplyList() {
		return userShopApplyList;
	}

	public void setUserShopApplyList(List<ClientUserShopApplyDtoResult> userShopApplyList) {
		this.userShopApplyList = userShopApplyList;
	}
	
	
}