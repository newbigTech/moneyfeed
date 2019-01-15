package com.newhope.openapi.api.vo.response.user;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.newhope.moneyfeed.api.vo.response.PageResult;

public class FinancialPaymentFlowListResult extends PageResult {
	private static final long serialVersionUID = -1751342725013541901L;

	private TreeMap<String, List<FinancialPaymentFlowResult>> flow;

    public TreeMap<String, List<FinancialPaymentFlowResult>> getFlow() {
        return flow;
    }

    public void setFlow(TreeMap<String, List<FinancialPaymentFlowResult>> flow) {
        this.flow = flow;
    }
}
