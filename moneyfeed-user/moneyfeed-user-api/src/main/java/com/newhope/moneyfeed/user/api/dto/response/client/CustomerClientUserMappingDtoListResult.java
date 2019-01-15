package com.newhope.moneyfeed.user.api.dto.response.client;

import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;


public class CustomerClientUserMappingDtoListResult extends PageDtoResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8454352515666025288L;
	private List<CustomerClientUserMappingDtoResult> customerContacts = new ArrayList<CustomerClientUserMappingDtoResult>();

	public List<CustomerClientUserMappingDtoResult> getCustomerContacts() {
		return customerContacts;
	}

	public void setCustomerContacts(List<CustomerClientUserMappingDtoResult> customerContacts) {
		this.customerContacts = customerContacts;
	}}
