package com.newhope.moneyfeed.user.api.dto.response.client;

import java.io.Serializable;

import com.newhope.moneyfeed.user.api.bean.client.UcCustomerAccountModel;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerAccountStatementModel;


public class CustomerAccountStatementDtoResult implements Serializable {
	
	private static final long serialVersionUID = 7280885861736159277L;

	UcCustomerAccountStatementModel statement;
	
	UcCustomerAccountModel account;

	public UcCustomerAccountStatementModel getStatement() {
		return statement;
	}

	public void setStatement(UcCustomerAccountStatementModel statement) {
		this.statement = statement;
	}

	public UcCustomerAccountModel getAccount() {
		return account;
	}

	public void setAccount(UcCustomerAccountModel account) {
		this.account = account;
	}
	
	
}
