package com.newhope.openapi.api.vo.response.customer;

import java.util.List;

import com.newhope.moneyfeed.api.vo.response.PageResult;


public class IntentionCustomerListResult extends PageResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6250284607788174997L;
	
	private List<IntentionCustomerResult> intentionCustomers;

	public List<IntentionCustomerResult> getIntentionCustomers() {
		return intentionCustomers;
	}

	public void setIntentionCustomers(List<IntentionCustomerResult> intentionCustomers) {
		this.intentionCustomers = intentionCustomers;
	}

}
