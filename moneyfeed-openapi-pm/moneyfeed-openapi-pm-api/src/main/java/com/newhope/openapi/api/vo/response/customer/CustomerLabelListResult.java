package com.newhope.openapi.api.vo.response.customer;

import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.response.label.LabelResult;

public class CustomerLabelListResult extends Result {

	private static final long serialVersionUID = -5677990957189755450L;
	
	private List<LabelResult> labels;

	public List<LabelResult> getLabels() {
		return labels;
	}

	public void setLabels(List<LabelResult> labels) {
		this.labels = labels;
	}
	

}
