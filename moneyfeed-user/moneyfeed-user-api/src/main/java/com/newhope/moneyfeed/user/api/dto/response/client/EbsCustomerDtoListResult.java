package com.newhope.moneyfeed.user.api.dto.response.client;


import java.util.List;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;



public class EbsCustomerDtoListResult extends PageDtoResult {


	/**
	 * 
	 */
	private static final long serialVersionUID = 9177688013786242992L;
	
	private List<EbsCustomerDtoResult> ebsCustomers;
	
	public List<EbsCustomerDtoResult> getEbsCustomers() {
		return ebsCustomers;
	}
	public void setEbsCustomers(List<EbsCustomerDtoResult> ebsCustomers) {
		this.ebsCustomers = ebsCustomers;
	}

}