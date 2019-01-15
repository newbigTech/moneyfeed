package com.newhope.openapi.api.vo.response.user;

import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;


public class CustomerEmployeeListResult extends Result {
	
	private static final long serialVersionUID = -2154001677675149615L;
	
	private List<CustomerEmployeeResult> CustomerEmployees;

	public List<CustomerEmployeeResult> getCustomerEmployees() {
		return CustomerEmployees;
	}

	public void setCustomerEmployees(List<CustomerEmployeeResult> CustomerEmployees) {
		this.CustomerEmployees = CustomerEmployees;
	}}
