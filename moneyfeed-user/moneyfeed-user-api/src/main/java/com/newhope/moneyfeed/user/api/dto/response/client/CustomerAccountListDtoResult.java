package com.newhope.moneyfeed.user.api.dto.response.client;


import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

public class CustomerAccountListDtoResult extends DtoResult {

	
	private static final long serialVersionUID = 9100842956877876368L;
	
	private List<CustomerAccountStatementDtoResult> statements;

	public List<CustomerAccountStatementDtoResult> getStatements() {
		return statements;
	}

	public void setStatements(List<CustomerAccountStatementDtoResult> statements) {
		this.statements = statements;
	}


}