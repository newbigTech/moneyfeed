package com.newhope.moneyfeed.user.api.dto.response.client;


import java.util.List;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcCustomerExModel;



public class CustomerDtoListResult extends PageDtoResult {

	private static final long serialVersionUID = 7698038571317226394L;
	
	private List<UcCustomerExModel> customers;

	public List<UcCustomerExModel> getCustomers() {
		return customers;
	}

	public void setCustomers(List<UcCustomerExModel> customers) {
		this.customers = customers;
	}
	
}