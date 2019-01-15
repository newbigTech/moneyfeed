package com.newhope.moneyfeed.user.api.dto.response.client;


import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcCustomerAccountStatementExModel;

public class CustomerAccountStatementListDtoResult extends PageDtoResult {

	
	private static final long serialVersionUID = -3677941749098348857L;
	
	private List<UcCustomerAccountStatementExModel> statements = new ArrayList<UcCustomerAccountStatementExModel>();

	public List<UcCustomerAccountStatementExModel> getStatements() {
		return statements;
	}

	public void setStatements(List<UcCustomerAccountStatementExModel> statements) {
		this.statements = statements;
	}

}