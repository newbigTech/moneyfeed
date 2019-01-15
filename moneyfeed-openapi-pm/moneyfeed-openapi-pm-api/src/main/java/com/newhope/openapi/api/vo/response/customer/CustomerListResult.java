package com.newhope.openapi.api.vo.response.customer;

import java.util.List;

import com.newhope.moneyfeed.api.vo.response.PageResult;


public class CustomerListResult extends PageResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = -932482751299826997L;
	
	private List<CustomerResult> customers;

	public List<CustomerResult> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerResult> customers) {
		this.customers = customers;
	}

}
