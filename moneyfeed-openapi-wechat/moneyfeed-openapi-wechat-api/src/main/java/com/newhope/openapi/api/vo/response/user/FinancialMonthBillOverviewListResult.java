package com.newhope.openapi.api.vo.response.user;

import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;

public class FinancialMonthBillOverviewListResult extends Result {
    
	private static final long serialVersionUID = -5409912063039942310L;
	
	private List<FinancialMonthBillOverviewResult> dataList = new ArrayList<FinancialMonthBillOverviewResult>();

	public List<FinancialMonthBillOverviewResult> getDataList() {
		return dataList;
	}

	public void setDataList(List<FinancialMonthBillOverviewResult> dataList) {
		this.dataList = dataList;
	}
	
	
}
