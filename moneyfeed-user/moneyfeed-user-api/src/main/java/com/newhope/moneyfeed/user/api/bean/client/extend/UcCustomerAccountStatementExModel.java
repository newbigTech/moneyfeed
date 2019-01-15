package com.newhope.moneyfeed.user.api.bean.client.extend;

import com.newhope.moneyfeed.user.api.bean.client.UcCustomerAccountModel;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerAccountStatementModel;

public class UcCustomerAccountStatementExModel extends UcCustomerAccountStatementModel {

	private static final long serialVersionUID = 5472632982396096772L;
	
	UcCustomerAccountModel account;

	public UcCustomerAccountModel getAccount() {
		return account;
	}

	public void setAccount(UcCustomerAccountModel account) {
		this.account = account;
	}
	
	
}