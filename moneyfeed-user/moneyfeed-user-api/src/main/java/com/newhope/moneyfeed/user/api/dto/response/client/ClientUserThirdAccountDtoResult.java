package com.newhope.moneyfeed.user.api.dto.response.client;


import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserThirdAccountModel;

public class ClientUserThirdAccountDtoResult extends DtoResult {

	private static final long serialVersionUID = 5956970357471403674L;
	
	private UcClientUserThirdAccountModel account;

	public UcClientUserThirdAccountModel getAccount() {
		return account;
	}

	public void setAccount(UcClientUserThirdAccountModel account) {
		this.account = account;
	}
	
	
}