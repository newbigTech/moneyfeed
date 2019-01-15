package com.newhope.moneyfeed.user.api.dto.request.client;

import java.io.Serializable;
import java.util.List;

public class CustomerAccountStatementPostListDtoReq implements Serializable {
	
	private static final long serialVersionUID = -2734933790139729067L;
	
	private List<CustomerAccountStatementPostDtoReq> dataList;

	public List<CustomerAccountStatementPostDtoReq> getDataList() {
		return dataList;
	}

	public void setDataList(List<CustomerAccountStatementPostDtoReq> dataList) {
		this.dataList = dataList;
	}
	
}
