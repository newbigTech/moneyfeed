package com.newhope.openapi.api.vo.response.customer;

import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.vo.response.PageResult;

public class FinancialPaymentFlowListResult extends PageResult {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3222404403496192906L;
	
	private List<FinancialPaymentFlowResult> dataList = new ArrayList<FinancialPaymentFlowResult>();

	public List<FinancialPaymentFlowResult> getDataList() {
		return dataList;
	}

	public void setDataList(List<FinancialPaymentFlowResult> dataList) {
		this.dataList = dataList;
	}
	
}
