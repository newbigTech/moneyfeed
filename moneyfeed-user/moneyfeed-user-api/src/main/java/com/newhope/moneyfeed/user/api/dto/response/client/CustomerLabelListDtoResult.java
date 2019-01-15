package com.newhope.moneyfeed.user.api.dto.response.client;

import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmLabelDtoResult;

public class CustomerLabelListDtoResult extends DtoResult {

	private static final long serialVersionUID = 3909598851583750958L;

	private List<UcPmLabelDtoResult> labels;

	public List<UcPmLabelDtoResult> getLabels() {
		return labels;
	}

	public void setLabels(List<UcPmLabelDtoResult> labels) {
		this.labels = labels;
	}
	
	
}
