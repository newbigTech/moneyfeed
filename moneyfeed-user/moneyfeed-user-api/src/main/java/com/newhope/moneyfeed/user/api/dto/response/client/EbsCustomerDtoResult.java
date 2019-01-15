package com.newhope.moneyfeed.user.api.dto.response.client;


import java.io.Serializable;

import com.newhope.moneyfeed.user.api.bean.client.UcEbsCustomerModel;

public class EbsCustomerDtoResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3748524397771480383L;
	private UcEbsCustomerModel ebsCustomer;

	public UcEbsCustomerModel getEbsCustomer() {
		return ebsCustomer;
	}

	public void setEbsCustomer(UcEbsCustomerModel ebsCustomer) {
		this.ebsCustomer = ebsCustomer;
	}
	
}