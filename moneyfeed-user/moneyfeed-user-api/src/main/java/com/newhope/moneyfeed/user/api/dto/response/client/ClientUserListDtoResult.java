package com.newhope.moneyfeed.user.api.dto.response.client;


import java.util.List;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserModel;

public class ClientUserListDtoResult extends PageDtoResult {

	private static final long serialVersionUID = -6814400521974893982L;
	
	private List<UcClientUserModel> users;

	public List<UcClientUserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UcClientUserModel> users) {
		this.users = users;
	}

	
}