package com.newhope.openapi.api.vo.response.customer;

import java.util.List;

import com.newhope.moneyfeed.api.vo.response.PageResult;

public class FinancialBillDetailListResult extends PageResult {
	
	private static final long serialVersionUID = 7687431914723627990L;
	
	private List<FinancialBillDetailResult> detail;

    public List<FinancialBillDetailResult> getDetail() {
        return detail;
    }

    public void setDetail(List<FinancialBillDetailResult> detail) {
        this.detail = detail;
    }
}
