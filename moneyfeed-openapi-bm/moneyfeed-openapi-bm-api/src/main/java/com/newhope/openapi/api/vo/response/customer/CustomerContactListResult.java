package com.newhope.openapi.api.vo.response.customer;

import java.util.List;

import com.newhope.moneyfeed.api.vo.response.PageResult;


public class CustomerContactListResult extends PageResult {
	
	private static final long serialVersionUID = -2154001677675149615L;
	
	private List<CustomerContactResult> customerContacts;

	public List<CustomerContactResult> getCustomerContacts() {
		return customerContacts;
	}

	public void setCustomerContacts(List<CustomerContactResult> customerContacts) {
		this.customerContacts = customerContacts;
	}}
